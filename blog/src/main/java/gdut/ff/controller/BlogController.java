package gdut.ff.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
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
import gdut.ff.domain.Message;
import gdut.ff.exception.LoginException;
import gdut.ff.service.BlogServiceImpl;
import gdut.ff.utils.Constant;
import gdut.ff.utils.JsonUtil;
import gdut.ff.utils.NodeUtil;
import gdut.ff.websocket.BlogWebSocketServer;

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
	
	@Autowired
	private BlogWebSocketServer blogWebSocketServer;
    
	/**
	 * 添加博客记录
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/blog")
	public JSONObject insertBlog(@RequestBody Blog blog,HttpServletRequest request) throws Exception {
		requireAuth(request);
		//添加blogId的值
		if(null != blog.getId()) {
			blogServiceImpl.updateBlog(blog);
		}else {
			blogServiceImpl.insertBlog(blog);
		}
		//服务端推送消息
		JSONObject blogJson = new JSONObject();
		blogJson.put("blog", blog);
		Message message = new Message();
		message.setGmtCreate(new Date());
		message.setGmtModified(new Date());
		message.setMessageId("blog" + Constant.dateFormatNow("yyyyMMddHHmmss", new Date()));
		message.setMessageContent(blogJson.toJSONString());
		blogWebSocketServer.blogServerMessage(message);
		return JsonUtil.successJson();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/blog/{id}")
	public JSONObject findBlogById(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) {
		Blog blog = blogServiceImpl.fingOneById(id);
		if(null != blog) {
			if(Constant.blogCountMap.containsKey(blog.getId())) {
				Constant.blogCountMap.put(blog.getId(), Constant.blogCountMap.get(blog.getId()) + 1);
			}else {
				Constant.blogCountMap.put(blog.getId(), 1);
			}
		}
		JSONObject result = JsonUtil.successJson();
		result.put("content", blog);
		return result;
	}
	
	/**
	 * 查询指定的博客记录
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/blog/{id}")
	public JSONObject updateBlogById(@PathVariable String id, @RequestBody Blog blog, HttpServletRequest request) {
		blogServiceImpl.updateBlog(blog);
		return JsonUtil.successJson();
	}
	
	/**
	 * 删除博客记录
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@DeleteMapping(value = "/blog/{id}")
	public JSONObject deleteBlogById(@PathVariable Integer id, HttpServletRequest request) throws Exception {
		requireAuth(request);
		blogServiceImpl.deleteBlogById(id);
		return JsonUtil.successJson();
	}
	
	/**
	 * 查询全部博客
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/blogs")
	public JSONObject findAllBlogs(Blog blog) {
		List<Blog> blogs = blogServiceImpl.findAllBlog(blog);
		JSONObject result = JsonUtil.successJson();
		result.put("blogs", blogs);
		return result;
	}
	
	@GetMapping(value = "/blog/lastest")
	public JSONObject findLastestBlog() {
		Blog blog = blogServiceImpl.findLastestBlog();
		JSONObject result = JsonUtil.successJson();
		result.put("blog", blog);
		return result;
	}
	
}
