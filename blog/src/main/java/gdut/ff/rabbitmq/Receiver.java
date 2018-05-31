package gdut.ff.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 
 * @author liuffei
 * @date 2018-05-31
 */
@Component
@RabbitListener(queues = "blog")
public class Receiver {

	@RabbitHandler
	public void process(String hello) {
		System.out.println("Receiver: " + hello);
	}
}
