package gdut.ff.web;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Html;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

@Gecco(matchUrl="https://github.com/{user}/{project}",pipelines="consolePipeline")
public class MyGithubController implements HtmlBean {
	
	private static final long serialVersionUID = 1L;
	
	@RequestParameter("user")
	private String user;
	
	@RequestParameter("project")
	private String project;
	
	@Text
	@HtmlField(cssPath=".repository-meta-cotent")
	private String title;

	@Text
	@HtmlField(cssPath=".pagehead-actions li:nth-child(2) .social-count")
	private int star;
	
	@Text
	@HtmlField(cssPath=".pagehead-actions li:nth-child(3) .social-count")
	private int fork;
	
	@Html
	@HtmlField(cssPath=".entry-content")
	private String readme;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getFork() {
		return fork;
	}

	public void setFork(int fork) {
		this.fork = fork;
	}

	public String getReadme() {
		return readme;
	}

	public void setReadme(String readme) {
		this.readme = readme;
	}
	
	public static void main(String[] args) {
		GeccoEngine.create()
		.classpath("gdut.ff.web")
		//.start("https://github.com/xtuhcy/gecco")
		.start("https://github.com/bluesnail95/blog")
		.thread(1)
		.interval(2000)
		.start();
	}
	
}
