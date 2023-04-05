package com.lin.Concurrency.DCL;

import net.jcip.annotations.ThreadSafe;

/**
 * DCL ���������
 * ���ַ�ʽ����Ϊ�ӳٳ�ʼ��ռλ��ģʽ���� Java ���屣֤��
 * ֻ�е����� getInstance ������LazyInitClassHolder.singleton �Żᱻ��ʼ�������Դ˷�ʽ��������� DCL��
 *
 * DCL ��ʹ�÷�ʽ�Ѿ����㷺������
 * DCL ֮���Գ�������Ϊ�޾���ͬ����ִ���ٶȺ������Լ� JVM �����������������������Ѿ��������ڣ�
 * �����������һ�ָ�Ч���Ż���ʩ���ӳٳ�ʼ��ռλ��ģʽ�ܴ�����ͬ�����ƣ�����������⡣
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
