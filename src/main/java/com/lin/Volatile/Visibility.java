package com.lin.Volatile;

import org.junit.Test;

/**
 * 别用注释掉的 Thread.sleep()
 * 当线程进入 WAITING 时会让线程有机会刷新工作内存，会尝试刷新达到另一种方式的同步
 * 在此会破坏案例
 */
public class Visibility {

    /**
     * 这段代码在创建 myThread 的时候会在主线程的工作内存内保留了一个 myThread 的副本
     * 这个 myThread 副本中的 flag 是 false 的，即使 myThread 子线程如何修改flag，
     * 都不影响主线程的工作内存（线程的工作内存互不可见），所以打印不出东西
     */
    @Test
    public void test01() throws InterruptedException {
        System.out.println("【 test01 】");

        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("【In mainThread】 myThread flag = " + myThread.isFlag());

        for (;;){
//            Thread.sleep(100);
            if (myThread.isFlag()) {
                System.out.println("【In mainThread】 myThread flag = " + myThread.isFlag());
            }
//            else {
//                System.out.println("【In mainThread】 myThread flag = " + myThread.isFlag());
//            }
        }
    }

    /**
     * 为啥加锁可以解决可见性问题呢？
     * 因为某一个线程进入 synchronized 代码块前后：
     * 线程会获得锁，
     *     首先会清空工作内存，
     *     从主内存拷贝共享变量最新的值到工作内存成为副本，
     *     执行代码，
     *     将修改后的副本的值刷新回主内存中，
     * 线程释放锁。
     * 而获取不到锁的线程会阻塞等待，所以变量的值肯定一直都是最新的。
     */
    @Test
    public void test02() throws InterruptedException {
        System.out.println("【 test02 】");

        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("【In mainThread】 myThread flag = " + myThread.isFlag());

//        Object lock = new Object();
        for (;;) {
//            Thread.sleep(100);
//            synchronized (lock){
//                if (myThread.isFlag()) {
//                    System.out.println("【In mainThread】 myThread flag = " + myThread.isFlag());
//                }
//            }
            synchronized (myThread){
                if (myThread.isFlag()) {
                    System.out.println("【In mainThread】 myThread flag = " + myThread.isFlag());
                }
                else {
                    System.out.println("【In mainThread】 myThread flag = " + myThread.isFlag());
                }
            }
        }
    }

    /**
     * 在线程类中使用 volatile 关键字修饰 flag
     *
     * 每个线程操作数据的时候会把数据从主内存读取到自己的工作内存，
     * 如果他操作了数据并且写回了，他其他已经读取的线程的变量副本就会失效了，
     * 需要都数据进行操作又要再次去主内存中读取了。
     *
     * volatile保证不同线程对共享变量操作的可见性：
     * 也就是说一个线程修改了volatile修饰的变量，当修改写回主内存时，另外一个线程立即看到最新的值。
     *
     * MESI（缓存一致性协议）：
     * 当CPU写数据时，如果发现操作的变量是共享变量，即在其他CPU中也存在该变量的副本，
     * 会发出信号通知其他CPU将该变量的缓存行置为无效状态，因此当其他CPU需要读取这个变量时，
     * 发现自己缓存中缓存该变量的缓存行是无效的，那么它就会从内存重新读取。
     * 每个处理器通过嗅探在总线上传播的数据来检查自己缓存的值是不是过期了，
     * 当处理器发现自己缓存行对应的内存地址被修改，就会将当前处理器的缓存行设置成无效状态，
     * 当处理器对这个数据进行修改操作的时候，会重新从系统内存中把数据读到处理器缓存里。
     *
     * 由于 Volatile 的 MESI 缓存一致性协议，需要不断的从主内存嗅探和 cas 不断循环，
     * 无效交互会导致总线带宽达到峰值。所以不要大量使用 Volatile，
     */
    @Test
    public void test03() throws InterruptedException {
        System.out.println("【 test03 】");

        MyVolatileThread myVolatileThread = new MyVolatileThread();
        myVolatileThread.start();

        System.out.println("【In mainThread】 myVolatileThread flag = " + myVolatileThread.isFlag());

        for (;;) {
//            Thread.sleep(100);
            if (myVolatileThread.isFlag()) {
                System.out.println("【In mainThread】 myVolatileThread flag = " + myVolatileThread.isFlag());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Visibility visibility = new Visibility();
//        visibility.test01();
//        visibility.test02();
        visibility.test03();
    }
}
