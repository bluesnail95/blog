package gdut.ff.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @Author bluesnail95
 * @Date 2020/7/20 21:33
 * @Description
 */
@EnableAspectJAutoProxy
@ComponentScan("gdut.ff.aop")
public class TestAop {
    public static void main(String[] args) throws Exception{
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestAop.class);
        ITestService  testServiceImpl = (ITestService) context.getBean("testServiceImpl");
        testServiceImpl.test();

//        byte[] $Proxies = ProxyGenerator.generateProxyClass("$Proxy", new Class[]{ITestService.class});
//        FileOutputStream fileOutputStream = new FileOutputStream("D:\\Test.class");
//        fileOutputStream.write($Proxies);
//        fileOutputStream.flush();
//        fileOutputStream.close();
    }
}
