
# jvm 调优到底调的是什么？
# 调优指标
- Maximum Pause Time Goal 最大停顿时间:

- Throughput Goal 吞吐量: 

he throughput goal is measured in terms of the time spent collecting garbage and the time spent outside of garbage collection (referred to as application time). The goal is specified by the command-line option -XX:GCTimeRatio=<nnn>. The ratio of garbage collection time to application time is 1 / (1 + <nnn>). For example, -XX:GCTimeRatio=19 sets a goal of 1/20th or 5% of the total time for garbage collection. 

The time spent in garbage collection is the total time for both the young generation and old generation collections combined. If the throughput goal is not being met, then the sizes of the generations are increased in an effort to increase the time that the application can run between collections.
- Footprint Goal 内存占用: 

If the throughput and maximum pause time goals have been met, then the garbage collector reduces the size of the heap until one of the goals (invariably the throughput goal) cannot be met. The goal that is not being met is then addressed.

# jvm 分代

## old 区对象的来源
- ⼀个对象在年轻代⾥躲过15次垃圾回收，年龄太⼤了，寿终正寝，进⼊⽼年代
- 对象太⼤了，超过了⼀定的阈值，直接进⼊⽼年代，不⾛年轻代
- ⼀次Young GC过后存活对象太多了，导致Survivor区域放不下了，这批对象会进⼊⽼年代
- 可能⼏次Young GC过后，Surviovr区域中的对象占⽤了超过50%的内存，此时会判断如果年龄1+年龄2+年龄N的对象总和超过了
- Survivor区域的50%，此时年龄N以及之上的对象都进⼊⽼年代，这是动态年龄判定规则

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