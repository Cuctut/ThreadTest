package com.lin.Concurrency.DCL;

import net.jcip.annotations.ThreadSafe;

/**
 * 如果 LazyInitClass 对象是不可变对象，则不使用 volatile 关键字 DCL 也能正常工作（code 1.3 所示）。
 * 这是由 Java 内存模型中，final 域的特殊语义保证的：final 域能确保初始化过程的安全性，
 * 从而可以不受限制地访问不可变对象，并在共享这些对象时无须同步。
 */

@ThreadSafe
public class DCL_6 {
    private ImmutableLazyInitClass instance;
    public synchronized ImmutableLazyInitClass getInstance() {
        if(instance == null){
            synchronized (this){
                if(instance == null)
                    instance = new ImmutableLazyInitClass("我填充进去了");
            }
        }
        return instance;
    }
}
