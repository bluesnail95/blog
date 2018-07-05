package gdut.ff.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 用于创建博客的实体
 * @author liuffei
 * @date 2018-04-03 update
 */
public class Blog implements Serializable{

	private static final long serialVersionUID = 111111L;
	
	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 具有业务标识的博客id
	 */
	private String blogId;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 摘要
	 */
	private String summary;
	
	/**
	 * 正文
	 */
	private String content;
	
	/**
	 * 分类
	 */
	private String categoryId;
	
	/**
	 * 创建者
	 */
	private String creator;
	
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	
	/**
	 * 修改时间
	 */
	private Date gmtModified;
		
	/**
	 * 是否是草稿 0 是 1 否
	 */
	private String isDraft;
	
	/**
	 * 点击次数
	 */
	private Integer clickCount;
	
	/**
	 * 用于目录的图片
	 */
	private String img;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
	public String getBlogId() {
		return blogId;
	}

	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}

	public String getIsDraft() {
		return isDraft;
	}

	public void setIsDraft(String isDraft) {
		this.isDraft = isDraft;
	}
	
	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", blogId=" + blogId + ", title=" + title + ", summary=" + summary + ", content="
				+ content + ", categoryId=" + categoryId + ", creator=" + creator + ", gmtCreate=" + gmtCreate
				+ ", gmtModified=" + gmtModified + ", isDraft=" + isDraft + ", clickCount=" + clickCount + ", img="
				+ img + "]";
	}

}
