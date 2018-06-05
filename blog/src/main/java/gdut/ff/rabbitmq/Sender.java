package gdut.ff.rabbitmq;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gdut.ff.domain.Blog;

/**
 * Rabbit的服务提供端
 * @author liuffei
 * @date 2018-05-31
@Component
public class Sender {
	
	private Logger logger = LoggerFactory.getLogger(Sender.class);
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public void send(Blog blog) {
		//此处的blog为队列名称
		this.rabbitTemplate.convertAndSend("blog", blog);
	}

}
 */
