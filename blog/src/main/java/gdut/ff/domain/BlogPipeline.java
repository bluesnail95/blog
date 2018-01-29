package gdut.ff.domain;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

/**
 * 
 * @author liuffei
 *
 */
@PipelineName("blogPipeline")
public class BlogPipeline implements Pipeline<BlogBean> {

	@Override
	public void process(BlogBean bean) {
		System.out.println("begin");
		List<CnBlogsBean> blogs = bean.getBlogsBean();
		if(null != blogs && blogs.size() > 0) {
			for(int i = 0;i < blogs.size();i++) {
				CnBlogsBean blog = blogs.get(i);
				System.out.println("url:"+blog.getUrl()+"->title:"+blog.getTitle());
			}
		}
		
	}

}
