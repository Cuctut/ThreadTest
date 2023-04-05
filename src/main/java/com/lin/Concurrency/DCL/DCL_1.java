package com.lin.Concurrency.DCL;

import net.jcip.annotations.NotThreadSafe;

/**
 * Double Check Lock 是多线程环境下为提高延迟初始化效率而被广泛使用的一种方式。
 * 我们常常会使用延迟初始化，以降低服务启动时间。
 *
 * 下面的代码是典型的延迟初始化的例子。当下面的例子暴露在多线程环境下时，
 * 便会出现各种问题。最明显的错误：当多个线程同时访问 getInstance 方法，
 * 会同时判空，返回多个 LazyInitClass 对象。
 */

@NotThreadSafe
public class DCL_1 {
    private LazyInitClass instance ;
    public LazyInitClass getInstance() {
        if(instance == null)
            instance = new LazyInitClass("LazyInitClassFieldName") ;
        return instance ;
    }
}
