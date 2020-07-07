package c.p.t.learning;

/**
 * @ClassName Test
 * @Description TODO
 * @Author User
 * @DATE 2020/4/29 16:23
 * @Version 1.0
 **/
public class RepeatableLock implements Runnable{

    public int ass = 2;

    public String name;

    public synchronized void get() {
        System.out.println(Thread.currentThread().getName());

    }

    public synchronized void set() {
        String threadName = Thread.currentThread().getName();
        ass = 5;
        System.out.println(threadName);
        get();
    }

    @Override
    public void run() {
        set();
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new RepeatableLock());
        t1.setName("线程1");
        t1.start();

        Thread t2 = new Thread(new RepeatableLock());
        t2.setName("线程2");
        t2.start();

        Thread t3 = new Thread(new RepeatableLock());
        t3.setName("线程3");
        t3.start();
    }
}
