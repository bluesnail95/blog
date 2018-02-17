package gdut.ff.domain;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 用户访问记录表
 * @author liuffei
 * @date 2018-02-07
 */
@Component
public class UserAccess implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 分类
	 */
	private String classification;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 请求路径
	 */
	private String url;

	/**
	 * 访问网站类型
	 */
	private int websiteType;
	
	private String userId;

	public UserAccess() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassification() {
		return this.classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

}