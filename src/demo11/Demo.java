package demo11;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Condition api https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/locks/Condition.html
public class Demo {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("等待信号");
                // 可以看出这里暂时释放了锁
                condition.await();
                System.out.println("获取到一个信号");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("发送信号");
                condition.signalAll();
                System.out.println("信号发送成功");
            } finally {
                lock.unlock();
            }
        });

        thread1.start();
        thread2.start();
    }
}


