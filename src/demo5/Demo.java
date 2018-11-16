package demo5;

public class Demo {
    /* 类Annotation描述
     *
     * @ThreadSafe 表示这个类是线程安全的 具体是否真安全,那要看实现者怎么实现的了,反正打上这个标签只是表示一下 不线程安全的类打上这个注解也没事儿
     * @Immutable 表示类是不可变的,包含了 @ThreadSafe 的意思
     * @NotThreadSafe 表示这个类不是线程安全的>如果是线程安全的非要打上这个注解,那也不会报错
     */

    /* 域Annotation和方法Annotation
     *
     * 描述哪个状态变量被哪个锁保护着,以及哪个锁保护这些变量的信息:
     * @GuardedBy(lock):线程只有在持有了一个特定的锁(lock)后,才能访问某个域或方法;
     * @GuardedBy("this"):包换在对象中的内部锁(方法或域是这个对象的一个成员);
     * @GuardedBy("fieldName"):值与filedName引用的对象相关联的锁,或者是一个隐式锁(filedName没有引用一个Lock),或者是一个显示锁(filedName引用了一个Lock);
     * @GuardedBy("ClassName.fieldName"):类似于@GuardedBy("fieldName"),不过所引用的锁对象是存储在另一个类(或本类)中的静态域;
     * @GuardedBy("methodName()"):锁对象是methodName()方法的返回值;
     * @GuardedBy("ClassName.class"):ClassName类的直接量对象.
     */

    // 可能由于包的原因 无法直接使用这些注释 所以先只做记录 我个人反正是理解了
}

