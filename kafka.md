# 工作原理

- 高吞吐，低延迟： 顺序写盘，追加到文件末尾；利用os cache缓存；
- 零拷贝， page cache , 利用linux sendFile 机制。

## 写数据原理

## 读数据原理

# 参考
- [http://kafka.apache.org/](http://kafka.apache.org/)