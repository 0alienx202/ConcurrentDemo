package demo3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// CountDownLatch API https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/CountDownLatch.html
public class Demo {
    public static void main(String[] args) {
//        ExecutorService pool = Executors.newCachedThreadPool();
//        SimpleBlockDemo test = new SimpleBlockDemo();
//        for (int i = 0; i < 10; i++) {
//            pool.execute(test);
//        }


        final CountDownLatch cdOrder = new CountDownLatch(1);
        final CountDownLatch cdAnswer = new CountDownLatch(3);

        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            service.execute(new CountdownLatchDemo(cdOrder, cdAnswer));
        }

        try {
            Thread.sleep((long) (Math.random() * 10000));

            System.out.println("线程" + Thread.currentThread().getName() + "即将发布命令");
            cdOrder.countDown();
            System.out.println("线程" + Thread.currentThread().getName() + "已发送命令，正在等待结果");
            cdAnswer.await();
            System.out.println("线程" + Thread.currentThread().getName() + "已收到所有响应结果");
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}

/**
 * 将所有线程堵起来 当达到某个条件时全部放开
 */
class SimpleBlockDemo implements Runnable {
    private final AtomicInteger number = new AtomicInteger();
    private volatile boolean bol = false;

    @Override
    public void run() {
        System.out.println(number.getAndIncrement());
        synchronized (this) {
            try {
                if (!bol) {
                    System.out.println(bol);
                    bol = true;
                    Thread.sleep(10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("并发数量为" + number.intValue());
        }
    }
}

/**
 * 使用CountdownLatch作为开关进行模拟并发
 */
class CountdownLatchDemo implements Runnable {
    private CountDownLatch cdOrder;
    private CountDownLatch cdAnswer;

    CountdownLatchDemo(CountDownLatch cdOrder, CountDownLatch cdAnswer) {
        this.cdOrder = cdOrder;
        this.cdAnswer = cdAnswer;
    }

    @Override
    public void run() {
        try {
            System.out.println("线程" + Thread.currentThread().getName() + "正准备接受命令");
            cdOrder.await();
            System.out.println("线程" + Thread.currentThread().getName() + "已接受命令");
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("线程" + Thread.currentThread().getName() + "回应命令处理结果");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cdAnswer.countDown();
        }
    }
}