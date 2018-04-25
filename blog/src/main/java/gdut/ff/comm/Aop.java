package gdut.ff.comm;

import javax.security.auth.login.LoginException;

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
		//System.out.println("环绕通知前");
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
		//System.out.println("环绕通知后");
		return obj;
	}
	
}
