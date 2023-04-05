package com.lin.Concurrency.DCL;

import net.jcip.annotations.ThreadSafe;

/**
 * 使 DCL 正常工作；
 * JDK 1.5 以后（包含 JDK 1.5）的解决方案:
 * 从 JDK5 开始，Java Memory Model 升级，volatile 关键字便可以保证可见性与有序性。
 * 要使 DCL 正常工作，多了一种更为方便的解决方案
 */

@ThreadSafe
public class DCL_5 {
    private volatile LazyInitClass instance ;

    public LazyInitClass getInstance() {
        if(instance == null){
            synchronized(this){
                if(instance == null){
                    instance = new LazyInitClass("LazyInitClassFieldName") ;
                }
            }
        }
        return instance ;
    }
}
