package gdut.ff.redis;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import gdut.ff.domain.Blog;
import junit.framework.Assert;
import redis.clients.jedis.Jedis;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Test
	public void test() {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		String value = stringRedisTemplate.opsForValue().get("aaa");
		System.out.println(value);
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}
	
	@Test
	public void testObj() throws Exception {
		Blog blog = new Blog();
		blog.setBlogId("1");
		blog.setContent("哈哈");
		ValueOperations<String, Blog> operations = redisTemplate.opsForValue();
		operations.set("www.liuffei", blog);
		operations.set("www.liuffei.com", blog, 1, TimeUnit.SECONDS);
		Thread.sleep(1000);
		boolean exists = redisTemplate.hasKey("www.liuffei.com");
		if(exists) {
			System.out.println("exists is true");
		}else {
			System.out.println("exists is false");
		}
	}
	
	@Test
	public void testPingJedis() {
		Jedis jedis = new Jedis("localhost");
		System.out.println("连接本地Redis服务成功");
		System.out.println("服务正在运行:"+jedis.ping());
		
		jedis.set("hello", "world");
		System.out.println(jedis.get("hello"));
	}
}
