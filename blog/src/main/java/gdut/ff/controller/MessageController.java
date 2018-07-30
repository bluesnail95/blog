package gdut.ff.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gdut.ff.domain.Message;
import gdut.ff.service.MessageServiceImpl;
import gdut.ff.utils.NodeUtil;
import gdut.ff.utils.JsonUtil;
import gdut.ff.utils.TokenUtil;

/**
 * 消息管理
 * @author liuffei
 * @date 
 */
@RestController
public class MessageController {
	
	@Autowired
	private MessageServiceImpl messageServiceImpl;
	
	/**
	 * 添加记录
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/message")
	public JSONObject insertMessage(@RequestBody Message message,HttpServletRequest request) throws Exception {
		if(message.getId() > 0) {
			messageServiceImpl.updateMessage(message);
		}else {
			messageServiceImpl.insertMessage(message);
		}
		return JsonUtil.successJson();
	}
	
	/**
	 * 查询指定记录
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/message/{id}")
	public JSONObject findMessageById(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		Message message = messageServiceImpl.findMessageById(id);
		JSONObject result = JsonUtil.successJson();
		result.put("content", message);
		return result;
	}
	
	/**
	 * 更新指定的记录
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/message/{id}")
	public JSONObject updateMessagegById(@PathVariable String id, @RequestBody Message message) {
		messageServiceImpl.updateMessage(message);
		return JsonUtil.successJson();
	}
	
	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/message/{id}")
	public JSONObject deleteMessageById(@PathVariable String id) {
		messageServiceImpl.deleteMessageById(id);
		return JsonUtil.successJson();
	}
	
	/**
	 * 查询全部
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/messages")
	public JSONObject finaAllMessages(@RequestBody Message message) {
		List<Message> messages = messageServiceImpl.findAllMessage(message);
		JSONObject result = JsonUtil.successJson();
		result.put("messages", messages);
		return result;
	}
	
	/**
	 * 查询最新的一条记录
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/lastestMessage")
	public JSONObject findLastestMessage() {
		Message lastestMessage = messageServiceImpl.findLastestMessage();
		JSONObject result = JsonUtil.successJson();
		result.put("message", lastestMessage);
		return result;
	}
}
