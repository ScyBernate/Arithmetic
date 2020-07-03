package c.p.t.geektime.tryWithResource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock implements AutoCloseable {

    public Lock lock;

    public MyLock(ReentrantLock lock){
        this.lock = lock;
    }

    public void lock(){
        lock.lock();
    }

    @Override
    public void close() throws Exception {
        lock.unlock();
        System.out.println("自动解锁");
    }

    public static void main(String[] args) {
        try(MyLock lock = new MyLock(new ReentrantLock(true));){
            lock.lock();
            System.out.println("锁住");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
