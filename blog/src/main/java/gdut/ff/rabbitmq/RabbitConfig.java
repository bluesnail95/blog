package gdut.ff.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 创建队列
 * @author liuffei
 * @date 2018-05-31
@Configuration
public class RabbitConfig {

	@Bean
	public Queue BlogQueue() {
		return new Queue("blog");
	}
}
*/
