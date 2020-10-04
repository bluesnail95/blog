package gdut.ff.configuration;

/**
 * @Author bluesnail95
 * @Date 2020/7/29 20:50
 * @Description
 */
public class ConfigurationServiceImpl implements ConfigurationService {

    public ConfigurationServiceImpl(ConfigurationBean configurationBean) {
        System.out.println("ConfigurationServiceImpl====" + configurationBean);
    }

    @Override
    public void test(ConfigurationBean configurationBean) {
        System.out.println("test====" + configurationBean);
    }
}
