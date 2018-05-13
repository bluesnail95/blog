package gdut.ff.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import gdut.ff.domain.Blog;

/**
 * 博客数据访问层接口
 * @author liuffei
 *
 */
@CacheConfig(cacheNames = "blogs")
public interface BlogMapper {
	
	@CachePut(key = "#p0.title")
	public int insertBlog(Blog blog);
	
	@Cacheable
	public Blog findBlogById(String id);
	
	@CachePut(key = "#p0.title")
	public int updateBlog(Blog blog);
	
	@CacheEvict
	public int deleteBlogById(String id);
	
	@Cacheable
	public List<Blog> findAllBlog(Blog blog);
	
	@Cacheable
	public Blog findLastestBlog();
}
