package gdut.ff.ioc;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author bluesnail95
 * @Date 2020/7/25 18:13
 * @Description
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
//        BeanDefinition rootBeanDefinition = new RootBeanDefinition(User.class);
//        registry.registerBeanDefinition("user", rootBeanDefinition);

        BeanDefinition userMapperBeanDefinition = new RootBeanDefinition("gdut.ff.ioc.MyFactoryBean2");
        userMapperBeanDefinition.getConstructorArgumentValues().addGenericArgumentValue("gdut.ff.ioc.UserMapper");
        registry.registerBeanDefinition("userMapper", userMapperBeanDefinition);
    }
}
