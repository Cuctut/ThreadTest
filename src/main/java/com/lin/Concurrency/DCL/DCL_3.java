package com.lin.Concurrency.DCL;

import net.jcip.annotations.NotThreadSafe;

/**
 * 使用下面 DCL 写法；
 * 每次调用 getInstance 方法不是进行同步，而是用锁将初始化的步骤同步起来；
 * 当两个线程同时访问方法时都判空，于是竞争锁，第一个竞争到的线程完成实例的初始化并返回，
 * 后来的线程会在锁的代码段里再次判断是否有实例，从而决定是否初始化实例。
 *
 * 很不幸，下面代码在编译器优化、多处理器共享内存的情况下，并不能正常工作。最典型的例子是：
 * 在 new 一个实例对象时正常的步骤一般是：申请内存空间 -> 在内存空间初始化对象相关的内容 -> 返回内存空间地址
 * 在编译器优化后 new 一个对象的步骤则是：申请内存空间 -> 返回内存空间地址 -> 在内存空间初始化对象相关的内容
 * 假设线程 A 先拿到锁并执行创建对象的语句，然而在编译器优化后，先返回内存空间地址，导致对象内的内容还没填充好，
 * 此时线程 B 再拿到锁，判断此对象非空，它会直接拿去用而不关心里面的内容是否已经填充好了，可能会读取到空的字符串
 *
 * 我们可以通过锁机制或者 volatile 来保证有序性。
 */

@NotThreadSafe
public class DCL_3 {
    private LazyInitClass instance;
    public synchronized LazyInitClass getInstance() {
        if(instance == null){
            synchronized (this){
                if(instance == null)
                    instance = new LazyInitClass("我填充进去了");
            }
        }
        return instance;
    }
}
