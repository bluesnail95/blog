package gdut.ff.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

/**
 * @date 2018-06-05
 * @author liuffei
 *
 */
@Controller
public class WebSocketController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 客户端订阅
	 * @return
	@SubscribeMapping("/subscribe")
	public JSONObject subscribe() {
		JSONObject result = new JSONObject();
		return result;
	}
	*/
}
