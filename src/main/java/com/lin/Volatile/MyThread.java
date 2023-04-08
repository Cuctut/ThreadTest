package com.lin.Volatile;

public class MyThread extends Thread{
    private boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {
        System.out.println("��In myThread�� origin flag = " + flag);

        try {
            System.out.println("��In myThread�� sleep 1 second");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        flag = true;
        System.out.println("��In myThread�� now flag = " + flag);
    }
}
