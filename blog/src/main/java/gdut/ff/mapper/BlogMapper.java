package gdut.ff.mapper;

import java.util.List;
import java.util.Map;

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
public interface BlogMapper {
	
	public int insertBlog(Blog blog);
	
	public Blog findBlogById(Integer id);
	
	public int updateBlog(Blog blog);
	
	public int deleteBlogById(Integer id);
	
	public List<Blog> findAllBlog(Blog blog);
	
	public Blog findLastestBlog();
	
	public void updateClickCount(Map<String, Integer> param);
}
