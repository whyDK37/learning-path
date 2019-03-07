# learning-path
学习路径

## 基础知识
- 系统
  - [《深入理解计算机系统》，《Computer System A programmer`s prespective](https://book.douban.com/subject/26912767/) - 程序员需要了解的计算机知识. 描述了计算机系统的实现细节, 帮助在大脑中构建一个层次型的计算机. 这是程序员必读的一本书.

- 网络
  - [TCP 的那些事儿（上）](https://coolshell.cn/articles/11564.html)
  - [TCP 的那些事儿（下）](https://coolshell.cn/articles/11609.html) 状态机, 拥塞控制中心, windows 滑动窗口
  - [图解TCP/IP](https://book.douban.com/subject/24737674/) - 快速入门书籍
  - [C10K 问题](http://www.kegel.com/c10k.html)
    - [The Secret To 10 Million Concurrent Connections -The Kernel Is The Problem, Not The Solution](http://highscalability.com/blog/2013/5/13/the-secret-to-10-million-concurrent-connections-the-kernel-i.html)
## Linux 系统

### 命令/工具
- sed  https://www.geeksforgeeks.org/sed-command-in-unix/

### 文章/博客
- [Learn Linux, 101: System logging](https://developer.ibm.com/tutorials/l-lpic1-108-2/)
- [How to use IP Command in Linux with Examples](https://linoxide.com/linux-command/use-ip-command-linux/)

## 编程语言
### [Haskell](https://www.haskell.org/)
一种函数式编程语言, 如果对学习新的语言或者函数式编程, 递归编程感兴趣, 可以研究一下.

### Java
学习 Java 语言有以下入门级的书（注意：下面一些书在入门篇中有所提及，但为了完整性，还是要在这里提一下，因为可能有朋友是跳着看的）。

- 基础
  - [java 编程思想](https://book.douban.com/subject/2130190/)
  - [JAVA 2核心技术 卷Ⅰ](https://book.douban.com/subject/1781451/)
  - [Effective java](https://book.douban.com/subject/3360807/)
- 标准
  - [Java Community Process](https://jcp.org/en/home/index) Java 标准（jsr）网站
- 底层知识
  - Java Memory Model(JMM)
    - [JSR 133 (Java Memory Model) FAQ](https://www.cs.umd.edu/~pugh/java/memoryModel/jsr-133-faq.html)
  - [Understanding Java JIT Compilation with JITWatch, Part 1](https://www.oracle.com/technetwork/articles/java/architect-evans-pt1-2266278.html)
  
#### 其他资料
- [awesome-java](https://github.com/akullpp/awesome-java) - A curated list of awesome Java frameworks, libraries and software.
- [Regular Expression Matching Can Be Simple And Fast (but is slow in Java, Perl, PHP, Python, Ruby, ...)](https://swtch.com/~rsc/regexp/regexp1.html) - This is a tale of two approaches to regular expression matching. One of them is in widespread use in the standard interpreters for many languages, including Perl. The other is used only in a few places, notably most implementations of awk and grep. The two approaches have wildly different performance characteristics:
- [在线分析GC gceasy](http://gceasy.io/)

### [Golang](https://golang.org/)
- [go github](https://github.com/golang/go)
  - [go users](https://github.com/golang/go/wiki/GoUsers)
- [Go in Action](https://book.douban.com/subject/25858023/)
- [Go语言高级进阶篇](https://blog.csdn.net/column/details/gosenior.html)

## 编程模型/范式

### I/O模型
  I/O模型可以分为以下几类:
- 阻塞I/O
- 非阻塞I/O
- I/O 的多路复用( select 和 poll )
- 信号驱动的 I/O (SIGIO)
- 异步 I/O ( POSIX 的 aio_functions)

- [Java I/O 模型的演进](https://waylau.com/java-io-model-evolution/)
- [Boost application performance using asynchronous I/O](https://www.ibm.com/developerworks/library/l-async/) ibm Boost application performance using asynchronous I/O

### Lock-Free 编程


### 编程范式
- [Programming paradigm](https://en.wikipedia.org/wiki/Programming_paradigm) wiki
- [Programming paradigm](http://cs.lmu.edu/~ray/notes/paradigms/) A programming paradigm is a style, or “way,” of programming.
- [Six programming paradigms that will change how you think about coding](https://www.ybrikman.com/writing/2014/04/09/six-programming-paradigms-that-will/) [中文翻译](https://my.oschina.net/editorial-story/blog/890965)
- [Programming Paradigms for Dummies: What Every Programmer Should Know](https://www.info.ucl.ac.be/~pvr/VanRoyChapter.pdf)
- [Why Functional Programming Matters](http://www.cse.chalmers.se/~rjmh/Papers/whyfp.html)

## 数据结构
- [ring buffer](https://en.wikipedia.org/wiki/Circular_buffer) A ring showing, conceptually, a circular buffer. This visually shows that the buffer has no real end and it can loop around the buffer. However, since memory is never physically created as a ring, a linear representation is generally used as is done below. A circular buffer, circular queue, cyclic buffer or ring buffer is a data structure that uses a single, fixed-size buffer as if it were connected end-to-end. This structure lends itself easily to buffering data streams.
- [6.851: Advanced Data Structures (Fall'17)](http://courses.csail.mit.edu/6.851/fall17/lectures/) - MIT 高级数据结构课程.[youtube 地址](https://www.youtube.com/playlist?list=PLUl4u3cNGP61hsJNdULdudlRL493b-XZf)
- [数据结构动画网站](https://www.cs.usfca.edu/~galles/visualization/Algorithms.html) - 以动画的方式展示数据结构, 非常直观.

## Algorithm 算法
### 基础知识
  - [图解算法](https://book.douban.com/subject/26979890/) - 比较生动的一本算法书
    - 练习题
      - 6.3 拓扑排序 Topological Sorting, 请问下面的三个列表哪些可行、哪些不可行？
  - [算法(第四版)](https://book.douban.com/subject/10432347/) - 算法领域的经典参考书 , 给出了50个程序员应该知道的算法. 深入浅出的算法介绍, 让一些比较难的算法也比较容易理解, 尤其是书中对红黑树的讲解非常精彩. 缺点是不深入, 缺少算法设计内容. 甚至动态规划都未提及.
  [官网](https://algs4.cs.princeton.edu/home/)
  - [编程珠玑](https://book.douban.com/subject/26302533/) -
  - [算法导论](https://book.douban.com/subject/1885170/) - 不太好肯的一本书, 详细介绍了算法的推导过程, 需要些数学功底.

### 算法设计思想
  - divide and conquer，D&C 分而治之
  - 动态规划 Dynamic programming(hard to learn)
  - 回溯 back tracing
  - 分治 Divide and Conquer

- 算法实例
  - [List_of_algorithms](https://www.wikiwand.com/en/List_of_algorithms) - 罗列了很多算法, 完全可以当做算法字典, 或用来开阔眼界.
  - 快速排序(D&C)
  - [蚂蚁算法](https://baike.baidu.com/item/%E8%9A%81%E7%BE%A4%E7%AE%97%E6%B3%95/9646604?fromtitle=%E8%9A%82%E8%9A%81%E7%AE%97%E6%B3%95&fromid=9585934)
  - 广度优先搜索（breadth-first search，BFS）.
    - [Topological Sorting | 拓扑排序](https://www.wikiwand.com/en/Topological_sorting) In the field of computer science, a topological sort or topological ordering of a directed graph is a linear ordering of its vertices such that for every directed edge uv from vertex u to vertex v, u comes before v in the ordering.

    使用广度优先搜索可以：
    1. 编写国际跳棋AI，计算最少走多少步就可获胜；
    2. 编写拼写检查器，计算最少编辑多少个地方就可将错拼的单词改成正确的单词，如将READED改为READER需要编辑一个地方；
    3. 根据你的人际关系网络找到关系最近的医生。
    4. 第一类问题：从节点A出发，有前往节点B的路径吗？（在你的人际关系网中，有芒果销售商吗？）
    5. 第二类问题：从节点A出发，前往节点B的哪条路径最短？（哪个芒果销售商与你的关系最近？）

- 基础算法题, 其中有大量的算法题，解这些题都是有套路的
  - 耗子的"理论学科"中的介绍
  - 递归, 深度优先DFS, 广度优先BFS
  - 折半查找 Binary search
  - 大量的对树, 数组, 链表, 字符串, hash表的操作.
  - 蓄水池算法, 统计过去1分钟P99
  - 服务调度的背包问题
  - 红黑树

### 资料
- [leetcode](https://leetcode.com/) - 一个算法在线练习网站
  - [陈浩的 leetcode](https://github.com/haoel/leetcode)

### 论文
  - [Algorithm = Logic + Control](https://www.doc.ic.ac.uk/~rak/papers/algorithm%20=%20logic%20+%20control.pdf) - 算法是逻辑+控制
  - [Algorithms and Data Structures ](http://www.ethoberon.ethz.ch/WirthPubl/AD.pdf) - 算法和数据结构. 综合这两篇论文, 可以得到公式: 程序=数据结构+逻辑+控制  
## 数据库
### Mysql
- 书籍
  - [High Performance MySQL, 3rd Edition](https://book.douban.com/subject/10443458/)

## 分布式系统
### 理论
- [CAP定理](https://zh.wikipedia.org/wiki/CAP%E5%AE%9A%E7%90%86)  掘进上的[分布式理论(一) - CAP定理](https://juejin.im/post/5b26634b6fb9a00e765e75d1)
### 规范
- [opentracing](https://opentracing.io/specification/) 分布式追踪语义规范，介绍了非语言相关的最终定义数据模型和机制。蚂蚁金服基于此标准实现了[分布式链路追踪中间件 SOFATracer SOFATracer](https://www.oschina.net/p/sofatracer)

## 软件设计
- 原则
  - [Don't repeat yourself](https://en.wikipedia.org/wiki/Don%27t_repeat_yourself)
  - [KISS principle](https://en.wikipedia.org/wiki/KISS_principle)
  - program to interface, not an implimentation
  - [You aren't gonna need it](http://en.wikipedia.org/wiki/You_Ain%27t_Gonna_Need_It)
  - [Law of Demeter | 迪米特法则,最少知识原则](https://en.wikipedia.org/wiki/Law_of_Demeter) 
  - [Solid](https://en.wikipedia.org/wiki/Solid)
  - [Liskov substitution principle](https://en.wikipedia.org/wiki/Liskov_substitution_principle) 里氏替换原则
  - [Internet service provider](https://en.wikipedia.org/wiki/Internet_service_provider) 接口隔离原则
  - [Dependency inversion principle](https://en.wikipedia.org/wiki/Dependency_inversion_principle) 依赖倒置原则
  - [Common Closure Principle](http://wiki.c2.com/?CommonClosurePrinciple) 共同封闭原则
  - [Common Reuse Principle | 共同重用原则](http://wiki.c2.com/?CommonReusePrinciple) 共同重用原则
  - [Principle of least astonishment | 最少惊诧原则](https://en.wikipedia.org/wiki/Principle_of_least_astonishment)
  - [Hollywood Principle](https://en.m.wikipedia.org/wiki/Hollywood_Principle) 好莱坞原则, "don`t call us, we`ll call you.". 是IOC, DI 的基础.[Inversion of Control Containers and the Dependency Injection pattern](https://martinfowler.com/articles/injection.html)
  - [High Cohesion](https://en.wikipedia.org/wiki/Cohesion_(computer_science)) & [Low/Loose coupling](https://en.m.wikipedia.org/wiki/Coupling_(computer_programming))  高内聚, 低耦合. [对于面向对象来说, 可以看看马塞诸塞州戈登学院的面向对象的一节讲义](http://www.math-cs.gordon.edu/courses/cs211/lectures-2009/Cohesion,Coupling,MVC.pdf)
  - [Convention over configuration](https://en.wikipedia.org/wiki/Convention_over_configuration) 惯例优于配置原则. 简单说, 就是将一些公认的配置方式和信息作为内部缺省规则来使用. 例如 Maven 的项目结构, Hibernate 的映射文件.
  - [Separation of concerns](https://en.wikipedia.org/wiki/Separation_of_concerns) 关注点分离. SoC 是计算机科学中最重要的努力目标之一. 这个原则是软件开发中, 通过各种手段, 将问题的各个关注点分开. 如果一个问题能分解为独立且较小的问题, 就是相对较容易解决的.
  - [Design by contract](https://en.m.wikipedia.org/wiki/Design_by_contract) 契约式设计. DbC 的核心思想是对软件系统中的元素之间相互合作以及责任和义务的比喻. 这种比喻从商业活动中客户与 供应商达成契约而来.如果在程序设计中一个模块提供了某种功能, 那么它要:
    1. 期望所有调用他的客户模块都保证一定的进入条件: 这就是模块的先验条件,
    2. 保证退出时给出特定的属性: 这就是模块的后验条件( 供应商的义务, 显然也是客户的权利)
    3. 在进入时假定, 并在退出时保证一些特定的属性: 不变式.
  - [Acyclic Dependencies Principle](http://wiki.c2.com/?AcyclicDependenciesPrinciple=) 无环依赖原则
  
- 书籍
  - [领域驱动设计](https://book.douban.com/subject/1629512/)
  - [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) 如果你读过 [Clean coode | 代码整洁之道](https://book.douban.com/subject/4199741/), [The Clean coder | 程序员的职业素养](https://book.douban.com/subject/11614538/) 都是出自 Bob 大叔 之手. Clean architecture 也是一本书, 这是一本很不错的架构类图书. 对软件架构的元素, 方法等讲的很清楚. 实例都比较简单, 并带一些软件变化历史的讲述, 可开阔视野.
  - [The Twelve-Factor App](https://12factor.net/)如今，软件通常会作为一种服务来交付，它们被称为网络应用程序，或软件即服务（SaaS）。12-Factor 为构建SaaS 应用提供了方法论，这也是架构师必读的文章。（[中译版](https://12factor.net/zh_cn/)） 这篇文章在业内的影响力很大，必读！
  - [How to Design a Good API & Why it Matters](https://www.infoq.com/presentations/effective-api-design)
## 机器学习


### 资料
- 基本算法

## 资料集散地
### 博客
- [Doug Lea's Home Page](http://gee.cs.oswego.edu/) Java 并发包的作者.
- [medium](https://medium.com/) 文章的集散地
- [cool shell](https://coolshell.cn)
- [杰夫·阿特伍德（Jeff Atwood）](https://blog.codinghorror.com/) [Stack Overflow](https://stackoverflow.com/) 网站创始人.
  - [高效能程序员的修炼](https://book.douban.com/subject/24868904/)
    - [Best Practices for Speeding Up Your Web Site](https://developer.yahoo.com/performance/rules.html)
    - [YSlow: Yahoo's Problems Are Not Your Problems](https://blog.codinghorror.com/yslow-yahoos-problems-are-not-your-problems/)
  - [程序员的修炼](https://book.douban.com/subject/25880845/)
  - [Effective Programming](https://book.douban.com/subject/11639780/)
  - https://blog.codinghorror.com/code-tells-you-how-comments-tell-you-why/
- https://www.joelonsoftware.com/
- http://blog.cleancoder.com/ 是編程大师 Bob大叔的博客, 其真名叫 Robert C. Martin, 世界级软件开发大师, 设计模式和敏捷开发先驱.
  - Bob 大叔的书籍
  - [程序员的职业素养](https://book.douban.com/subject/11614538/)
  - [代码整洁之道](https://book.douban.com/subject/4199741/)
- https://martinfowler.com/ Marting 主要专注面向对象分析和设计, 统一建模语言, 领域建模, 以及敏捷软件开发方法.
- http://www.paulgraham.com/articles.html
- [think in java 作者的博客](http://bruceeckel.github.io/)
- [dzone](https://dzone.com/)
​  
### 图书
- [软件随想录](https://book.douban.com/subject/4163938/)
- [Patterns of Enterprise Application Architecture | 企业应用架构模式](https://book.douban.com/subject/4826290/) 作者 [美] Martin Fowler , 
### 其他
- [编程语言排行](https://www.tiobe.com/tiobe-index/)
- [The Key To Accelerating Your Coding Skills](http://blog.thefirehoseproject.com/posts/learn-to-code-and-be-self-reliant/) - When you learn to code, there is a moment when everything begins to change. At Firehose, we like to call this the inflection point of coding. After this phase, the way you operate as a developer will be dramatically different. Building up to the inflection point is the process of becoming self-sufficient in programming, to the point where you no longer need any hand-holding. It can be a frustrating experience, but once it’s behind you, it is incredibly empowering.
- [Teach Yourself Programming in Ten Years](http://norvig.com/21-days.html)
- [awesome-java](https://github.com/akullpp/awesome-java)  - A curated list of awesome Java frameworks, libraries and software.
- [How To Ask Questions The Smart Way](http://www.catb.org/~esr/faqs/smart-questions.html)
- [What-are-some-of-the-most-basic-things-every-programmer-should-know](https://www.quora.com/What-are-some-of-the-most-basic-things-every-programmer-should-know)

## 工具

### git
- [Pro Git](https://git-scm.com/book/en/v2) 如果要了解一个版本管理工具的话, git是首选.
- [Git代码行统计命令集](https://blog.csdn.net/dwarven/article/details/46550117)

## 软技能
### 面试技巧

## 工具
- [youtube 视频下载](https://qdownloader.net/), [youtube 字幕下载](http://mo.dbxdb.com/setting.html)