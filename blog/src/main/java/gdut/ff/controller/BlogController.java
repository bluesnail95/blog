package gdut.ff.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gdut.ff.domain.Blog;
import gdut.ff.exception.LoginException;
import gdut.ff.service.BlogServiceImpl;
import gdut.ff.utils.JsonUtil;
import gdut.ff.utils.NodeUtil;

/**
 * 用于获取我的博客的数据
 * @author liuffei
 * @date 2018-01-25
 */
@RestController
public class BlogController extends CommController{
	
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
	public JSONObject insertBlog(@RequestBody Blog blog,HttpServletRequest request) throws Exception {
		try {
			requireAuth(request);
			//添加blogId的值
			if(StringUtil.isNotBlank(blog.getId())) {
				blogServiceImpl.updateBlog(blog);
			}else {
				blogServiceImpl.insertBlog(blog);
			}
			return JsonUtil.successJson();
		}catch(LoginException ex) {
			ex.printStackTrace();
			return JsonUtil.loginJson();
		}catch(Exception e) {
			e.printStackTrace();
			return JsonUtil.errorJson(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/blog/{id}")
	@Cacheable(value="blog",key="#id")
	public JSONObject findBlogById(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		try {	
			Blog blog = blogServiceImpl.fingOneById(id);
			JSONObject result = JsonUtil.successJson();
			result.put("content", blog);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return JsonUtil.errorJson(e.getMessage());
		}
	}
	
	/**
	 * 查询指定的博客记录
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/blog/{id}")
	public JSONObject updateBlogById(@PathVariable String id, @RequestBody Blog blog, HttpServletRequest request) {
		try {
			blogServiceImpl.updateBlog(blog);
			return JsonUtil.successJson();
		}catch(Exception e) {
			e.printStackTrace();
			return JsonUtil.errorJson(e.getMessage());
		}
	}
	
	/**
	 * 删除博客记录
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/blog/{id}")
	public JSONObject deleteBlogById(@PathVariable String id, HttpServletRequest request) {
		try {
			requireAuth(request);
			blogServiceImpl.deleteBlogById(id);
			return JsonUtil.successJson();
		}catch(LoginException ex) {
			ex.printStackTrace();
			return JsonUtil.loginJson();
		}catch(Exception e) {
			e.printStackTrace();
			return JsonUtil.errorJson(e.getMessage());
		}
	}
	
	/**
	 * 查询全部博客
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/blogs")
	@Cacheable(value = "blogs")
	public JSONObject findAllBlogs() {
		try {
			List<Blog> blogs = blogServiceImpl.findAllBlog();
			JSONObject result = JsonUtil.successJson();
			result.put("blogs", blogs);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return JsonUtil.errorJson(e.getMessage());
		}
	}
	
}
