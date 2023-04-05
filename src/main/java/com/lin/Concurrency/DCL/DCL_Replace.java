package com.lin.Concurrency.DCL;

import net.jcip.annotations.ThreadSafe;

/**
 * DCL 的替代方案
 * 这种方式被称为延迟初始化占位类模式，由 Java 语义保证：
 * 只有调用了 getInstance 方法后，LazyInitClassHolder.singleton 才会被初始化。所以此方式能完美替代 DCL。
 *
 * DCL 的使用方式已经被广泛废弃。
 * DCL 之所以出现是因为无竞争同步的执行速度很慢，以及 JVM 启动很慢。但这两个问题已经不复存在，
 * 因而它并不是一种高效的优化措施。延迟初始化占位类模式能带来相同的优势，并更容易理解。
 */

@ThreadSafe
public class DCL_Replace {
    private static class LazyInitClassHolder {
        static LazyInitClass singleton = new LazyInitClass("LazyInitClassFieldName");
    }

    public static LazyInitClass getInstance() {
        return LazyInitClassHolder.singleton ;
    }
}
