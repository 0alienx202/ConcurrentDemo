package demo2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock的多种实现 （只要展示ReentrantLock的使用方法）
 * ReentrantLock  该锁是通过限制并发数量闲置的
 *
 * ReentrantReadWriteLock 读-写锁 (本文不写示例)
 *      读写锁：分为读锁和写锁，多个读锁不互斥，读锁与写锁互斥，这是由JVM自己控制的，只要上好相应的锁即可
 *      用法: 读的时候上读锁，写的时候上写锁
 *      说明:
 *          线程进入读锁的前提条件：
 *              没有其他线程的写锁，
 *              没有写请求或者有写请求，但调用线程和持有锁的线程是同一个
 *          线程进入写锁的前提条件：
 *              没有其他线程的读锁
 *              没有其他线程的写锁
 */
public class Demo {
    private Lock lock = new ReentrantLock();

    private void method1(Thread thread) {
        lock.lock();
        try {
            System.out.println("线程名" + thread.getName() + "获得了锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("线程名" + thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    private void method2(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println("线程名" + thread.getName() + "获得了锁");
            } catch (Exception ignore) {
            } finally {
                System.out.println("线程名" + thread.getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println("我是" + Thread.currentThread().getName() + "有人占着锁，我就不要啦");
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> demo.method1(Thread.currentThread())).start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> demo.method2(Thread.currentThread())).start();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
