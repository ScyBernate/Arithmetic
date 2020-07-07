package c.p.t.learning;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Test
 * @Description TODO
 * @Author User
 * @DATE 2020/4/13 15:14
 * @Version 1.0
 **/
public class ConditionTest {

    public static ReentrantLock lock = new ReentrantLock();

    public static Condition condition = lock.newCondition();

    public void newThreadRun(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try{
                    System.out.println("newThreadRun lock");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionTest ct = new ConditionTest();
        ct.newThreadRun();

        TimeUnit.SECONDS.sleep(3);

        lock.lock();

    }

}
