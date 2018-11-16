package demo1;

/**
 * 如要理解不仅要看代码理解  还要注意看字节码的变化
 */
public class Demo {
    public static void main(String[] args) {
        /* synchronized (this) --> 类的实例对象加锁 */
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        A a = new A();
//        for (int i = 0; i < 20; i++) {
//            executorService.execute(a);
//        }
        /* 反证代码 */
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        for (int i = 0; i < 20; i++) {
//            executorService.execute(new A());
//        }

        /* ----------------------------------------1------------------------------------------ */

        /* synchronized (B.class) --> 类对象加锁 */
//        for (int i = 0; i < 20; i++) {
//            Thread thread = new Thread(new B());
//            thread.start();
//        }
        /* 无反正代码 是给类级别加的锁,比较重型 */

        /* ----------------------------------------2------------------------------------------ */

        /* synchronized (object) --> Object实例对象的加锁 */
//        CTest cTest = new CTest(1000);
//        for (int i = 0; i < 10; i++) {
//            new Thread(new C(cTest)).start();
//        }
        /* 反证方式  将run中的synchronized去掉运行试试就知道了 */

        /* ----------------------------------------3------------------------------------------ */

        /* public synchronized void a() {} --> 类对象的实例加锁 */
//        D d = new D();
//        for (int i = 0; i < 20; i++) {
//            new Thread(d).start();
//        }

        /* ----------------------------------------4------------------------------------------ */

        /* public static synchronized void a() {} --> 类对象加锁 */
//        for (int i = 0; i < 20; i++) {
//            new Thread(new E()).start();
//        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// synchronized (this) --> 类的实例对象加锁
class A implements Runnable {
    private static int i = 0;

    @Override
    public void run() {
        synchronized (this) {
            i++;
            System.out.println(i);
        }
    }
}

// synchronized (B.class) --> 类对象加锁
class B implements Runnable {
    private static int i = 0;

    @Override
    public void run() {
        synchronized (B.class) {
            i++;
            System.out.println(i);
        }
    }
}

// synchronized (object) --> Object实例对象的加锁
class C implements Runnable {
    private CTest cTest;

    C(CTest cTest) {
        this.cTest = cTest;
    }

    @Override
    public void run() {
        synchronized (cTest) {
            cTest.add(10);
            cTest.subtract(10);
            System.out.println(cTest.getA());
        }
    }
}

class CTest {
    CTest(int a) {
        this.a = a;
    }

    private int a;

    void add(int v) {
        a += v;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void subtract(int v) {
        a -= v;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    int getA() {
        return a;
    }
}

// public synchronized void a() {} --> 类对象的实例加锁
class D implements Runnable {
    private synchronized void a() {
        System.out.println("a方法开始");
        for (int i = 0; i < 20; i++) {
            System.out.println("普通a:" + i);
        }
        System.out.println("a方法结束");
    }

    private void a1() {
        System.out.println("a的对比方法开始");
        for (int i = 0; i < 20; i++) {
            System.out.println("a的对比:" + i);
        }
        System.out.println("a的对比a方法结束");
    }

    @Override
    public void run() {
        a();
//        a1();
    }
}

// public static synchronized void a() {} --> 类对象加锁
class E implements Runnable {
    private static synchronized void a() {
        System.out.println("a方法开始");
        for (int i = 0; i < 20; i++) {
            System.out.println("普通a:" + i);
        }
        System.out.println("a方法结束");
    }

    private static void a1() {
        System.out.println("a的对比方法开始");
        for (int i = 0; i < 20; i++) {
            System.out.println("a的对比:" + i);
        }
        System.out.println("a的对比a方法结束");
    }

    @Override
    public void run() {
//        a();
        a1();
    }
}