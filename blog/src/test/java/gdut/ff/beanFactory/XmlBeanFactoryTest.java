package gdut.ff.beanFactory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @Author bluesnail95
 * @Date 2020/8/22 10:01
 * @Description
 */
public class XmlBeanFactoryTest {

    public static void main(String[] args) {
        ClassPathResource classPathResource = new ClassPathResource("xxx.xml");
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        reader.loadBeanDefinitions(classPathResource);
    }

}
