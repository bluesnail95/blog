package gdut.ff.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签
 * @author liuffei
 * @date 2018-07-10
 *
 */
public class Tag implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//主键
	private int id;
	
	//标签的业务标识
	private String tagId;
	
	//标签名称
	private String tagName;
	
	//创建时间
	private Date gmtCreate;
	
	//修改时间
	private Date gmtModified;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
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
		return "Tag [id=" + id + ", tagId=" + tagId + ", tagName=" + tagName + ", gmtCreate=" + gmtCreate
				+ ", gmtModified=" + gmtModified + "]";
	}
}
