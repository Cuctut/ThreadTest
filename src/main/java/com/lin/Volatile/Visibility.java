package com.lin.Volatile;

import org.junit.Test;

/**
 * ����ע�͵��� Thread.sleep()
 * ���߳̽��� WAITING ʱ�����߳��л���ˢ�¹����ڴ棬�᳢��ˢ�´ﵽ��һ�ַ�ʽ��ͬ��
 * �ڴ˻��ƻ�����
 */
public class Visibility {

    /**
     * ��δ����ڴ��� myThread ��ʱ��������̵߳Ĺ����ڴ��ڱ�����һ�� myThread �ĸ���
     * ��� myThread �����е� flag �� false �ģ���ʹ myThread ���߳�����޸�flag��
     * ����Ӱ�����̵߳Ĺ����ڴ棨�̵߳Ĺ����ڴ滥���ɼ��������Դ�ӡ��������
     */
    @Test
    public void test01() throws InterruptedException {
        System.out.println("�� test01 ��");

        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("��In mainThread�� myThread flag = " + myThread.isFlag());

        for (;;){
//            Thread.sleep(100);
            if (myThread.isFlag()) {
                System.out.println("��In mainThread�� myThread flag = " + myThread.isFlag());
            }
//            else {
//                System.out.println("��In mainThread�� myThread flag = " + myThread.isFlag());
//            }
        }
    }

    /**
     * Ϊɶ�������Խ���ɼ��������أ�
     * ��Ϊĳһ���߳̽��� synchronized �����ǰ��
     * �̻߳�������
     *     ���Ȼ���չ����ڴ棬
     *     �����ڴ濽������������µ�ֵ�������ڴ��Ϊ������
     *     ִ�д��룬
     *     ���޸ĺ�ĸ�����ֵˢ�»����ڴ��У�
     * �߳��ͷ�����
     * ����ȡ���������̻߳������ȴ������Ա�����ֵ�϶�һֱ�������µġ�
     */
    @Test
    public void test02() throws InterruptedException {
        System.out.println("�� test02 ��");

        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("��In mainThread�� myThread flag = " + myThread.isFlag());

//        Object lock = new Object();
        for (;;) {
//            Thread.sleep(100);
//            synchronized (lock){
//                if (myThread.isFlag()) {
//                    System.out.println("��In mainThread�� myThread flag = " + myThread.isFlag());
//                }
//            }
            synchronized (myThread){
                if (myThread.isFlag()) {
                    System.out.println("��In mainThread�� myThread flag = " + myThread.isFlag());
                }
                else {
                    System.out.println("��In mainThread�� myThread flag = " + myThread.isFlag());
                }
            }
        }
    }

    /**
     * ���߳�����ʹ�� volatile �ؼ������� flag
     *
     * ÿ���̲߳������ݵ�ʱ�������ݴ����ڴ��ȡ���Լ��Ĺ����ڴ棬
     * ��������������ݲ���д���ˣ��������Ѿ���ȡ���̵߳ı��������ͻ�ʧЧ�ˣ�
     * ��Ҫ�����ݽ��в�����Ҫ�ٴ�ȥ���ڴ��ж�ȡ�ˡ�
     *
     * volatile��֤��ͬ�̶߳Թ�����������Ŀɼ��ԣ�
     * Ҳ����˵һ���߳��޸���volatile���εı��������޸�д�����ڴ�ʱ������һ���߳������������µ�ֵ��
     *
     * MESI������һ����Э�飩��
     * ��CPUд����ʱ��������ֲ����ı����ǹ����������������CPU��Ҳ���ڸñ����ĸ�����
     * �ᷢ���ź�֪ͨ����CPU���ñ����Ļ�������Ϊ��Ч״̬����˵�����CPU��Ҫ��ȡ�������ʱ��
     * �����Լ������л���ñ����Ļ���������Ч�ģ���ô���ͻ���ڴ����¶�ȡ��
     * ÿ��������ͨ����̽�������ϴ���������������Լ������ֵ�ǲ��ǹ����ˣ�
     * �������������Լ������ж�Ӧ���ڴ��ַ���޸ģ��ͻὫ��ǰ�������Ļ��������ó���Ч״̬��
     * ����������������ݽ����޸Ĳ�����ʱ�򣬻����´�ϵͳ�ڴ��а����ݶ��������������
     *
     * ���� Volatile �� MESI ����һ����Э�飬��Ҫ���ϵĴ����ڴ���̽�� cas ����ѭ����
     * ��Ч�����ᵼ�����ߴ���ﵽ��ֵ�����Բ�Ҫ����ʹ�� Volatile��
     */
    @Test
    public void test03() throws InterruptedException {
        System.out.println("�� test03 ��");

        MyVolatileThread myVolatileThread = new MyVolatileThread();
        myVolatileThread.start();

        System.out.println("��In mainThread�� myVolatileThread flag = " + myVolatileThread.isFlag());

        for (;;) {
//            Thread.sleep(100);
            if (myVolatileThread.isFlag()) {
                System.out.println("��In mainThread�� myVolatileThread flag = " + myVolatileThread.isFlag());
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Visibility visibility = new Visibility();
//        visibility.test01();
//        visibility.test02();
        visibility.test03();
    }
}
