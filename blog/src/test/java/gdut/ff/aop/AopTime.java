package gdut.ff.aop;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.JWTVerificationException;
import gdut.ff.utils.JsonUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;

/**
 * @Author bluesnail95
 * @Date 2020/7/21 7:09
 * @Description
 */
@Configuration
@Aspect
public class AopTime {

    @Around("execution(* gdut.ff.aop.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        long start = System.currentTimeMillis();
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("消耗时间：" + (end - start) + " interface name is : " + joinPoint.getTarget());
        return obj;
    }

}
