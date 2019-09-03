
# jvm 调优到底调的是什么？
# 调优指标
- Maximum Pause Time Goal 最大停顿时间:

- Throughput Goal 吞吐量: 

he throughput goal is measured in terms of the time spent collecting garbage and the time spent outside of garbage collection (referred to as application time). The goal is specified by the command-line option -XX:GCTimeRatio=<nnn>. The ratio of garbage collection time to application time is 1 / (1 + <nnn>). For example, -XX:GCTimeRatio=19 sets a goal of 1/20th or 5% of the total time for garbage collection. 

The time spent in garbage collection is the total time for both the young generation and old generation collections combined. If the throughput goal is not being met, then the sizes of the generations are increased in an effort to increase the time that the application can run between collections.
- Footprint Goal 内存占用: 

If the throughput and maximum pause time goals have been met, then the garbage collector reduces the size of the heap until one of the goals (invariably the throughput goal) cannot be met. The goal that is not being met is then addressed.

# jvm 分代

# gc 触发时机

##　minor gc

## old gc

## full gc

# 垃圾回收器

## CMS

## G1 垃圾回收期详解

## 参考
- [内存分页大小对性能的提升原理]https://blog.csdn.net/chluknight/article/details/6689323
- [JVM performance optimization, Part 1: A JVM technology primer](https://www.javaworld.com/article/2078623/core-java-jvm-performance-optimization-part-1-a-jvm-technology-primer.html)
- [JVM performance optimization, Part 2: Compilers](https://www.javaworld.com/article/2078635/jvm-performance-optimization-part-2-compilers.html)
- [JVM performance optimization, Part 3: Garbage collection](https://www.javaworld.com/article/2078645/jvm-performance-optimization-part-3-garbage-collection.html)

- [JVM 垃圾回收器工作原理及使用实例介绍](https://www.ibm.com/developerworks/cn/java/j-lo-JVMGarbageCollection/)
- [GC root](https://www.yourkit.com/docs/java/help/gc_roots.jsp) yourkit , 一个监控工具.
- [Java 核心技术专题](https://www.ibm.com/developerworks/cn/java/coretech/java-vm.html)