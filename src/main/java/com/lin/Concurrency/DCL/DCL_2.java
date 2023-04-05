package com.lin.Concurrency.DCL;

import net.jcip.annotations.NotThreadSafe;

/**
 * 使用 synchronized 关键字；
 * 每次调用 getInstance 方法都进行同步，可以避免多线程环境下多次调用 getInstance 得到不同的对象。
 * 但当 instance 初始化完成后，多个线程每次访问都要同步，同步成为影响 getInstance 性能的关键。
 * 有没有一种方法，可以在初始化时进行正确的同步，初始化完成后又避免同步呢？于是 DCL 出现了。
 */

@NotThreadSafe
public class DCL_2 {
    private LazyInitClass instance ;
    public synchronized LazyInitClass getInstance() {
        if(instance == null)
            instance = new LazyInitClass("LazyInitClassFieldName") ;
        return instance ;
    }
}
