package c.p.t.learning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiFunction;

/**
 * @ClassName CompletableFutureTest
 * @Description TODO completableFuture测试
 * @Author User
 * @DATE 2020/4/13 15:10
 * @Version 1.0
 **/

@RunWith(SpringRunner.class)
public class CompletableFutureTest {


    //无返回值的测试
    //@Test
    public void testRunAsync() throws IOException, ExecutionException, InterruptedException {
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("do something");
            }
        });
        System.out.println("do other thing");
        TimeUnit.SECONDS.sleep(6);
    }

    //有返回值的测试
    @Test
    public void testSupplyAsync() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(4/0);
            System.out.println("1------->执行任务中");
            return "success";
        /*}).thenApply(new Function<String, String>() {  //依赖上一个任务的结果进行第二个任务的场景(这里是线程相互依赖)
            @Override
            public String apply(String s) {
                if ("success".equals(s)) {
                    System.out.println("2=======执行任务2中");
                }
                return "完成测试";
            }
        });*/
        }).handle(new BiFunction<String, Throwable, String>() { //同样是依赖上一个任务产生的结果进行任务处理，但是会针对异常进行处理
            @Override
            public String apply(String s, Throwable throwable) {
                if (throwable == null) {
                    System.out.println("2============执行任务中");
                    return s;
                } else {
                    System.out.println("产生报错，报错：" + throwable.getMessage());
                }
                return null;
            }
        });
        System.out.println("do other thing");
        System.out.println(result.get(6, TimeUnit.SECONDS));  //超时会报 TimeOutException
    }


}
