package gdut.ff.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *实体 存储用户访问的IP地址
 *@author liuffei
 *@date 
 */
public class Ip implements Serializable{

    private final static long serialVersionUID = 1L;

    //主键
    private int id;
		
    //ip地址
    private String ip;
		
    //国家
    private String country;
		
    //省
    private String province;
		
    //城市
    private String city;
		
    //区
    private String district;
		
    //创建时间
    private Date gmtCreate;
		
    //修改时间
    private Date gmtModified;
		
	
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return this.id;
    }
	
    public void setIp(String ip){
        this.ip = ip;
    }
    
    public String getIp(){
        return this.ip;
    }
	
    public void setCountry(String country){
        this.country = country;
    }
    
    public String getCountry(){
        return this.country;
    }
	
    public void setProvince(String province){
        this.province = province;
    }
    
    public String getProvince(){
        return this.province;
    }
	
    public void setCity(String city){
        this.city = city;
    }
    
    public String getCity(){
        return this.city;
    }
	
    public void setDistrict(String district){
        this.district = district;
    }
    
    public String getDistrict(){
        return this.district;
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
	
}