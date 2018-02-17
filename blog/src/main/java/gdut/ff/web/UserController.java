package gdut.ff.web;

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
import gdut.ff.utils.AjaxResult;
import gdut.ff.utils.NodeUtil;
import gdut.ff.utils.TokenUtil;

@RestController
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
	@Value("${blog.user.expire-minutes}")
	private int expireMinutes;
	           
	@Value("${blog.user.secret}")
	private String SECERT;
	
	@Autowired
	private UserAccessMapper userAccessMapper;
	
	/**
	 * 查询全部的用户
	 * @return
	 */
	@GetMapping(value = "/users")
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
	@GetMapping(value = "/user/{id}")
	public ObjectNode getUser(@PathVariable long id) {
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode result = objectMapper.createObjectNode();
		User user = userMapper.fingOneById(id);
		result.putPOJO("user",user);
		result.put("status",1);
		return result;
	}
	
	/**
	 * 修改用户头像
	 * @param file
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/upload/user")
	public ObjectNode singleFileUpload(@RequestParam("file")MultipartFile file,HttpServletRequest req) {
		ObjectNode result = NodeUtil.create();
		if(file.isEmpty()) {
			result.put("message","Please select a file to upload");
		    return result;
		}
		try {
			//获取登录token			
			String token = req.getHeader("token");
			if(StringUtil.isNotBlank(token)) {
				User user = TokenUtil.verifyUser(token,SECERT);
				if(null == user) {
					result.put("status",2);
					result.put("message","请确认登录!!!");
					return result;
				}
				String imgName = user.getLoginName();
				byte[] bytes = file.getBytes();
				String root = ResourceUtils.getURL("classpath:").getPath() + "/upload";
				File parent = new File(root);
				if(!parent.exists()) {
					parent.mkdirs();
				}
				int split = file.getOriginalFilename().lastIndexOf(".");
				String fileName = file.getOriginalFilename().replace(file.getOriginalFilename().substring(0,split),imgName);
				BufferedOutputStream bos =    
	                    new BufferedOutputStream(new FileOutputStream(new File(parent,fileName)));    
				bos.write(bytes);    
				bos.close();
				//保存用户头像信息
				user.setImg(root+"/"+fileName);
				userMapper.updateUser(user);
				result.put("status", 1);
				result.put("url",root+"/"+fileName);
			    result.put("message","successfully upload "+file.getOriginalFilename());
			}else {
				result.put("status",2);
				result.put("message","请确认登录!!!");
				return result;
			}
		}catch(Exception e) {
			e.printStackTrace();
			result.put("error","error");
		}
		return result;
	}

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	@PostMapping("/user/login")
	public ObjectNode userLogin(@RequestBody JsonNode param) {
		ObjectNode result = NodeUtil.create();
		User userValidate = NodeUtil.transToPOJO(param, User.class);
		//根据邮箱和密码查找用户
		User user = userMapper.loginUser(userValidate);
		if(null == user) {
			result.put("error","用户名/邮箱或密码错误");
			return result;
		}
		try {
			String token = TokenUtil.token(SECERT, user, expireMinutes);
			result.put("token",token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//不返回用户密码
		result.putPOJO("user", new AjaxResult(NodeUtil.transFromPOJO(user)).blacksProps("password").filter());
		result.put("status", 1);
		return result;
	}
	
	/**
	 * 用户注册
	 * @param user
	 * @return
	 */
	@PostMapping("/user/regist")
	public ObjectNode userRegist(@RequestBody JsonNode param) {
		ObjectNode result = NodeUtil.create();
		String passwordConfirm = param.has("passwordConfirm") ? param.get("passwordConfirm").asText() :"";
		String password = param.has("password") ? param.get("password").asText() : "";
		//判断密码是否相等
		if(StringUtil.isBlank(passwordConfirm) || StringUtil.isBlank(password) 
				|| !passwordConfirm.equals(password)) {
			result.put("error","两次输入密码不一致，请重新输入");
		    return result;	
		}
		User user = NodeUtil.transToPOJO(param,User.class);
		user.setId(UUID.randomUUID().toString());
		userMapper.saveUser(user);
		result.put("status",1);
		return result;
	}
	
	/**
	 * 用户校验token测试
	 * @param param
	 * @return
	 */
	@PostMapping("/user/verify")
	public ObjectNode userVerify(@RequestBody JsonNode param) {
		ObjectNode result = NodeUtil.create();
		String token = param.has("token") ? param.get("token").asText() : "";
		if(StringUtil.isNotBlank(token)) {
			try {
				User user = TokenUtil.verifyUser(token, SECERT);
				if(null == user) {
					result.put("error","登录失败");
				}
				result.putPOJO("user",user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			result.put("error","登录失败");
		}
		result.put("status",1);
		return result;
	}
	
	/**
	 * 保存用户访问
	 * @param userAccess
	 * @return
	 */
	@PostMapping("/user/access")
	public ObjectNode userAccess(@RequestBody UserAccess userAccess,HttpServletRequest request) {
		ObjectNode result = NodeUtil.create();
		//设置主键和创建时间 保存
		userAccess.setId(UUID.randomUUID().toString());
		userAccess.setCreateTime(new Date());
		//判断token是否有效，有效取出用户的主键
		String token = request.getHeader("token");
		try {
			User user = TokenUtil.verifyUser(token, SECERT);
			if(null != user) {
				userAccess.setId(user.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error","出错啦!!!");
			return result;
		}
		userAccessMapper.saveUserAccess(userAccess);
		result.put("status",1);
		return result;
	}
	
	/**
	 * 用户修改登录密码
	 * @param param password 当前密码 newPassword 新密码 confirmPassword 确认密码
	 * @return
	 */
	@PutMapping(value="/user/password")
	public ObjectNode updatePassword(@RequestBody JsonNode param,HttpServletRequest req) {
		ObjectNode result = NodeUtil.create();
		String token = req.getHeader("token");
		try {
			User user = TokenUtil.verifyUser(token, SECERT);
			if(null == user) {
				result.put("status", 2);
				result.put("msg", "token失效，请登录!!!");
				return result;
			}
			String password = param.has("password") ? param.get("password").asText() : "";
			if(StringUtil.isBlank(password) || !password.equals(user.getPassword())) {
				result.put("status", 2);
				result.put("msg", "当前密码输入有误，请重新输入");
				return result;
			}
			String newPassword = param.has("newPassword") ? param.get("newPassword").asText() : "";
			String comfirmPassword = param.has("comfirmPassword") ? param.get("comfirmPassword").asText() : "";
			if(StringUtil.isBlank(newPassword) || StringUtil.isBlank(comfirmPassword) || !newPassword.equals(comfirmPassword) ) {
				result.put("status", 2);
				result.put("msg","新密码或重复输入的新密码为空，或两次输入的密码不一致");
				return result;
			}
			user.setPassword(newPassword);
			userMapper.updateUser(user);
			String newToken = TokenUtil.token(SECERT, user, expireMinutes);
			result.put("token",newToken);
			user.setPassword(null);
			result.putPOJO("user", user);
			result.put("status", 1);
		} catch (Exception e) {
			result.put("error","error");
			e.printStackTrace();
			return result;
		}
		return result;
	}
}
