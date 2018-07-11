package gdut.ff.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 用户访问记录表
 * @author liuffei
 * @date 2018-04-03 update
 */
@Component
public class UserAccess implements Serializable {
	private static final long serialVersionUID = 1L;

	//主键
	private int id;
	
	//具有业务意义的标识
	private String userAccessId;

	//分类
	private String classification;

	//标题
	private String title;

	//请求路径
	private String url;

	//访问网站类型
	private int websiteType;
	
	//用户
	private String userId;
	
	//创建时间
	private Date gmtCreate;
	
	//修改时间
	private Date gmtModified;

	public UserAccess() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassification() {
		return this.classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getWebsiteType() {
		return this.websiteType;
	}

	public void setWebsiteType(int websiteType) {
		this.websiteType = websiteType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserAccess [id=" + id + ", userAccessId=" + userAccessId + ", classification=" + classification
				+ ", title=" + title + ", url=" + url + ", websiteType=" + websiteType + ", userId=" + userId
				+ ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}

}