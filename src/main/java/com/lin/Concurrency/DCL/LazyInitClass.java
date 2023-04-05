package com.lin.Concurrency.DCL;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class LazyInitClass {
    private String lazyInitClassField = "我还没被填充";
    public LazyInitClass(String lazyInitClassField) {
        this.lazyInitClassField = lazyInitClassField ;
        System.out.println(this.lazyInitClassField);
    }
}
