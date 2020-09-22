package demo8;

import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Timer + TimerTask
//        Timer timer = new Timer();
//        timer.schedule(new TempC(), 1000); // 1000毫秒后执行
//        timer.schedule(new TempC(), 1000, 3000); // 1000毫秒后执行 每隔3秒执行一次

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

        ses.scheduleAtFixedRate(() -> {
            System.out.println("TimerTask");
        }, 1, 1, TimeUnit.SECONDS);

        System.out.println("主线程执行完成");

    }
}

// Runnable的实现类
class TempC extends TimerTask {

    @Override
    public void run() {
        System.out.println("TimerTask");
    }
}