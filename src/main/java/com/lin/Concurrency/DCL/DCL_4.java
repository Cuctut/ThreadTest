package com.lin.Concurrency.DCL;

import net.jcip.annotations.ThreadSafe;

/**
 * 使 DCL 正常工作；
 * JDK 1.3 以前（不包含 JDK 1.3）的解决方案：由于 JDK1.2 版本 ThreadLocal 非常慢，
 * 所以并不推荐使用 ThreadLocal 解决 DCL 问题。JDK1.3 版本以前 DCL 并没有解决方案。
 * JDK 1.3 以后（包含 JDK 1.3）的解决方案如下：
 */

@ThreadSafe
public class DCL_4 {
    private final ThreadLocal perThreadInstance = new ThreadLocal();
    private LazyInitClass instance ;
    public LazyInitClass getInstance() {
        if (perThreadInstance.get() == null) createInstance();
        return instance;
    }
    private void createInstance() {
        synchronized(this) {
            if (instance == null)
                instance = new LazyInitClass("LazyInitClassFieldName");
        }
        perThreadInstance.set(perThreadInstance);
    }
}
