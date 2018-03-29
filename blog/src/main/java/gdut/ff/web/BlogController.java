package gdut.ff.web;

import org.springframework.beans.factory.annotation.Autowired;
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
import gdut.ff.service.BlogServiceImpl;
import gdut.ff.utils.NodeUtil;

/**
 * 用于获取我的博客的数据
 * @author liuffei
 * @date 2018-01-25
 */
@RestController
@RequestMapping(value="/blog")
public class BlogController {
	
	@Autowired
	private BlogServiceImpl blogServiceImpl;
    
	/**
	 * 查询全部的用户
	 * @return
	 */
	@PostMapping(value = "/blog")
	public ObjectNode insertBlog(@RequestBody Blog blog) {
		ObjectNode result = NodeUtil.create();
		blogServiceImpl.insertBlog(blog);
		result.put("status", 1);
		return result;
	}
	
	@GetMapping(value = "/blog/{id}")
	public ObjectNode findBlogById(@PathVariable String id) {
		ObjectNode result = NodeUtil.create();
		Blog blog = blogServiceImpl.fingOneById(id);
		result.putPOJO("content", blog);
		result.put("status", 1);
		return result;
	}
	
	@PutMapping(value = "/blog/{id}")
	public ObjectNode updateBlogById(@PathVariable String id) {
		ObjectNode result = NodeUtil.create();
		
		result.put("status", 1);
		return result;
	}
	
	@DeleteMapping(value = "/blog/{id}")
	public ObjectNode deleteBlogById(@PathVariable String id) {
		ObjectNode result = NodeUtil.create();
		
		result.put("status", 1);
		return result;
	}
	
	@GetMapping(value = "/blogs")
	public ObjectNode finaAllBlogs() {
		ObjectNode result = NodeUtil.create();
		
		result.put("status", 1);
		return result;
	}
}
