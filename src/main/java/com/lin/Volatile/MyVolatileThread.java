package com.lin.Volatile;

public class MyVolatileThread extends Thread{
    private volatile boolean flag = false;

    public boolean isFlag() {
        return flag;
    }

    @Override
    public void run() {
        System.out.println("¡¾In myVolatileThread¡¿ origin flag = " + flag);

        try {
            System.out.println("¡¾In myVolatileThread¡¿ sleep 1 second");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        flag = true;
        System.out.println("¡¾In myVolatileThread¡¿ now flag = " + flag);
    }
}
