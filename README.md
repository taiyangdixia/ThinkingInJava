# 《Java编程思想》阅读时的一些代码以及读书笔记。


## Chapter 9	接口
- 接口的访问修饰符必须为public，默认是包访问控制权限。接口中的方法声明默认为public。
- 实现接口中的方法必须声明为public，不能不写访问控制符。


## Chapter 21	并发
**让步**

 Thread.yield():在run()方法中对其调用是对线程调度器的一种*建议*声明。表明“我已经执行完声明周期中最重要的部分了，此刻正式切换给其他任务执行一段时间的大好时机”。

**后台线程**
必须在线程start之前调用setDeamon()方法，才能将线程设置为后台线程。

` Thread deamon = new Thread(new SimpleTask());
    deamon.setDeamon(true);
    deamon.start(); `
     
**volatile**
基本上如果一个域可能会被多个任务同时访问，或者这些任务中至少有一个是写入任务，就该将这个域设置为volatile的。读取和写入这个域都是针对内存，没有被缓存。

**Synchronized关键字**
不属于方法特征签名的组成部分，所以可以在覆盖方法的时候加上去。

**管道**
- PipedWriter类：允许向管道写
- PipedReader类：允许不同任务从同一个管道中读取
- 管道模型可以看做“生产者-消费者”问题的变体。类似同步队列方式，内部已经自己实现同步。
- 管道IO和普通IO的区别：IO可以被中断，interrupt()，而interrupt()不能打断普通IO。

**生产者-消费者问题**
- 自己编程实现同步
- 利用同步队列BlockingQueue实现
- 利用管道PipedWriter、PipedReader实现

**死锁的必要条件**
- 互斥条件
- 请求与保持
- 不剥夺条件
- 环路等待条件
*要发生死锁的话，所有这些条件必须全部满足；要防止死锁的话，只要破坏其中一个即可。*

   *@evashi*
