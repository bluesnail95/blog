package gdut.ff.comm;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.codahale.metrics.annotation.Counted;
import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Metered;
import com.codahale.metrics.annotation.Timed;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.codahale.metrics.MetricRegistry.name;

/**
 * @Author bluesnail95
 * @Date 2020/10/4 9:07
 * @Description
 */
@Component
@Aspect
public class AutoMetricAspect {

    protected ConcurrentMap<String, Meter> meters = new ConcurrentHashMap<>();
    protected ConcurrentMap<String, Meter> exceptionMeters = new ConcurrentHashMap<>();
    protected ConcurrentMap<String, Timer> timers = new ConcurrentHashMap<>();
    protected ConcurrentMap<String, Counter> counters = new ConcurrentHashMap<>();

    @Autowired
    MetricRegistry metricRegistry;

    @Pointcut(value = "execution(gdut.ff..* *(..))")
    public void publicMethods() {

    }

    @Before("publicMethods() && @annotation(countedAnnotation)")
    public void instrumentCounted(JoinPoint jp, Counted countedAnnotation) {
        String name = name(jp.getTarget().getClass(), StringUtils.hasLength(countedAnnotation.name()) ? countedAnnotation.name() : jp.getSignature().getName(), "counter");
        Counter counter = counters.computeIfAbsent(name, key -> metricRegistry.counter(key));
        counter.inc();
    }

    @Before("publicMethods() && @annotation(meteredAnnotation)")
    public void instrumentMetered(JoinPoint jp, Metered meteredAnnotation) {
        String name = name(jp.getTarget().getClass(), StringUtils.hasLength(meteredAnnotation.name()) ? meteredAnnotation.name() : jp.getSignature().getName(), "meter");
        Meter meter = meters.computeIfAbsent(name, key -> metricRegistry.meter(key));
        meter.mark();
    }

    @AfterThrowing(value = "publicMethods() && @annotation(exMeteredAnnotation)", throwing = "ex")
    public void instrumentMetered(JoinPoint jp, Throwable ex, ExceptionMetered exMeteredAnnotation) {
        String name = name(jp.getTarget().getClass(), StringUtils.hasLength(exMeteredAnnotation.name()) ? exMeteredAnnotation.name() : jp.getSignature().getName(), "meter", "exception");
        Meter meter = exceptionMeters.computeIfAbsent(name, meterName -> metricRegistry.meter(meterName));
        meter.mark();
    }

    @Around(value = "publicMethods() && @annotation(timedAnnotation)")
    public Object instrumentTimed(ProceedingJoinPoint pjp, Timed timedAnnotation) throws Throwable{
        String name = name(pjp.getTarget().getClass(), StringUtils.hasLength(timedAnnotation.name()) ? timedAnnotation.name() : pjp.getSignature().getName(), "timer");
        Timer timer = timers.computeIfAbsent(name, inputName -> metricRegistry.timer(inputName));
        Timer.Context time = timer.time();
        try {
            return pjp.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            time.stop();
        }
        return null;
    }



}
