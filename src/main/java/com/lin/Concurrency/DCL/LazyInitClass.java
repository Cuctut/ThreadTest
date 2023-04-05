package com.lin.Concurrency.DCL;

import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class LazyInitClass {
    private String lazyInitClassField = "�һ�û�����";
    public LazyInitClass(String lazyInitClassField) {
        this.lazyInitClassField = lazyInitClassField ;
        System.out.println(this.lazyInitClassField);
    }
}
