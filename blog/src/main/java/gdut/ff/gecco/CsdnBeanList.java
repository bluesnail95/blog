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
@Gecco(matchUrl="https://www.csdn.net/", pipelines={"consolePipeline","csdnPipeline"})
public class CsdnBeanList implements HtmlBean{
	
	private static final long serialVersionUID = 1L;

	@HtmlField(cssPath="#feedlist_id > .clearfix")
	private List<CsdnBean> beanList;

	public List<CsdnBean> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<CsdnBean> beanList) {
		this.beanList = beanList;
	}

}
