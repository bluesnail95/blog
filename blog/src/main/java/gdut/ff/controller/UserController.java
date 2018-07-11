package gdut.ff.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;

import gdut.ff.domain.User;
import gdut.ff.domain.UserAccess;
import gdut.ff.service.UserAccessServiceImpl;
import gdut.ff.service.UserServiceImpl;
import gdut.ff.utils.JsonUtil;
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
	 * @throws Exception 
	 */
	@GetMapping(value = "/users")
	public JSONObject findAllUsers(HttpServletRequest request) throws Exception {
		requireAuth(request);
		List<User> users =  userServiceImpl.findAllUser();
		JSONObject result = JsonUtil.successJson();
		result.put("users", users);
		return result;
		
	}
	
	/**
	 * 查询指定的用户
	 * @param id 用户ID
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value = "/user/{id}")
	public JSONObject getUser(HttpServletRequest request, @PathVariable int id) throws Exception {
		requireAuth(request);
		User user = userServiceImpl.fingUserById(id);
		JSONObject result = JsonUtil.successJson();
		result.put("user",user);
		return result;
	}
	
	/**
	 * 更新用户信息
	 * @param request
	 * @param user 用户信息
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/user")
	public JSONObject updateUser(HttpServletRequest request,@RequestBody User user) throws Exception {
		requireAuth(request);
		userServiceImpl.updateUser(user);
		JSONObject result = JsonUtil.successJson();
		return result;
	}
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("/user/login")
	public JSONObject userLogin(@RequestBody JsonNode param) throws Exception {
		User userValidate = NodeUtil.transToPOJO(param, User.class);
		User user = userServiceImpl.loginUser(userValidate);
		if(null == user) {
			NodeUtil.errorNode("用户名/邮箱或密码错误");
		}
		String token = TokenUtil.token(SECERT, user, expireMinutes);
		JSONObject result = JsonUtil.successJson();
		result.put("token", token);
		//不返回用户密码
		user.setPassword(null);
		result.put("user", user);
		return result;
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@PostMapping("/user/regist")
	public JSONObject userRegist(@RequestBody JsonNode param) {
		String passwordConfirm = param.has("passwordConfirm") ? param.get("passwordConfirm").asText() :"";
		String password = param.has("password") ? param.get("password").asText() : "";
		//判断密码是否相等
		if(StringUtil.isBlank(passwordConfirm) || StringUtil.isBlank(password) 
				|| !passwordConfirm.equals(password)) {
		    NodeUtil.errorNode("两次输入密码不一致，请重新输入");
		}
		User user = NodeUtil.transToPOJO(param,User.class);
		userServiceImpl.saveUser(user);
		return JsonUtil.successJson();
	}
	
	/**
	 * 保存用户访问
	 * @param userAccess
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("/user/access")
	public JSONObject userAccess(@RequestBody UserAccess userAccess,HttpServletRequest request) throws Exception {
		//设置主键和创建时间 保存
		User user = getUser(request);
		userAccess.setUserId(user.getUserId());
		userAccessServiceImpl.saveUserAccess(userAccess);
		return JsonUtil.successJson();
	}
	
	/**
	 * 用户修改登录密码
	 * @param param password 当前密码 newPassword 新密码 confirmPassword 确认密码
	 * @return
	 * @throws Exception 
	 */
	@PutMapping(value="/user/password")
	public JSONObject updatePassword(@RequestBody JsonNode param,HttpServletRequest request) throws Exception {
		User user = getUser(request);
		if(null == user) NodeUtil.errorNode("请重新登录");
		String password = param.has("password") ? param.get("password").asText() : "";
		if(StringUtil.isBlank(password) || !password.equals(user.getPassword())) NodeUtil.errorNode("当前密码输入有误，请重新输入");
		String newPassword = param.has("newPassword") ? param.get("newPassword").asText() : "";
		String comfirmPassword = param.has("comfirmPassword") ? param.get("comfirmPassword").asText() : "";
		if(StringUtil.isBlank(newPassword) || StringUtil.isBlank(comfirmPassword) || !newPassword.equals(comfirmPassword) ) NodeUtil.errorNode("新密码或重复输入的新密码为空，或两次输入的密码不一致");
		user.setPassword(newPassword);
		String newToken = TokenUtil.token(SECERT, user, expireMinutes);
		userServiceImpl.updateUser(user);
		JSONObject result = JsonUtil.successJson();
		result.put("token",newToken);
		user.setPassword(null);
		result.put("user", user);
		return result;
	}
	
	
}
