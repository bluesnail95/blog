package gdut.ff.rabbitmq;

import java.util.concurrent.SynchronousQueue;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import gdut.ff.domain.Blog;

/**
 * RabbitMq的服务消费端
 * @author liuffei
 * @date 2018-05-31
@Component
@RabbitListener(queues = "blog")
public class Receiver {

	public static SynchronousQueue queue = new SynchronousQueue();
	
	@RabbitHandler
	public void process(Blog blog) {
		queue.add(blog);
	}

}
 */