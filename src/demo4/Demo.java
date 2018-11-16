package demo4;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer的 普通线程和守护线程 使用方式
 * Timer的执行机制 (队列形式,顺序执行) 如果前一个任务耗时超长 则下一个任务不一定能按照准确时间执行
 */
public class Demo {
    /* Timer作为一个新的线程存在运行 */
    private static Timer timer = new Timer();

    /* Timer作为一个守护线程存在运行 */
//    private static Timer timer = new Timer(true);

    public static void main(String[] args) {
        /* 执行机制的测试 */
//        timer.schedule(new MyTaskA(), Date.from(LocalDateTime.of(2018, 11, 15, 14, 38, 50).toInstant(ZoneOffset.of("+8"))));
//        timer.schedule(new MyTask(), Date.from(LocalDateTime.of(2018, 11, 15, 14, 38, 51).toInstant(ZoneOffset.of("+8"))));

        /* 循环执行示例 */
        // 当开始时间小于当前时间也会执行
        timer.schedule(new MyTaskB(), Date.from(LocalDateTime.of(2018, 11, 15, 15, 39, 0).toInstant(ZoneOffset.of("+8"))), 1000);

        /* 销毁全部任务 多线程存在锁争抢,不一定会按顺序执行 */
//        timer.cancel();

        /* scheduleAtFixedRate方法需要自己测试一下 */
//        timer.scheduleAtFixedRate();
    }
}

class MyTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("运行了!时间是:" + LocalTime.now());
    }
}

/**
 * 延时任务
 */
class MyTaskA extends TimerTask {
    @Override
    public void run() {
        System.out.println("耗时的任务开始运行了!时间是:" + LocalTime.now());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("耗时任务运行结束!");
    }
}

/**
 * 取消任务
 */
class MyTaskB extends TimerTask {
    @Override
    public void run() {
        System.out.println("运行了!时间是:" + LocalTime.now());
        this.cancel();
    }
}


