package gdut.ff.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import gdut.ff.domain.Blog;
import gdut.ff.service.BlogServiceImpl;

/**
 * 测试博客的增删改查接口
 * @author bluesnail95
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogMapperTest {
	
	@Autowired
	private BlogServiceImpl blogServiceImpl;
	
	@Autowired
	private BlogMapper blogMapper;
	
	@Autowired CacheManager cacheManager;
	
	@Test
	public void testGetBlog() {
		//Blog blog = blogServiceImpl.fingOneById("bb38eab4-2f04-4560-b8c2-6b4a625e7ede");
		//System.out.println(blog);
	}
	
	@Test
	public void testInsertBlog() {
		Blog blog = new Blog();
		//blog.setId(UUID.randomUUID().toString());
		blog.setContent("<p>hello,world!!!</p>");
		blog.setSummary("一个简单的测试");
		blog.setTitle("测试");
		blog.setCreator("liuffei");
		blog.setGmtCreate(new Date());
		blog.setGmtModified(new Date());
		blogServiceImpl.insertBlog(blog);
		//blogServiceImpl.deleteBlogById("bb38eab4-2f04-4560-b8c2-6b4a625e7ede");
		List<Blog> blogs = blogServiceImpl.findAllBlog(null);
		if(null != blogs && blogs.size() > 0) {
			for(int i = 0;i < blogs.size();i++) {
				System.out.println(blogs.get(i));
			}
		}
	}
	
	@Test
	public void testUpdateBlog() {
		Blog blog = new Blog();
		//blog.setId("bb38eab4-2f04-4560-b8c2-6b4a625e7ede");
		blog.setContent("<p>hello,world!!!</p>");
		blog.setSummary("一个简单的测试");
		blog.setTitle("测试");
		blog.setCreator("liuffei");
		blog.setGmtCreate(new Date());
		blog.setGmtModified(new Date());
		blogServiceImpl.updateBlog(blog);
	}
	

}
