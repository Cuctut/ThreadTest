package com.lin.Concurrency.DCL;

import net.jcip.annotations.ThreadSafe;

/**
 * ʹ DCL ����������
 * JDK 1.5 �Ժ󣨰��� JDK 1.5���Ľ������:
 * �� JDK5 ��ʼ��Java Memory Model ������volatile �ؼ��ֱ���Ա�֤�ɼ����������ԡ�
 * Ҫʹ DCL ��������������һ�ָ�Ϊ����Ľ������
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
