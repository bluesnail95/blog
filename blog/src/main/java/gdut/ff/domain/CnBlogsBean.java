package gdut.ff.domain;

import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

public class CnBlogsBean implements HtmlBean{
	
	@Attr("href")
	@HtmlField(cssPath = ".post_item_body > h3 > a")
	private String url;
	
	@Text
	@HtmlField(cssPath = ".post_item_body > h3 > a")
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
