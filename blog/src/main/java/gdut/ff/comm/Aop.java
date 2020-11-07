package gdut.ff.comm;

import javax.security.auth.login.LoginException;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import gdut.ff.listener.MetricsListener;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.JWTVerificationException;

import gdut.ff.utils.JsonUtil;

/**
 * 切面(切入点，前置通知，后置通知，环绕通知，异常通知，返回通知)
 * @author liuffei
 * @date 2018-04-25
 */
@Configuration
@Aspect
public class Aop {

    private final Timer executions = MetricsListener.METRIC_REGISTRY.timer(MetricRegistry.name(Aop.class, "executions"));
	
	/**
	 * 异常通知
	 * @param point
	 * @param ex
	 * 
	 * 
	 */
	@AfterThrowing(pointcut = "execution(* gdut.ff..*.*(..))", throwing = "ex")
	public void afterThrowing(JoinPoint point, Exception ex) {
		ex.printStackTrace();
	}
	
	/**
	 * 环绕通知 = 前置通知 + 后置通知
	 * @param joinPoint
	 * @return
	 * @throws Throwable 
	 */
	@Around("execution(* gdut.ff.controller.*.*(..))")
	public JSONObject around(ProceedingJoinPoint joinPoint) {
        final Timer.Context context = executions.time();
		long start = System.currentTimeMillis();
		JSONObject obj = null;
		try {
		    obj = (JSONObject) joinPoint.proceed();
		}catch(JWTVerificationException exp) {
			exp.printStackTrace();
			obj = JsonUtil.loginJson();
		}catch(LoginException ex) {
			ex.printStackTrace();
			obj = JsonUtil.loginJson();
		}catch(Throwable e) {
			e.printStackTrace();
			obj = JsonUtil.errorJson(e.getMessage());
		}
		long end = System.currentTimeMillis();
		System.out.println("消耗时间：" + (end - start) + " interface name is : " + joinPoint.getTarget());
		context.stop();
		return obj;
	}
	
}
