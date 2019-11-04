
# bigdata

## 01_hadoop hdfs从0基础入门到精通源码

## 02_hadoop源码二次开发、系统优化以及问题修复

## 03_支撑每日10亿数据量的Kafka架构实战 
主要业务数据： 用户行为， 业务变更， 竞争对手数据（爬虫）

- 2019-9-2 1-19 **done**
- 2019-9-3 20-43 **done**
  - 024_高水位机制可能导致leader切换时发生数据丢失问题
  - 025_高水位机制可能导致leader切换时发生数据不一致问题, **高版本通过 leader epoch 避免该问题**。
- 2019-9-4 44-50
- 2019-9-4 51-59
- 2019-9-5 60-70
- 2019-9-6 71-78
- 2019-9-6 79-87
- 2019-9-8 88-100
  - 096_基于Java_API写一个Kafka_Consumer消费消息的代码示例 由于消费offset是保存在内存中，定期提交，如果机器宕机，很可能重复消费相同的数据。
- 2019-9-9 101-122
  - 109_如何实现Consumer Group的状态机流转机制？
- 2019-9-10 122-125
  - 122_Kafka的时间轮延时调度机制（一）：**比较经典的算法， 很多框架组件都有自己的实现**
- 2019-9-10 126-128
## 04_探索世界上最复杂的消息系统：Kafka内核源码分析
- 2019-9-11 1-15
- 2019-9-11 16-29
  - 022_是否有必要现在就对底层的网络通信组件深入分析？ **底层网络通讯比较复杂，包括请求的发送和接收，同步和异步等，研究起来非常耗时。所以暂时先弄清楚流程。后期有时间了再详细研究这部分。**
  - 23，24， 分区，采用了google的 Murmurhash 算法计算可以的hash值。
  - 026_大致浏览一下源码中将消息写入内存缓冲的运行流程。很重要的流程。
  org/apache/kafka/clients/producer/KafkaProducer.java:791
- 2019-9-13 30-36 讲解 producer 的源码实现, 比较难.


## 05_大数据架构师能力模型、职业规划以及年薪展望

## 06_大型电商数据化运营平台项目需求分析

## 07_每日TB量级的数据采集平台架构实战
> 关键词：canal, flume, openFalcon, Prometheus, Grafana, kafka, HDFS
- 2019-10-24 1-50
- 2019-10-23 71-80
- 2019-10-30 54-65
   
  - 54 一般hadoop都是用cdh版本。
  - 55 源码结构：node 是程序入口。 
    - source 开启事务提交event到channel
    - MemoryChannel ， 只要一个事务提交，就会把数据提交到全局的队列中
    - FileChannel 工作原理，**看源码事务是如何实现的**，commit写入一个commit对象，强制fsync，清空内存，维护写入参数。rollback，写入一个rollback对象，并不是删除文件内容。每次写入的event都会有关联的transactionId，如果没有这个Id commit或者rollback，估计也会认为这个事务没有提交。
    - SpillableMemoryChannel  官方不建议在生产环境使用，只能在实验环境。所以如果要在生产环境使用，需要自己开发。
    - KafkaSink 如果提交事务之前有异常，可能会向kafka中提交重复的数据。
    
  - 76 canal parse 伪装成 MySQL slave 拉取数据。初始化parse，dbsync负责具体的解析 binlog。
  - 高可用需要满足两点
    - 1 canal 本身的高可用，元数据都存储在zk
    - 2 canal 高可用。需要自己实现。kafka 如果挂掉，canal暂时把文件写入到磁盘，等kafka 恢复再继续些kafka 
  - 7 **OpenFalcon** 监控 flume 和 canal 
  - 12 MySQL binlog 同一条记录可能存在多个记录，增删改，当这部分数据导入到HDFS之后，每天需要用 spark 对数据进行清洗，最后处理后的数据才是最终的数据。
  - 13 canal 如何支持分库分表。需要把分散的表聚合成一张逻辑表，这需要二次开发。 
  - 19 flume 核心原理，读官方文档。
  - 32 sink processor 高阶功能。
                      可以把多个sink组成一个组，可以配置load balance，failover等策略。
                      如果是agent直连，可以在第一层的sink配置group，load balance 到第二层的多个source，实现负载均衡。
  - 34 interceptor 对source数据打标签，增加属性，修改source数据等。也可以自己实现定制逻辑。
  - 35 jvm 参数 ， 生产环境一定要配置。
  - 38 内置的 metrics, 不同的source， channel， sink 支持的监控统计信息不同。可以把监控统计发送到统计系统上，如 openFalcon。
  - 监控内容， 机器，jvm，磁盘，网络，metrics。
  - 40 高可用架构， 需要定制source，在些kafka失败时，些本地磁盘，并且监听kafka如果恢复，继续往kafka写数据。
       对与数据是否可丢失，不同的业务不同。业务数据不能丢，用户行为日志肯能丢一点不要紧。
  - 49 介绍很多开元监控平台, canal 官方支持 Prometheus。
  
