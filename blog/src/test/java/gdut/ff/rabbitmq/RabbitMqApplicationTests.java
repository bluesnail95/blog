package gdut.ff.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqApplicationTests {

	@Autowired
	private Sender sender;
	
	@Test
	public void blog() {
		sender.send();
	}
}
