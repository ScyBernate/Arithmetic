package c.p.t.learning;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName LockInstanceTest
 * @Description TODO
 * @Author User
 * @DATE 2020/4/14 10:13
 * @Version 1.0
 **/
public class LockInstanceTest {

    //消息队列
    public LinkedBlockingQueue<String> queue = new LinkedBlockingQueue(10);

    //生产者
    class Producer {

        //生产消息
        public void produce(String message) throws InterruptedException {
            synchronized (queue) {
                if (!queue.offer(message)) { // 队列满
                    System.out.println("produce queue has lock");
                    queue.wait();
                }
                System.out.println(new Date() + "---" + Thread.currentThread().getName() + "生产的消息：" + message);
                queue.notifyAll();
            }
        }

    }

    //消费者
    class Consumer {

        public synchronized void consume() throws InterruptedException {
            synchronized (queue) {
                String message = queue.poll();
                if (message == null) {
                    queue.wait();
                }
                System.out.println(new Date() + "---" + Thread.currentThread().getName() + "--消费到的消息：" + message);
                queue.notifyAll();
            }
        }

    }


    public static void main(String[] args) throws InterruptedException {
        LockInstanceTest lit = new LockInstanceTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
                        lit.new Producer().produce("消息" + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lit.new Consumer().consume();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}
