package demo7;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 异步逻辑的实现 Java 9 有相关的改进
public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        // CompletableFuture 异步逻辑   Vue的Promise逻辑与其类似  Spring中可以使用@Async实现异步功能
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }, es);
        completableFuture.thenApply(e -> {
            System.out.println("异步调用执行完成![第一个Apply]： " + e);
            return e;
        }).thenApply(e -> {
            System.out.println("异步调用执行完成![第二个Apply]： " + e);
            return "123";
        }).thenAccept(e -> {
            System.out.println("异步调用执行完成![第一个Accept]: " + e);
        }).thenAccept(e -> {
            System.out.println("异步调用执行完成![第二个Accept]: " + e);
        });

        // 更多实用方法可以查阅API

        es.shutdown();
        System.out.println("主线程执行完成");
    }
}