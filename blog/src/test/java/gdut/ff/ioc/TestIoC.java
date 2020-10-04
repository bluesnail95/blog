package gdut.ff.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.Arrays;

/**
 * @Author bluesnail95
 * @Date 2020/7/25 10:04
 * @Description
 */
//@ComponentScan("gdut.ff.ioc")
@Import(MyImportBeanDefinitionRegistrar.class)
public class TestIoC {
    public static void main(String[] args) {
        //xml方式
//        ResourceLoader resourceLoader = new DefaultResourceLoader();
//        Resource resource = resourceLoader.getResource("spring.xml");

//        BeanDefinitionRegistry simpleBeanDefinitionRegistry = new SimpleBeanDefinitionRegistry();
//        BeanDefinitionRegistry simpleBeanDefinitionRegistry = new DefaultListableBeanFactory();
//        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(simpleBeanDefinitionRegistry);
//        beanDefinitionReader.loadBeanDefinitions(resource);

//        BeanDefinition rootBeanDefinition = new RootBeanDefinition(IOCConfig.class);
//        simpleBeanDefinitionRegistry.registerBeanDefinition("test", rootBeanDefinition);
//
//        System.out.println(Arrays.toString(simpleBeanDefinitionRegistry.getBeanDefinitionNames()));
//        System.out.println(((DefaultListableBeanFactory) simpleBeanDefinitionRegistry).getBean("test"));

        //注解方式
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestIoC.class);
//        System.out.println(context.getBean(IOCConfig.class));

        //注册到beanDefinitionMap
//        BeanFactory defaultListableBeanFactory = context.getDefaultListableBeanFactory();
//        BeanDefinition rootBeanDefinition = new RootBeanDefinition(IOCConfig.class);
//        ((DefaultListableBeanFactory) defaultListableBeanFactory).registerBeanDefinition("config", rootBeanDefinition);
//        ((RootBeanDefinition) rootBeanDefinition).setAutowireMode(3);

        //注册到singletonMap
//        ((DefaultListableBeanFactory) defaultListableBeanFactory).registerSingleton("config", new IOCConfig());
//        System.out.println(((DefaultListableBeanFactory) defaultListableBeanFactory).getBeanDefinition("IOCConfig"));
//        ((DefaultListableBeanFactory) defaultListableBeanFactory).getBeanDefinition("config").getPropertyValues().addPropertyValue("name","xxx");

//        System.out.println(context.getBean("config"));

//        System.out.println(context.getBean("myFactoryBean"));
//        System.out.println(context.getBean("&myFactoryBean"));
        System.out.println(context.getBean("userMapper"));
    }
}
