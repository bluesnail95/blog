package gdut.ff.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import gdut.ff.domain.Blog;

/**
 * @date 2018-06-05
 * @author liuffei
 * 服务端推送消息
 */
@Component
public class BlogWebSocketServer {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	public void blogServerMessage(Blog blog) {
		messagingTemplate.convertAndSend("/topic/subscribe", blog);
	}
	
}
