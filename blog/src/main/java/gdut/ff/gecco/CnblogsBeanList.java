package gdut.ff.gecco;

import java.util.List;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * 
 * @author liuffei
 * @date 2017-01-31
 */
@Gecco(matchUrl="https://www.cnblogs.com/", pipelines={"cnblogsPipeline"})
public class CnblogsBeanList implements HtmlBean{
	
	private static final long serialVersionUID = 1L;

	@HtmlField(cssPath="#post_list > .post_item")
	private List<CnblogsBean> beanList;

	public List<CnblogsBean> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<CnblogsBean> beanList) {
		this.beanList = beanList;
	}

}
