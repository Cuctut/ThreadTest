package com.lin.Concurrency.DCL;

import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class ImmutableLazyInitClass {
    private final String lazyInitClassField ;
    public ImmutableLazyInitClass(String lazyInitClassField) {
        this.lazyInitClassField = lazyInitClassField ;
        System.out.println(this.lazyInitClassField);
    }
}
