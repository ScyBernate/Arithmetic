package c.p.t.geektime;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Fetch And Add & Compare And Set
 */
public class FaaAndCas implements Runnable {

    private AtomicInteger count;

    private CountDownLatch latch;

    public FaaAndCas(AtomicInteger count, CountDownLatch latch) {
        this.count = count;
        this.latch = latch;
    }

    public void run() {
        while (!count.compareAndSet(count.get(), count.get() + 1)) {}
        latch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(1000);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1000,1000,0, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
        for(int i = 0;i<1000;i++){
            executor.execute(new FaaAndCas(count,latch));
        }
        latch.await();
        executor.shutdown();
        System.out.println(count.get());


        /** * 解决ABA问题  */
        AtomicStampedReference<Integer> tamp = new AtomicStampedReference<>(100,0);
        tamp.compareAndSet(tamp.getReference(),tamp.getReference()+1,tamp.getStamp(),tamp.getStamp()+1);
        System.out.println(tamp.getReference());
    }

}
