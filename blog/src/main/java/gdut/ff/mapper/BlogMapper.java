package gdut.ff.mapper;

import java.util.List;

import gdut.ff.domain.Blog;

/**
 * 博客数据访问层接口
 * @author liuffei
 *
 */
public interface BlogMapper {

	public int insertBlog(Blog blog);
	
	public Blog findBlogById(String id);
	
	public int updateBlog(Blog blog);
	
	public int deleteBlogById(String id);
	
	public List<Blog> findAllBlog();
}
