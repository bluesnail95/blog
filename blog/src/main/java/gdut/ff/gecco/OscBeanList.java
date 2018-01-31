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
@Gecco(matchUrl="https://www.oschina.net/", pipelines={"oscPipeline"})
public class OscBeanList implements HtmlBean{
	
	private static final long serialVersionUID = 1L;

	@HtmlField(cssPath="#v_news .news")
	private List<OscBean> beanList;

	public List<OscBean> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<OscBean> beanList) {
		this.beanList = beanList;
	}

}
