package com.lin.Concurrency.DCL;

import net.jcip.annotations.NotThreadSafe;

/**
 * ʹ�� synchronized �ؼ��֣�
 * ÿ�ε��� getInstance ����������ͬ�������Ա�����̻߳����¶�ε��� getInstance �õ���ͬ�Ķ���
 * ���� instance ��ʼ����ɺ󣬶���߳�ÿ�η��ʶ�Ҫͬ����ͬ����ΪӰ�� getInstance ���ܵĹؼ���
 * ��û��һ�ַ����������ڳ�ʼ��ʱ������ȷ��ͬ������ʼ����ɺ��ֱ���ͬ���أ����� DCL �����ˡ�
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
