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

import com.fasterxml.jackson.databind.node.ObjectNode;

import gdut.ff.domain.Blog;
import gdut.ff.domain.User;
import gdut.ff.service.BlogServiceImpl;
import gdut.ff.utils.NodeUtil;
import gdut.ff.utils.TokenUtil;

/**
 * 用于获取我的博客的数据
 * @author liuffei
 * @date 2018-01-25
 */
@RestController
public class BlogController {
	
	@Autowired
	private BlogServiceImpl blogServiceImpl;
	
	@Value("${blog.user.secret}")
	private String SECERT;
    
	/**
	 * 添加博客记录
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/blog")
	public ObjectNode insertBlog(@RequestBody Blog blog,HttpServletRequest request) throws Exception {
		ObjectNode result = NodeUtil.create();
		//TODO:要求具有管理员权限的用户才能发表博客
		/*
		String token = request.getHeader("token");
		if(StringUtil.isNotBlank(token)) {
			User user = TokenUtil.verifyUser(token,SECERT);
			if(null == user) {
				result.put("status",2);
				result.put("message","请确认登录!!!");
				return result;
			}
		}
		*/
		try {
			if(StringUtil.isNotBlank(blog.getId())) {
				blogServiceImpl.updateBlog(blog);
			}else {
				blogServiceImpl.insertBlog(blog);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		result.put("status", 1);
		return result;
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/blog/{id}")
	public ObjectNode findBlogById(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		ObjectNode result = NodeUtil.create();
		try {
			Blog blog = blogServiceImpl.fingOneById(id);
			result.putPOJO("content", blog);
			result.put("status", 1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询指定的博客记录
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/blog/{id}")
	public ObjectNode updateBlogById(@PathVariable String id, @RequestBody Blog blog) {
		ObjectNode result = NodeUtil.create();
		try {
			blogServiceImpl.updateBlog(blog);
			result.put("status", 1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除博客记录
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/blog/{id}")
	public ObjectNode deleteBlogById(@PathVariable String id) {
		ObjectNode result = NodeUtil.create();
		try {
			blogServiceImpl.deleteBlogById(id);
			result.put("status", 1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询全部博客
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/blogs")
	public ObjectNode finaAllBlogs() {
		ObjectNode result = NodeUtil.create();
		try {
			List<Blog> blogs = blogServiceImpl.findAllBlog();
			result.putPOJO("blogs", blogs);
			result.put("status", 1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
