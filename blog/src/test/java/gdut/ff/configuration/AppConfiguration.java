package gdut.ff.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author bluesnail95
 * @Date 2020/7/29 20:49
 * @Description
 */
@Configuration
public class AppConfiguration {

    @Bean
    public ConfigurationService configurationService() {
        return new ConfigurationServiceImpl(configurationBean());
    }

    @Bean
    public ConfigurationBean configurationBean() {
        return new ConfigurationBean();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
//        System.out.println(context.getBean("configurationService"));
        AppConfiguration appConfiguration = (AppConfiguration) context.getBean("appConfiguration");
        System.out.println(appConfiguration);

    }

}
