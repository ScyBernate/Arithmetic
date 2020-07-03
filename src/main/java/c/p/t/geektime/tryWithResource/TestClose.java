package c.p.t.geektime.tryWithResource;

public class TestClose implements AutoCloseable {

    public void test(){
        System.out.println("调用test方法");
    }

    @Override
    public void close() throws Exception {
        System.out.println("自动关闭");
    }

    public static void main(String[] args) {
        try(TestClose tc = new TestClose();){
            tc.test();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
