package com.lin.Thread;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 如何优雅地停止一个线程
 * 不要用即将被弃用的 stop() 方法，而是改用 interrupt()
 */
public class stopThreadsGracefully {

    /**
     * 锁的作用是保证线程安全。
     * static final 是 Java 中的常量，它们的值在编译时就已经确定了，因此它们不会被修改。
     * 在多线程环境下，如果多个线程同时访问一个对象的锁，那么这些线程就会互相竞争，从而导致线程不安全。
     * 因此，为了保证线程安全，我们需要使用 static final 来定义锁，这样就可以保证锁的值不会被修改。
     * 这样一来，多个线程就可以共享同一个锁对象，从而保证线程安全。
     */
    public static final Object lock1 = new Object();
    public static final ReentrantLock lock2 = new ReentrantLock();

    /**
     * 在 t1 线程中用 lock1 锁执行一段 for 循环，在执行三秒后主线程用 stop() 停止 t1；
     * 在 t1 停止后有另一段代码竞争这个锁（打印 end）
     * 结果：锁被释放，end 被打印
     */
    @Test
    public void Test01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock1){
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        t1.start();
        Thread.sleep(3000);
        t1.stop();

        synchronized (lock1){
            System.out.println("end");
        }
    }

    /**
     * 在 t2 线程中用 lock2 锁执行一段 for 循环，在执行三秒后主线程用 stop() 停止 t2；
     * 在 t2 停止后有另一段代码竞争这个锁（打印 end）
     * 结果：锁无法被释放，end 无法被打印（阻塞状态）
     */
    @Test
    public void Test02() throws InterruptedException {
        Thread t2 = new Thread(() -> {
            lock2.lock();
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            lock2.unlock();
        });

        t2.start();
        Thread.sleep(3000);
        t2.stop();

        lock2.lock();
        System.out.println("end");
        lock2.unlock();
    }

    /**
     * 这段代码尝试用 interrupt() 方法中断线程。
     * 在 t3 中的 for 循环内判断当前此线程是否被中断（后面跟的 and 条件可以不要），
     * 但是可能会遇到刚好在 sleep 时中断而抛出异常的情况，可以删除抛出异常再测试。
     * 删除后测试会发现在 sleep 时中断会让 isInterrupted() 重新设置为 false。
     * @throws InterruptedException
     */
    @Test
    public void Test03() throws InterruptedException {
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {

                if (Thread.currentThread().isInterrupted() && i > 50){
                    break;
                }

                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
                }
            }

        });

        t3.start();
        Thread.sleep(3000);
        t3.interrupt();

        System.out.println("end");

    }

    @Test
    public void Test04() throws InterruptedException {

        Thread t4 = new Thread(() -> {
            boolean isStop = false;
            for (int i = 0; i < 100; i++) {

                if (isStop && i > 50){
                    break;
                }

                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
//                    throw new RuntimeException(e);
                    isStop = true;
                }
            }

        });

        t4.start();
        Thread.sleep(3000);
        t4.interrupt();
        System.out.println("end");

    }

    public static void main(String[] args) throws InterruptedException {
        stopThreadsGracefully stopThreadsGracefully = new stopThreadsGracefully();
        stopThreadsGracefully.Test01();
        stopThreadsGracefully.Test02();
        stopThreadsGracefully.Test03();
        stopThreadsGracefully.Test04();
    }
}
