package gdut.ff.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gdut.ff.domain.Blog;
import gdut.ff.mapper.BlogMapper;
import gdut.ff.utils.Constant;

import javax.annotation.Resource;

/**
 * 
 * @author liuffei
 * @date 2018-03-29
 */
@Service
@Transactional
public class BlogServiceImpl {
	
	@Resource
	private BlogMapper blogMapper;

	@Transactional(readOnly = true)
	public Blog fingOneById(Integer id) {
		return blogMapper.findBlogById(id);
	}
	
	public int insertBlog(Blog blog) {
		blog.setBlogId(UUID.randomUUID().toString());
		blog.setGmtCreate(new Date());
		blog.setGmtModified(new Date());
		blog.setIsDraft("0");
		return blogMapper.insertBlog(blog);
	}
	
	public int updateBlog(Blog blog) {
		blog.setGmtModified(new Date());
		return blogMapper.updateBlog(blog);
	}

	public int deleteBlogById(Integer id) {
		return blogMapper.deleteBlogById(id);
	}

	@Transactional(readOnly = true)
	public int countBlog(Blog blog) {
		return blogMapper.countBlog(blog);
	}

	@Transactional(readOnly = true)
	public List<Blog> findAllBlog(Blog blog) {
		return blogMapper.findAllBlog(blog);
	}
	
	@Transactional(readOnly = true)
	public Blog findLastestBlog() {
		return blogMapper.findLastestBlog();
	}
	
	@Async
	public void updateClickCount(Integer clickCount, Integer id) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("clickCount", clickCount);
		param.put("id", id);
		blogMapper.updateClickCount(param);
	}
}
