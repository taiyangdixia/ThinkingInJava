# 《Java编程思想》阅读时的一些代码以及读书笔记。


## Chapter 9	接口
- 接口的访问修饰符必须为public，默认是包访问控制权限。接口中的方法声明默认为public。
- 实现接口中的方法必须声明为public，不能不写访问控制符。

**接口的继承extends**
- 只能将extends应用在单一类，但是可以引用多个接口。

```
//这种引用多个接口的继承方式仅适用于接口的继承
interface a{}
interface b{}
interface c extends a,b{}
```
## Chapter 10	内部类
**.this**


## Chapter 20	注解
**Java内置标准注解**
- @Override：覆盖超类中的方法
- @Deprecated：编译器发出警告信息
- @SuppressWarnings：关闭不当的警告信息

**Java内置四种元注解**
- @Target：表示该注解可以用在什么地方，可用ElementType指定。
- @Retention：表示需要在什么级别保存该注解信息。用RetentionPolicy指定。SOURCE、CLASS、RUNTIME。
- @Documented：将此注解包含在Javadoc中。
- @Inherited：允许子类继承父类中的注解。

**注解元素**

```
example:
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
  public int id();
  public String description() default "no description";
}

```
<hr>

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

**新类库中的构件**
- CountDownLatch 向CountDownLatch对象设置一个初始计数值，任何在这个对象上调用await()的方法都将阻塞，直至这个计数值达到0。
- CyclicBarrier
- DelayQueue
- PriorityBlockingQueue Producer:queue.add(new blala); Consumer:queue.take().run() 无需显式同步
- ScheduledThreadPoolExecutor schedule方法：运行一次任务；scheduleAtFixedRate()方法：每隔规则的时间重复执行任务。
- Semaphore 允许n个任务同时访问资源,资源池
- Exchanger 两个任务之间交换对象的栅栏。生产者和消费者各持有一个对象，在两个对象都完成Exchanger.exchange(obj)时，两个对象的obj可以交换。



   *@evashi*
