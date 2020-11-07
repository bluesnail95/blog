package gdut.ff.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 在线用户监听器
 * @author bluesnail95
 *
 */
@WebListener
public class OnlineUserListener implements HttpSessionListener, ServletContextListener{
	
	private RedisTemplate redisTemplate;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		SetOperations<String,Object> set = redisTemplate.opsForSet();
		set.add("users", se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		redisTemplate = (RedisTemplate) context.getBean("redisTemplate");
		System.out.println(redisTemplate);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
	
	

}
