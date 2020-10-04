package gdut.ff.metrics;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.management.MBeanServer;

/**
 * @Author bluesnail95
 * @Date 2020/10/4 8:09
 * @Description
 */
@Configuration
@ComponentScan("")
@AutoConfigureAfter(AopAutoConfiguration.class)
public class DropwizardMetricsMBeansAutoConfiguration {

    @Value("${metrics.mbeans.domain.name:gdut.ff.metrics}")
    String metricsMBeansDomainName;

    @Autowired
    MBeanServer mBeanServer;

    @Autowired
    MetricRegistry metricRegistry;

    public JmxReporter jmxReporter() {
        JmxReporter reporter = JmxReporter.forRegistry(metricRegistry)
                .inDomain(metricsMBeansDomainName)
                .registerWith(mBeanServer)
                .build();
        return reporter;
    }
}

