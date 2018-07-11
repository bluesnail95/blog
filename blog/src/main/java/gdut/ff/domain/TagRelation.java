package gdut.ff.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签和文件/博客      多对一的关系
 * @date 2018-07-10
 * @author liuffei
 *
 */
public class TagRelation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//主键
	private int id;
	
	//业务标识
	private String relationId;
	
	//标签属于的类型 1 表示blog 2表示file
	private String relationType;
	
	//blogId或是fileId
	private String typeId;
	
	//标签的业务标识
	private String tagId;
	
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

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
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
		return "TagRelation [id=" + id + ", relationId=" + relationId + ", relationType=" + relationType + ", typeId="
				+ typeId + ", tagId=" + tagId + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}
}
