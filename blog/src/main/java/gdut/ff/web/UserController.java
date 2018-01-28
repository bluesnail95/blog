package gdut.ff.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gdut.ff.domain.User;
import gdut.ff.mapper.UserMapper;
import gdut.ff.utils.NodeUtil;

@RestController
public class UserController {
	
	@Autowired
	private UserMapper userMapper;
	
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
	
	@PostMapping(value = "/upload/user")
	public ObjectNode singleFileUpload(@RequestParam("file")MultipartFile file,HttpServletRequest req) {
		ObjectNode result = NodeUtil.create();
		if(file.isEmpty()) {
			result.put("message","Please select a file to upload");
		    return result;
		}
		try {
			byte[] bytes = file.getBytes();
			String root = ResourceUtils.getURL("classpath:").getPath() + "/upload";
			File parent = new File(root);
			if(!parent.exists()) {
				parent.mkdirs();
			}
			BufferedOutputStream bos =    
                    new BufferedOutputStream(new FileOutputStream(new File(parent,file.getOriginalFilename())));    
			bos.write(bytes);    
			bos.close();
		    result.put("message","successfully upload "+file.getOriginalFilename());
		    
		}catch(IOException e) {
			e.printStackTrace();
			result.put("error","error");
		}
		return result;
	}

}
