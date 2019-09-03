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

# 参考
- [Kafka+Replication](https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Replication)
- [一文看懂Kafka消息格式的演变](https://blog.csdn.net/u013256816/article/details/80300225)
- [Kafka水位(high watermark)与leader epoch的讨论](https://www.cnblogs.com/huxi2b/p/7453543.html)
- [http://kafka.apache.org/](http://kafka.apache.org/)
- [kafka document 官方文档](http://kafka.apache.org/22/documentation.html)