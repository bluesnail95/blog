package gdut.ff.rabbitmq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author liuffei
 * @date 2018-05-31
 */
@Component
public class Sender {
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	public void send() {
		String context = "hello " + new Date();
		System.out.println("Sender: " + context);
		//此处的blog为队列名称
		this.rabbitTemplate.convertAndSend("blog", context);
	}

}
