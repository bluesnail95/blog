package gdut.ff.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import gdut.ff.domain.Blog;
import gdut.ff.domain.Category;
import gdut.ff.domain.Message;
import gdut.ff.domain.User;
import gdut.ff.service.BlogServiceImpl;
import gdut.ff.service.CategoryServiceImpl;
import gdut.ff.service.TagRelationServiceImpl;
import gdut.ff.service.UserServiceImpl;
import gdut.ff.utils.Constant;
import gdut.ff.utils.JsonUtil;
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
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private TagRelationServiceImpl tagRelationServiceImpl;
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
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
		if(blog.getId() > 0) {
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
		JSONObject result = JsonUtil.successJson();
		Blog blog = blogServiceImpl.fingOneById(id);
		if(null != blog) {
			//直接更新博客的点击率
			blogServiceImpl.updateClickCount(1, id);
			//查询博客的创建者的名称
			String creator = blog.getCreator();
			User user = userServiceImpl.findUserByUserId(creator);
			result.put("creatorName", user.getName());
			//查询博客的分类
			String categoryId = blog.getCategoryId();
			Category category = categoryServiceImpl.fingCategoryByCategoryId(categoryId);
			result.put("categoryName", category.getCategoryName());
			//查询博客的标签
			Map<String, Object> tagParam = new HashMap<String, Object>();
			tagParam.put("blogId", blog.getBlogId());
			tagParam.put("relationType", "1");
			List<Map<String, Object>> tags = tagRelationServiceImpl.findTagRelationDetail(tagParam);
			List<String> blogTag = new ArrayList<String>();
			if(null != tags && tags.size() > 0) {
				tags.forEach((Map<String, Object> tag) -> blogTag.add(tag.get("tag_name").toString()));
			}
			result.put("tags", blogTag.toArray());
		}
		result.put("content", blog);
		return result;
	}
	
	/**
	 * 查询指定的博客记录
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/blog/{id}")
	public JSONObject updateBlogById(@PathVariable String id, @RequestBody Blog blog, HttpServletRequest request) throws Exception {
		requireAuth(request);
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
	
	/**
	 *  获取最新的博客
	 * @return
	 */
	@GetMapping(value = "/blog/lastest")
	public JSONObject findLastestBlog() {
		Blog blog = blogServiceImpl.findLastestBlog();
		JSONObject result = JsonUtil.successJson();
		result.put("blog", blog);
		return result;
	}
	
}
