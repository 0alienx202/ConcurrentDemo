package demo10;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// CyclicBarrier API https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/CyclicBarrier.html
public class Demo {
    static CyclicBarrier barrier = new CyclicBarrier(5, () -> {
        System.out.println("CyclicBarrier");
    });

    // 将线程分组执行，每当线程堆积达到栅栏位置时，就会集中通过一次，有点像机械化工具的意思
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 12; i++) {
            int fi = i;
            Thread.sleep(1000);
            executor.execute(() -> {
                try {
                    System.out.println(fi + " is ready");
                    if (fi == 0) Thread.sleep(1);

                    barrier.await();
                    System.out.println(fi + " continue");
                } catch (Exception ignore) {
                }
            });
        }
        executor.shutdown();
    }
}
