package Demo6;

import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        // Runnable
//        es.execute(new TempA());
//        es.submit(new TempA());

        // Callable + Future
//        Future<Integer> future = es.submit(new TempB());
//        System.out.println(future.get());

        // Callable + FutureTask    FutureTask 是 Future 的实现
//        FutureTask<Integer> futureTask = new FutureTask<>(new TempB());
//        es.submit(futureTask);
//        System.out.println(futureTask.get());

        es.shutdown();
        System.out.println("主线程执行完成");
    }
}

// Runnable 与 Callable 的区别

class TempA implements Runnable {

    @Override
    public void run() {
        System.out.println("Runnable");
    }
}

class TempB implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("Callable");
        return 1;
    }
}