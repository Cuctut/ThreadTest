package com.lin.Concurrency.DCL;

import net.jcip.annotations.ThreadSafe;

/**
 * ��� LazyInitClass �����ǲ��ɱ������ʹ�� volatile �ؼ��� DCL Ҳ������������code 1.3 ��ʾ����
 * ������ Java �ڴ�ģ���У�final ����������屣֤�ģ�final ����ȷ����ʼ�����̵İ�ȫ�ԣ�
 * �Ӷ����Բ������Ƶط��ʲ��ɱ���󣬲��ڹ�����Щ����ʱ����ͬ����
 */

@ThreadSafe
public class DCL_6 {
    private ImmutableLazyInitClass instance;
    public synchronized ImmutableLazyInitClass getInstance() {
        if(instance == null){
            synchronized (this){
                if(instance == null)
                    instance = new ImmutableLazyInitClass("������ȥ��");
            }
        }
        return instance;
    }
}
