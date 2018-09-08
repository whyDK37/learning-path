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

## 编程语言
### [Haskell](https://www.haskell.org/)
一种函数式编程语言, 如果对学习新的语言或者函数式编程, 递归编程感兴趣, 可以研究一下.

### Java

学习 Java 语言有以下入门级的书（注意：下面一些书在入门篇中有所提及，但为了完整性，还是要在这里提一下，因为可能有朋友是跳着看的）。

- 基础
  - [java 编程思想](https://book.douban.com/subject/2130190/)
  - [JAVA 2核心技术 卷Ⅰ](https://book.douban.com/subject/1781451/)
  - [Effective java](https://book.douban.com/subject/3360807/)
- 底层知识

#### 其他资料
- [awesome-java](https://github.com/akullpp/awesome-java) - A curated list of awesome Java frameworks, libraries and software.
- [Regular Expression Matching Can Be Simple And Fast (but is slow in Java, Perl, PHP, Python, Ruby, ...)](https://swtch.com/~rsc/regexp/regexp1.html) - This is a tale of two approaches to regular expression matching. One of them is in widespread use in the standard interpreters for many languages, including Perl. The other is used only in a few places, notably most implementations of awk and grep. The two approaches have wildly different performance characteristics:
- [在线分析GC gceasy](http://gceasy.io/)

### [Golang](https://golang.org/)
- [Go in Action](https://book.douban.com/subject/25858023/)
- [Go语言高级进阶篇](https://blog.csdn.net/column/details/gosenior.html)

## 编程模型/范式
### I/O模型

#### 阻塞I/O
#### 非阻塞I/O
#### I/O 的多路复用( select 和 poll )
#### 信号驱动的 I/O (SIGIO)
#### 异步 I/O ( POSIX 的 aio_functions)

### Lock-Free 编程


### 函数式编程
- [Why Functional Programming Matters](http://www.cse.chalmers.se/~rjmh/Papers/whyfp.html)

## 数据结构
- [6.851: Advanced Data Structures (Fall'17)](http://courses.csail.mit.edu/6.851/fall17/lectures/) - MIT 高级数据结构课程.[youtube 地址](https://www.youtube.com/playlist?list=PLUl4u3cNGP61hsJNdULdudlRL493b-XZf)
- [数据结构动画网站](https://www.cs.usfca.edu/~galles/visualization/Algorithms.html) - 以动画的方式展示数据结构, 非常直观.

## Algorithm 算法
- 基础知识
  - [图解算法](https://book.douban.com/subject/26979890/) - 比较生动的一本算法书
    - 练习题
      - 6.3 请问下面的三个列表哪些可行、哪些不可行？
  - [算法](https://book.douban.com/subject/10432347/) - 算法领域的经典参考书 , 给出了50个程序员应该知道的算法. 深入浅出的算法介绍, 让一些比较难的算法也比较容易理解, 尤其是书中对红黑树的讲解非常精彩. 缺点是不深入, 缺少算法设计内容. 甚至动态规划都未提及.
  [官网](https://algs4.cs.princeton.edu/home/) 
  - [编程珠玑](https://book.douban.com/subject/26302533/) - 
  - [算法导论](https://book.douban.com/subject/1885170/) - 不太好肯的一本书, 详细介绍了算法的推导过程, 需要些数学功底.
  
- 算法设计思想
  - divide and conquer，D&C 分而治之
  - 动态规划 Dynamic programming(hard to learn)
  - 回溯 back tracing
  - 分治 Divide and Conquer
  
- 算法实例
  - [List_of_algorithms](https://www.wikiwand.com/en/List_of_algorithms) - 罗列了很多算法, 完全可以当做算法字典, 或用来开阔眼界.
  - 快速排序(D&C) 
  - [蚂蚁算法](https://baike.baidu.com/item/%E8%9A%81%E7%BE%A4%E7%AE%97%E6%B3%95/9646604?fromtitle=%E8%9A%82%E8%9A%81%E7%AE%97%E6%B3%95&fromid=9585934)
  - 广度优先搜索（breadth-first search，BFS）. 
    - [Topological Sorting | 拓扑排序](https://www.wikiwand.com/en/Topological_sorting)
    
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
## 数据库
### Mysql
- 书籍
  - [High Performance MySQL, 3rd Edition](https://book.douban.com/subject/10443458/) 

## 分布式系统


## 机器学习


### 资料
- 基本算法
- 论文
  - [Algorithm = Logic + Control](https://www.doc.ic.ac.uk/~rak/papers/algorithm%20=%20logic%20+%20control.pdf) - 算法是逻辑+控制
  - [Algorithms and Data Structures ](http://www.ethoberon.ethz.ch/WirthPubl/AD.pdf) - 算法和数据结构. 综合这两篇论文, 可以得到公式: 程序=数据结构+逻辑+控制

## 资料集散地
### 博客
- [medium](https://medium.com/) 文章的集散地
- [cool shell](https://coolshell.cn)
- [杰夫·阿特伍德（Jeff Atwood）](https://blog.codinghorror.com/)  
  - https://blog.codinghorror.com/code-tells-you-how-comments-tell-you-why/
- https://www.joelonsoftware.com/
- http://blog.cleancoder.com/ 是編程大师 Bob大叔的博客, 其真名叫 Robert C. Martin, 世界级软件开发大师, 设计模式和敏捷开发先驱.
- https://martinfowler.com/ Marting 主要专注面向对象分析和设计, 统一建模语言, 领域建模, 以及敏捷软件开发方法.
- http://www.paulgraham.com/articles.html
- [think in java 作者的博客](http://bruceeckel.github.io/)
- [dzone](https://dzone.com/)
​  
### 其他
- [编程语言排行](https://www.tiobe.com/tiobe-index/)
- [The Key To Accelerating Your Coding Skills](http://blog.thefirehoseproject.com/posts/learn-to-code-and-be-self-reliant/) - When you learn to code, there is a moment when everything begins to change. At Firehose, we like to call this the inflection point of coding. After this phase, the way you operate as a developer will be dramatically different. Building up to the inflection point is the process of becoming self-sufficient in programming, to the point where you no longer need any hand-holding. It can be a frustrating experience, but once it’s behind you, it is incredibly empowering. 
- [Teach Yourself Programming in Ten Years](http://norvig.com/21-days.html)
- [awesome-java](https://github.com/akullpp/awesome-java)  - A curated list of awesome Java frameworks, libraries and software. 
- [How To Ask Questions The Smart Way](http://www.catb.org/~esr/faqs/smart-questions.html)
- [What-are-some-of-the-most-basic-things-every-programmer-should-know](https://www.quora.com/What-are-some-of-the-most-basic-things-every-programmer-should-know)

## 软技能
### 面试技巧

## 工具
- [youtube 视频下载](https://qdownloader.net/), [youtube 字幕下载](http://mo.dbxdb.com/setting.html)