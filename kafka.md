# 工作原理

- 高吞吐，低延迟： 顺序写盘，追加到文件末尾；利用os cache缓存；
- 零拷贝， page cache , 利用linux sendFile 机制；
- 压缩日志文件；
- 集群存储， 多副本冗余；
- 数据不丢失，ISR机制, in sync replacation
- 请求负载均衡  
- 使用zk 让 kafka broker 实现无状态。

## 写数据原理

## 读数据原理

# 参考
- [http://kafka.apache.org/](http://kafka.apache.org/)