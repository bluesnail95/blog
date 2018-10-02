package gdut.ff.redis;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	public void addSetValue(String key,Object value) {
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		set.add(key, value);
	}
	
	public void expireTime(String key,int timeout,TimeUnit unit) {
		redisTemplate.expire(key, timeout, unit);
	}
	
	public Set<Object> getSetValue(String key) {
		if(!redisTemplate.hasKey(key)) {
			return null;
		}
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		Set<Object> values = set.members(key);
		return values;
	}

}
