package gdut.ff.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @date 2018-07-05
 * @author liuffei 文章分类
 */
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String categoryId;

	private String categoryName;

	private Date gmtCreate;

	private Date gmtModified;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", gmtCreate="
				+ gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
	
}
