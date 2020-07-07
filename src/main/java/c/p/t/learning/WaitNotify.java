package c.p.t.learning;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WaitNotify {

    LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

    class productor {

        public void product() throws InterruptedException {
            for (int i = 1; i < 10000; i++) {
                synchronized (queue) {
                    if (!queue.offer(i)) {
                        System.out.println("等待消费");
                        queue.wait();
                        System.out.println("继续执行");
                    }else{
                        System.out.println("生产第"+i+"条信息");
                    }
                    queue.notifyAll();
                }

            }
        }
    }

    class consumer {
        public void comsume() throws InterruptedException {
            TimeUnit.SECONDS.sleep(5);
            while (true) {
                synchronized (queue) {
                    Integer element = queue.poll();
                    System.out.println(element);
                    if (element == null) {
                        queue.wait();
                    }
                    queue.notifyAll();
                }
            }
        }
    }


    public static void main(String[] args) {
        final WaitNotify wn = new WaitNotify();
        new Thread(new Runnable() {
            @Override
            public void run() {
                productor p = wn.new productor();
                try {
                    p.product();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                consumer c = wn.new consumer();
                try {
                    c.comsume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
