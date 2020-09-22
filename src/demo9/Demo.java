package demo9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// C 语言中也存在 Semaphore
// Semaphore 与 CountDownLatch 的区别在于 前者更像是交警适合用于流量管制，而后者更像是闸门一泻千里(见demo3)
public class Demo {
    //创建一个信号量的实例，信号量初始值为0
    static Semaphore semaphore = new Semaphore(0, false);

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.submit(() -> {
            System.out.println("Thread1---start");
            //信号量加一
            semaphore.release();
        });

        pool.submit(() -> {
            System.out.println("Thread2---start");
            //信号量加一
            semaphore.release();
        });

        pool.submit(() -> {
            System.out.println("Thread3---start");
            //信号量加一
            semaphore.release();
        });

        //等待两个子线程执行完毕就放过，必须要信号量等于2才放过
        semaphore.acquire(2);
        System.out.println("两个子线程执行完毕");

//        semaphore.acquire(N); // 获取N个许可
//        semaphore.release(N); // 释放N个许可

        //关闭线程池，正在执行的任务继续执行
        pool.shutdown();
    }
}
