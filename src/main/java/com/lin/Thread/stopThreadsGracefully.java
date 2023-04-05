package com.lin.Thread;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ������ŵ�ֹͣһ���߳�
 * ��Ҫ�ü��������õ� stop() ���������Ǹ��� interrupt()
 */
public class stopThreadsGracefully {

    /**
     * ���������Ǳ�֤�̰߳�ȫ��
     * static final �� Java �еĳ��������ǵ�ֵ�ڱ���ʱ���Ѿ�ȷ���ˣ�������ǲ��ᱻ�޸ġ�
     * �ڶ��̻߳����£��������߳�ͬʱ����һ�������������ô��Щ�߳̾ͻụ�ྺ�����Ӷ������̲߳���ȫ��
     * ��ˣ�Ϊ�˱�֤�̰߳�ȫ��������Ҫʹ�� static final ���������������Ϳ��Ա�֤����ֵ���ᱻ�޸ġ�
     * ����һ��������߳̾Ϳ��Թ���ͬһ�������󣬴Ӷ���֤�̰߳�ȫ��
     */
    public static final Object lock1 = new Object();
    public static final ReentrantLock lock2 = new ReentrantLock();

    /**
     * �� t1 �߳����� lock1 ��ִ��һ�� for ѭ������ִ����������߳��� stop() ֹͣ t1��
     * �� t1 ֹͣ������һ�δ��뾺�����������ӡ end��
     * ����������ͷţ�end ����ӡ
     */
    @Test
    public void Test01() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (lock1){
                for (int i = 0; i < 100; i++) {
                    System.out.println(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        t1.start();
        Thread.sleep(3000);
        t1.stop();

        synchronized (lock1){
            System.out.println("end");
        }
    }

    /**
     * �� t2 �߳����� lock2 ��ִ��һ�� for ѭ������ִ����������߳��� stop() ֹͣ t2��
     * �� t2 ֹͣ������һ�δ��뾺�����������ӡ end��
     * ��������޷����ͷţ�end �޷�����ӡ������״̬��
     */
    @Test
    public void Test02() throws InterruptedException {
        Thread t2 = new Thread(() -> {
            lock2.lock();
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            lock2.unlock();
        });

        t2.start();
        Thread.sleep(3000);
        t2.stop();

        lock2.lock();
        System.out.println("end");
        lock2.unlock();
    }

    /**
     * ��δ��볢���� interrupt() �����ж��̡߳�
     * �� t3 �е� for ѭ�����жϵ�ǰ���߳��Ƿ��жϣ�������� and �������Բ�Ҫ����
     * ���ǿ��ܻ������պ��� sleep ʱ�ж϶��׳��쳣�����������ɾ���׳��쳣�ٲ��ԡ�
     * ɾ������Իᷢ���� sleep ʱ�жϻ��� isInterrupted() ��������Ϊ false��
     * @throws InterruptedException
     */
    @Test
    public void Test03() throws InterruptedException {
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {

                if (Thread.currentThread().isInterrupted() && i > 50){
                    break;
                }

                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
                }
            }

        });

        t3.start();
        Thread.sleep(3000);
        t3.interrupt();

        System.out.println("end");

    }

    @Test
    public void Test04() throws InterruptedException {

        Thread t4 = new Thread(() -> {
            boolean isStop = false;
            for (int i = 0; i < 100; i++) {

                if (isStop && i > 50){
                    break;
                }

                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException");
//                    throw new RuntimeException(e);
                    isStop = true;
                }
            }

        });

        t4.start();
        Thread.sleep(3000);
        t4.interrupt();
        System.out.println("end");

    }

    public static void main(String[] args) throws InterruptedException {
        stopThreadsGracefully stopThreadsGracefully = new stopThreadsGracefully();
        stopThreadsGracefully.Test01();
        stopThreadsGracefully.Test02();
        stopThreadsGracefully.Test03();
        stopThreadsGracefully.Test04();
    }
}
