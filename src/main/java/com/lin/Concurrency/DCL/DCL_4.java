package com.lin.Concurrency.DCL;

import net.jcip.annotations.ThreadSafe;

/**
 * ʹ DCL ����������
 * JDK 1.3 ��ǰ�������� JDK 1.3���Ľ������������ JDK1.2 �汾 ThreadLocal �ǳ�����
 * ���Բ����Ƽ�ʹ�� ThreadLocal ��� DCL ���⡣JDK1.3 �汾��ǰ DCL ��û�н��������
 * JDK 1.3 �Ժ󣨰��� JDK 1.3���Ľ���������£�
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
