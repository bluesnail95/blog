package gdut.ff.listener;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.servlets.MetricsServlet;

import javax.servlet.annotation.WebListener;

/**
 * @Author bluesnail95
 * @Date 2020/10/5 9:23
 * @Description
 */
@WebListener
public class MetricsListener extends MetricsServlet.ContextListener {

    public static final MetricRegistry METRIC_REGISTRY = new MetricRegistry();

    @Override
    protected MetricRegistry getMetricRegistry() {
        return METRIC_REGISTRY;
    }

}
