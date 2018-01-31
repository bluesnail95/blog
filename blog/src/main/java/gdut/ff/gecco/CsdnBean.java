package gdut.ff.gecco;

import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * 
 * @author liuffei
 * @date 2017-01-31
 */
public class CsdnBean implements HtmlBean{
	
	private static final long serialVersionUID = 1L;
	
	@Attr("href")
	@HtmlField(cssPath = ".list_con > h2 > a")
	private String url;
	
	@Text
	@HtmlField(cssPath = ".list_con > h2 > a")
	private String title;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
