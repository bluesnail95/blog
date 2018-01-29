package gdut.ff.domain;

import java.util.List;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(matchUrl="https://www.cnblogs.com/", pipelines={"consolePipeline","blogPipeline"})
public class BlogBean implements HtmlBean{
	
	private static final long serialVersionUID = 1L;

	@HtmlField(cssPath="#post_list > .post_item")
	private List<CnBlogsBean> blogsBean;

	public List<CnBlogsBean> getBlogsBean() {
		return blogsBean;
	}

	public void setBlogsBean(List<CnBlogsBean> blogsBean) {
		this.blogsBean = blogsBean;
	}
	
	
	
	
}
