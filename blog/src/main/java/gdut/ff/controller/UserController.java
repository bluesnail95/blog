package gdut.ff.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gdut.ff.domain.User;
import gdut.ff.domain.UserAccess;
import gdut.ff.mapper.UserAccessMapper;
import gdut.ff.mapper.UserMapper;
import gdut.ff.service.UserAccessServiceImpl;
import gdut.ff.service.UserServiceImpl;
import gdut.ff.utils.AjaxResult;
import gdut.ff.utils.NodeUtil;
import gdut.ff.utils.TokenUtil;

@RestController
public class UserController extends CommController{
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Value("${blog.user.expire-minutes}")
	private int expireMinutes;
	           
	@Value("${blog.user.secret}")
	private String SECERT;
	
	@Autowired
	private UserAccessServiceImpl userAccessServiceImpl;
	
	/**
	 * 查询全部的用户
	 * @return
	 */
	@GetMapping(value = "/users")
	public ObjectNode findAllUsers(HttpServletRequest request) {
		try {
			requireAuth(request);
			List<User> users =  userServiceImpl.findAllUser();
			ObjectNode result = NodeUtil.successNode();
			result.putPOJO("users", users);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return NodeUtil.errorNode(e.getMessage());
		}
	}
	
	/**
	 * 查询指定的用户
	 * @param id 用户ID
	 * @return
	 */
	@GetMapping(value = "/user/{id}")
	public ObjectNode getUser(HttpServletRequest request, @PathVariable long id) {
		try {
			requireAuth(request);
			User user = userServiceImpl.fingUserById(id);
			ObjectNode result = NodeUtil.successNode();
			result.putPOJO("user",user);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return NodeUtil.errorNode(e.getMessage());
		}
	}

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	@PostMapping("/user/login")
	public ObjectNode userLogin(@RequestBody JsonNode param) {
		User userValidate = NodeUtil.transToPOJO(param, User.class);
		User user = null;
		try {
			//根据邮箱和密码查找用户
			user = userServiceImpl.loginUser(userValidate);
		}catch(Exception e) {
			e.printStackTrace();
			return NodeUtil.errorNode(e.getMessage());
		}
		if(null == user) {
			NodeUtil.errorNode("用户名/邮箱或密码错误");
		}
		String token = null;
		try {
			token = TokenUtil.token(SECERT, user, expireMinutes);
		} catch (Exception e) {
			e.printStackTrace();
			return NodeUtil.errorNode(e.getMessage());
		}
		ObjectNode result = NodeUtil.successNode();
		result.put("token", token);
		//不返回用户密码
		result.putPOJO("user", new AjaxResult(NodeUtil.transFromPOJO(user)).blacksProps("password").filter());
		return result;
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@PostMapping("/user/regist")
	public ObjectNode userRegist(@RequestBody JsonNode param) {
		String passwordConfirm = param.has("passwordConfirm") ? param.get("passwordConfirm").asText() :"";
		String password = param.has("password") ? param.get("password").asText() : "";
		//判断密码是否相等
		if(StringUtil.isBlank(passwordConfirm) || StringUtil.isBlank(password) 
				|| !passwordConfirm.equals(password)) {
		    NodeUtil.errorNode("两次输入密码不一致，请重新输入");
		}
		User user = NodeUtil.transToPOJO(param,User.class);
		user.setId(UUID.randomUUID().toString());
		try {
			userServiceImpl.saveUser(user);
		}catch(Exception e) {
			e.printStackTrace();
			return NodeUtil.errorNode(e.getMessage());
		}
		return NodeUtil.successNode();
	}
	
	/**
	 * 保存用户访问
	 * @param userAccess
	 * @return
	 */
	@PostMapping("/user/access")
	public ObjectNode userAccess(@RequestBody UserAccess userAccess,HttpServletRequest request) {
		
		//设置主键和创建时间 保存
		userAccess.setId(UUID.randomUUID().toString());
		try {
			User user = getUser(request);
			userAccess.setUserId(user.getUserId());
			userAccessServiceImpl.saveUserAccess(userAccess);
		}catch(Exception e) {
			e.printStackTrace();
			return NodeUtil.errorNode(e.getMessage());
		}
		return NodeUtil.successNode();
	}
	
	/**
	 * 用户修改登录密码
	 * @param param password 当前密码 newPassword 新密码 confirmPassword 确认密码
	 * @return
	 */
	@PutMapping(value="/user/password")
	public ObjectNode updatePassword(@RequestBody JsonNode param,HttpServletRequest request) {
		User user = null;
		try {
			user = getUser(request);
		} catch (Exception e) {
			return NodeUtil.errorNode(e.getMessage());
		}
		if(null == user) NodeUtil.errorNode("请重新登录");
		String password = param.has("password") ? param.get("password").asText() : "";
		if(StringUtil.isBlank(password) || !password.equals(user.getPassword())) NodeUtil.errorNode("当前密码输入有误，请重新输入");
		String newPassword = param.has("newPassword") ? param.get("newPassword").asText() : "";
		String comfirmPassword = param.has("comfirmPassword") ? param.get("comfirmPassword").asText() : "";
		if(StringUtil.isBlank(newPassword) || StringUtil.isBlank(comfirmPassword) || !newPassword.equals(comfirmPassword) ) NodeUtil.errorNode("新密码或重复输入的新密码为空，或两次输入的密码不一致");
		user.setPassword(newPassword);
		String newToken = null;
		try {
			userServiceImpl.updateUser(user);
			newToken = TokenUtil.token(SECERT, user, expireMinutes);
		}catch(Exception e) {
			return NodeUtil.errorNode(e.getMessage());
		}
		ObjectNode result = NodeUtil.successNode();
		result.put("token",newToken);
		user.setPassword(null);
		result.putPOJO("user", user);
		return result;
	}
	
	
}