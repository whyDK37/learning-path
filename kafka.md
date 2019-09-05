# 工作原理

- 高吞吐，低延迟： 顺序写盘，追加到文件末尾；利用os cache缓存；
- 零拷贝， page cache , 利用linux sendFile 机制；
- 压缩日志文件；
- 集群存储， 多副本冗余；
- 数据不丢失，ISR机制, in sync replacation
- 请求负载均衡  
- 使用zk 让 kafka broker 实现无状态。

## partition

几个核心offset:
- LEO log end offset, leader 收到数据后会更新，下一条需要写入的offset
  - follower 主动拉去数据的时候会发送自己的LEO
  - leader 会维护所有的follower列表和LEO， 发送数据给 follower。
  - follower 收到数据，更新自己的LEO
  - 当leader发现所有的follower都更新到新的LEO，就会更新HW
- HW  high water , leader follow 都同步到的数据，LEO有可能比HW大，等follow同步后，HW会后移动。消费者只能消费到HW。

## ISR
- 默认落后4000条数据，会被剔除。慢慢会跟上后会加回来。比如leader突然有大量数据涌入的情况。如果有这种情况， 可以把这个数字设置大一些， 比如10w，防止触发剔除条件。
  - 新版本使用 replica.lag.max.ms设置落后时间，去掉了 replica.lag.max.messages参数

## 写数据原理

## 读数据原理

# 集群配置参数
## kafka 内核参数(51)
- broker.id ：唯一 id
- log.dirs ： kafka 保存数据的路径， 可配置多个
- zookeeper.connect : zk 链接地址，不用多说了
- listeners ： 监听端口。
- unclean.leader.election.enable ： 制选举 ISR 列表里的 follower 为 leader。
- delete.topic.enable ： 允许删除 topic
- log.retention.hours ： kafka 消息保存的你小时，默认 168， 也就是 7 天。
- log.retention.bytes： 不常用， 默认 -1
- **min.insync.replicas**；重要！集群一般都是双副本，为了保证数据不丢失，可设置为2， 如果允许不分丢失可设置为1， 提高吞吐量. acks=-1, 让 ISR 列表里的副本都同步。
- num.io.threads： 写盘线程，可适当调大。
- num.network.threads： 接受请求线程， 可适当调大。
- message.max.bytes ： 可适当调打，防止消息过大 kafka 直接拒绝， 增加此参数的同时需要增加消费端参数。参数可以设置到 topic 维度。

## JVM、GC 参数(52)
- 主要设置堆内存，一般6g以上。
- gc， 一般用g1， 可以设置最小停顿时间。
- jmx 需要开启： JMX_PORT=9997
## 操作系统参数(53)
- ulimit -n 100000, 增加文件描述符
- 设置刷盘时间： 默认是5秒， 如果设置的大些， 可提高吞吐量。vm.dirty_*

## 核心监控指标
首先打开 JMX 监控端口，然后才能监控 broker 的指标。
- 机器层面， cpu，内存， 网络，磁盘
- jvm层面，gc
- kafka，异常日志，链接空闲，ISR刷新，kafka JMX 的一些监控指标。

# 参考
- [http://kafka.apache.org/](http://kafka.apache.org/)
- [kafka document 官方文档](http://kafka.apache.org/22/documentation.html)

- [Kafka+Replication](https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Replication)
- [一文看懂Kafka消息格式的演变](https://blog.csdn.net/u013256816/article/details/80300225)
- [Kafka水位(high watermark)与leader epoch的讨论](https://www.cnblogs.com/huxi2b/p/7453543.html)
- [The Log: What every software engineer should know about real-time data's unifying abstraction](https://engineering.linkedin.com/distributed-systems/log-what-every-software-engineer-should-know-about-real-time-datas-unifying)
- [How to choose the number of topics/partitions in a Kafka cluster?](https://www.confluent.io/blog/how-choose-number-topics-partitions-kafka-cluster)

- [How to Support More Queues in RocketMQ?](http://rocketmq.apache.org/rocketmq/how-to-support-more-queues-in-rocketmq/) 对比分析了RocketMq和kafka，解释为什么要写RocketMq。由于Kafka是分partition的，所以写入是分散的，很难利用Linux IO Group Commit 机制

- [为了追求极致的性能，Kafka掌控这11项要领](https://blog.csdn.net/u013256816/article/details/93772377) 。从11个点描述kafka的设计细节，包括日志格式，批处理，消息压缩，客户端优化，分区，顺序写盘，页缓存（不清楚这一点的代码实现逻辑，如何匹配OS的缓存机制呢？）等。
- [Linux IO磁盘篇整理小记](https://blog.csdn.net/u013256816/article/details/78945085) 
