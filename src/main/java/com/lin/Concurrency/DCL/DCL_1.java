package com.lin.Concurrency.DCL;

import net.jcip.annotations.NotThreadSafe;

/**
 * Double Check Lock �Ƕ��̻߳�����Ϊ����ӳٳ�ʼ��Ч�ʶ����㷺ʹ�õ�һ�ַ�ʽ��
 * ���ǳ�����ʹ���ӳٳ�ʼ�����Խ��ͷ�������ʱ�䡣
 *
 * ����Ĵ����ǵ��͵��ӳٳ�ʼ�������ӡ�����������ӱ�¶�ڶ��̻߳�����ʱ��
 * �����ָ������⡣�����ԵĴ��󣺵�����߳�ͬʱ���� getInstance ������
 * ��ͬʱ�пգ����ض�� LazyInitClass ����
 */

@NotThreadSafe
public class DCL_1 {
    private LazyInitClass instance ;
    public LazyInitClass getInstance() {
        if(instance == null)
            instance = new LazyInitClass("LazyInitClassFieldName") ;
        return instance ;
    }
}