### canal
- server
- instance 
- parse binlog 解析模块
- sink 数据加工处理模块
- store 数据存储模块
- meta 元数据维护模块
- canal protocol ： canal 客户端订阅 

## 08_ZooKeeper顶尖高手课程：从实战到源码
> 关键词： zookeeper
>  netflix, 雅虎， linkedin, facebook, google, 亚马逊， 这些公司开元的项目质量还是比较高的。

- 2019-10-23 21-37
- 2019-10-28 38-57
- 2019-11-3  58-67
- 2019-11-4  68-75

  - **34 为运维人员提供的运维工具**
  - 36 开启 JMX ，修改启动脚本中的 ZOMAIN，可连接远程zk。 
  
  - 一般使用3.4.5版本。 
  - zk 核心功能CRUD+节点类型+ 监听+回调，给予核心功能可以封装成很多高阶的功能，如选举，分布式锁等。
  - 机器配置， 最好是8c/16g，打内存最好用G1，设置期望停顿时间, gc日志和崩溃dump都要配置，在业务高峰期需要观察gc情况，如果有问题需要及时更新。最好接入监控系统。
  - 内存数据快照，定期些快照
  - 集群配置，2888：3888， 3888是集群选举使用的端口，2888是客户端和集群间同步数据使用的。
  - 提高读 qps，增加 observer 节点 
  - curator 是最好用的客户端包。是对原生客户端的封装，使用更方便。
    - 看源码，可以彻底弄明白客户端一般怎么玩，可以看懂通过zk原生api封装高阶api，可以看懂开源框架如何使用zk的，明白他的封装逻辑。
    - leader
    - barrier
    - doubleBarrier
    - counter. 实际上用 redis 更好，吞吐量更高
    - 子节点监听，pathCache, nodeCache, treeCache。这个功能很重要，协调通知全靠他。
      - pathCache, zk原生的监听在使用一次后会失效，需要重新注册，但是curator会重新注册监听，会继续收到实践。只能监听一级child的增删改，孙子节点的变化不会收到通知。
      - nodeCache
      - treeCache
    
  核心参数（[官网有参数说明](http://zookeeper.apache.org/doc/r3.5.6/zookeeperAdmin.html)）：
  - tickTime，基本时间单位，
  - dataDir
  - dataLogDIr
  - initLimit 
  - syncLimit leader 和 flower 的同步时间
  - snapCount 10w个事务写快照，一般不需要修改
  - maxClientCnxns ： 相同的客户端ip默认60个链接。也是为了防止DDOS攻击。一个应用server最好只启动一个客户端链接zk，
  - jute.maxbuffer ： 一个znode最多存储1mb数据，一般zk存储的数据都是配置信息，不要太大。
  - autopurge.snapRetainCount,autopurge.purgeInterval 开启定时清理快照文件和事务文件，默认不开启。
  - forceSync： commit时，强制刷新数据到磁盘，最好开启
  leader 相关
  - leaderServes： 配置laeder是否接受客户端链接
  - cnxTimeout ： 选举超市时间， 默认 5s
  
### 运维工具与使用  