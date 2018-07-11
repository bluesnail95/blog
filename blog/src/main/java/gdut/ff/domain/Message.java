package gdut.ff.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *实体
 *@author liuffei
 *@date 2018-06-07
 */
public class Message implements Serializable{

    private final static long serialVersionUID = 1L;

    //主键
    private int id;
		
    //业务标识
    private String messageId;
		
    //通知内容
    private String messageContent;
		
    //创建时间
    private Date gmtCreate;
		
    //更新时间
    private Date gmtModified;
	
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
	
    public void setMessageId(String messageId){
        this.messageId = messageId;
    }
    
    public String getMessageId(){
        return this.messageId;
    }
	
    public void setMessageContent(String messageContent){
        this.messageContent = messageContent;
    }
    
    public String getMessageContent(){
        return this.messageContent;
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
		return "Message [id=" + id + ", messageId=" + messageId + ", messageContent=" + messageContent + ", gmtCreate="
				+ gmtCreate + ", gmtModified=" + gmtModified + "]";
	}

}