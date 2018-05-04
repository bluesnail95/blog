package gdut.ff.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件类
 * @author liuffei
 * @date 2018-04-28
 *
 */
public class File implements Serializable{

    private final static long serialVersionUID = 1L;

    //主键
    private String id;
		
    //业务标识
    private String fileId;
		
    //文件下载的次数
    private int count;
		
    //文件名
    private String fileName;
		
    //文件介绍
    private String fileIntroduction;
		
    //文件的预览图片
    private String groupName;
		
    //文件路径
    private String remoteFileName;
		
    //创建时间
    private Date gmtCreate;
		
    //修改时间
    private Date gmtModified;
		
    public void setId(String id){
        this.id = id;
    }
    
    public String getId(){
        return this.id;
    }
	
    public void setFileId(String fileId){
        this.fileId = fileId;
    }
    
    public String getFileId(){
        return this.fileId;
    }
	
    public void setCount(int count){
        this.count = count;
    }
    
    public int getCount(){
        return this.count;
    }
	
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    
    public String getFileName(){
        return this.fileName;
    }
	
    public void setFileIntroduction(String fileIntroduction){
        this.fileIntroduction = fileIntroduction;
    }
    
    public String getFileIntroduction(){
        return this.fileIntroduction;
    }
	
    public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRemoteFileName() {
		return remoteFileName;
	}

	public void setRemoteFileName(String remoteFileName) {
		this.remoteFileName = remoteFileName;
	}

	public void setGmtCreate(Date gmtCreate){
        this.gmtCreate = gmtCreate;
    }
    
    public Date getGmtCreate(){
        return this.gmtCreate;
    }
	
    public void setGmtModified(Date gmtModified){
        this.gmtModified = gmtModified;
    }
    
    public Date getGmtModified(){
        return this.gmtModified;
    }

	@Override
	public String toString() {
		return "File [id=" + id + ", fileId=" + fileId + ", count=" + count + ", fileName=" + fileName
				+ ", fileIntroduction=" + fileIntroduction + ", groupName=" + groupName + ", remoteFileName="
				+ remoteFileName + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + "]";
	}

    
}