package gdut.ff.ioc;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author bluesnail95
 * @Date 2020/7/26 12:00
 * @Description
 */
public class MyFactoryBean2 implements FactoryBean {

    private Class mapperClass;

    public MyFactoryBean2(Class mapperClass) {
        this.mapperClass = mapperClass;
    }

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("Proxy.newProxyInstance");
                return null;
            }
        });
    }

    @Override
    public Class<?> getObjectType() {
        return mapperClass;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
