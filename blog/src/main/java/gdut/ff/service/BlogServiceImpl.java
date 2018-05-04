package gdut.ff.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gdut.ff.domain.Blog;
import gdut.ff.mapper.BlogMapper;

/**
 * 
 * @author liuffei
 * @date 2018-03-29
 */
@Service
@Transactional
public class BlogServiceImpl {
	
	@Autowired
	private BlogMapper blogMapper;

	@Transactional(readOnly = true)
	public Blog fingOneById(String id) {
		return blogMapper.findBlogById(id);
	}
	
	public int insertBlog(Blog blog) {
		blog.setId(UUID.randomUUID().toString());
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

	public int deleteBlogById(String id) {
		return blogMapper.deleteBlogById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Blog> findAllBlog(Blog blog) {
		return blogMapper.findAllBlog(blog);
	}
	
}
