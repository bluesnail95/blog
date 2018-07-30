package gdut.ff.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

import gdut.ff.domain.Message;
import gdut.ff.service.MessageServiceImpl;
import gdut.ff.utils.JsonUtil;

/**
 * @date 2018-06-05
 * @author liuffei
 *
 */
@Controller
public class WebSocketController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MessageServiceImpl messageServiceImpl;
	
	@MessageMapping("/blogMessage")
	@SendTo("/topic/blog")
	public JSONObject blogMessage(Message message) {
		JSONObject result = JsonUtil.successJson();
		//如果发送的通知message不为空，就直接添加，如果发送的通知message为空，就查询数据库最新的一条通知记录
		if(null == message) {
			message = messageServiceImpl.findLastestMessage();
		}
		result.put("message", message);
		return result;
	}
}
