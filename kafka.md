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
- jmx 监控broker的吞吐率，观察高峰期吞吐量是否打满， 如果打满了可能需要扩容；
- 监控磁盘的使用率，业务两的增加需要磁盘扩容。
- 集群负载倾斜，此时需要手动调整分区，使请求平均。
- kafka 报错异常，解决报错问题，gc问题。这时非常考验源码功底。

## java api 参数
缓冲区大小 
- buffer.memory：设置发送消息的缓冲区，默认值是33554432，就是32MB。 
如果发送消息出去的速度小于写入消息进去的速度，就会导致缓冲区写满，此时生产消息就会阻塞住，所以说这里就应该多做一些压测，尽可能保证说这块缓冲区不会被写满导致生产行为被阻塞住

- compression.type，默认是none，不压缩，但是也可以使用lz4压缩，效率还是不错的，压缩之后可以减小数据量，提升吞吐量，但是会加大producer端的cpu开销

下面两个参数配合 batch 使用。
- batch.size，设置meigebatch的大小，如果batch太小，会导致频繁网络请求，吞吐量下降；如果batch太大，会导致一条消息需要等待很久才能被发送出去，而且会让内存缓冲区有很大压力，过多数据缓冲在内存里
默认值是：16384，就是16kb，也就是一个batch满了16kb就发送出去，一般在实际生产环境，这个batch的值可以增大一些来提升吞吐量，可以自己压测一下
一般需要压测来调整。

- linger.ms，这个值默认是0，意思就是消息必须立即被发送，**但是这是不对的**，一般设置一个100毫秒之类的，这样的话就是说，这个消息被发送出去后进入一个batch，如果100毫秒内，这个batch满了16kb，自然就会发送出去
但是如果100毫秒内，batch没满，那么也必须把消息发送出去了，不能让消息的发送延迟时间太长，也避免给内存造成过大的一个压力
这个参数的时间一般会比一个batch的时间稍微大一点，这个时间需要测试， 多长时间可以凑成一个batch。

- max.request.size：这个参数用来控制发送出去的消息的大小，默认是1048576字节，也就1mb，这个一般太小了，很多消息可能都会超过1mb的大小，所以需要自己优化调整，把他设置更大一些
你发送出去的一条大数据，超大的JSON串，超过1MB，就不让你发了。

- request.timeout.ms：这个就是说发送一个请求出去之后，他有一个超时的时间限制，默认是30秒，如果30秒都收不到响应，那么就会认为异常，会抛出一个TimeoutException来让我们进行处理

- acks参数，其实是控制发送出去的消息的持久化机制的
  - 如果acks=0，那么producer根本不管写入broker的消息到底成功没有，发送一条消息出去，立马就可以发送下一条消息，这是吞吐量最高的方式，但是可能消息都丢失了，你也不知道的，但是说实话，你如果真是那种实时数据流分析的业务和场景，就是仅仅分析一些数据报表，丢几条数据影响不大的
    会让你的发送吞吐量会提升很多，你发送弄一个batch出，不需要等待人家leader写成功，直接就可以发送下一个batch了，吞吐量很大的，哪怕是偶尔丢一点点数据，实时报表，折线图，饼图
  - acks=all，或者acks=-1：这个leader写入成功以后，必须等待其他ISR中的副本都写入成功，才可以返回响应说这条消息写入成功了，此时你会收到一个回调通知
    min.insync.replicas = 2，ISR里必须有2个副本，一个leader和一个follower，最最起码的一个，不能只有一个leader存活，连一个follower都没有了
  - acks = -1，每次写成功一定是leader和follower都成功才可以算做成功，leader挂了，follower上是一定有这条数据，不会丢失
    retries = Integer.MAX_VALUE，无限重试，如果上述两个条件不满足，写入一直失败，就会无限次重试，保证说数据必须成功的发送给两个副本，如果做不到，就不停的重试，除非是面向金融级的场景，面向企业大客户，或者是广告计费，跟钱的计算相关的场景下，才会通过严格配置保证数据绝对不丢失
  - acks=1：只要leader写入成功，就认为消息成功了，默认给这个其实就比较合适的，还是可能会导致数据丢失的，如果刚写入leader，leader就挂了，此时数据必然丢了，其他的follower没收到数据副本，变成leader

# 参考
- [http://kafka.apache.org/](http://kafka.apache.org/)
- [kafka document 官方文档](http://kafka.apache.org/22/documentation.html)

- [Kafka+Replication](https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Replication)
- [The Log: What every software engineer should know about real-time data's unifying abstraction](https://engineering.linkedin.com/distributed-systems/log-what-every-software-engineer-should-know-about-real-time-datas-unifying)
- [How to choose the number of topics/partitions in a Kafka cluster?](https://www.confluent.io/blog/how-choose-number-topics-partitions-kafka-cluster)

- [How to Support More Queues in RocketMQ?](http://rocketmq.apache.org/rocketmq/how-to-support-more-queues-in-rocketmq/) 对比分析了RocketMq和kafka，解释为什么要写RocketMq。由于Kafka是分partition的，所以写入是分散的，很难利用Linux IO Group Commit 机制
### 细节是魔鬼
- [一文看懂Kafka消息格式的演变](https://blog.csdn.net/u013256816/article/details/80300225)
- [为了追求极致的性能，Kafka掌控这11项要领](https://blog.csdn.net/u013256816/article/details/93772377) 。从11个点描述kafka的设计细节，包括日志格式，批处理，消息压缩，客户端优化，分区，顺序写盘，页缓存（不清楚这一点的代码实现逻辑，如何匹配OS的缓存机制呢？）等。
- [Kafka水位(high watermark)与leader epoch的讨论](https://www.cnblogs.com/huxi2b/p/7453543.html)
- [Linux IO磁盘篇整理小记](https://blog.csdn.net/u013256816/article/details/78945085) 
- [TimingWheel 时间轮详解](https://www.jianshu.com/p/0f0fec47a0ad)