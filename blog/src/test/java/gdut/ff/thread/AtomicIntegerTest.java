package gdut.ff.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author bluesnail95
 * @Date 2020/6/26 9:58
 * @Description
 */
public class AtomicIntegerTest {

    private static volatile AtomicInteger page;

    public static void main(String[] args) {

        page = new AtomicInteger(0);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 6000, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        while(page.get() < 50) {
            increase(page);
            Future submit = threadPoolExecutor.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    System.out.println("page is : " + page.get() );
                    System.out.println("------------------------");
                    return true;
                }
            });
        }
        while(threadPoolExecutor.isTerminated()) {
            threadPoolExecutor.shutdown();
            return;
        }
    }

    public static void increase(AtomicInteger page) {
        page.incrementAndGet();
    }

}
