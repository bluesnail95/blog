package gdut.ff.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gdut.ff.domain.User;
import gdut.ff.mapper.UserMapper;

@RestController
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 查询全部的用户
	 * @return
	 */
	@RequestMapping(value = "/users",method = RequestMethod.GET)
	public ObjectNode findAllUsers() {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode result = objectMapper.createObjectNode();
		List<User> users = userMapper.findAll();
		ArrayNode content = result.putArray("users");
		content.addAll(objectMapper.convertValue(users, ArrayNode.class));
		result.put("status",1);
		return result;
	}
	
	/**
	 * 查询指定的用户
	 * @param id 用户ID
	 * @return
	 */
	@RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
	public ObjectNode getUser(@PathVariable long id) {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode result = objectMapper.createObjectNode();
		User user = userMapper.fingOneById(id);
		result.putPOJO("user",user);
		result.put("status",1);
		return result;
	}
	
	

}
