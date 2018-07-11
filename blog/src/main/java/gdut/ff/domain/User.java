package gdut.ff.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 用户实体层
 * @author liuffei
 * @date 2018-04-03 update
 * @description
 */
@Component
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int id;
	
	//用户的业务标识
	private String userId;
	
	//用户名
	private String name;
	
	//邮箱
	private String email;
	
	//出生日期
	private Date birthday;
	
	//登录名
	private String loginName;
	
	//密码
	private String password;
	
	//个性签名
	private String signature;

	//头像
	private Object img;
	
	//是否是管理员 '0' 是 '1' 否
	private String isAdmin;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Object getImg() {
		return img;
	}

	public void setImg(Object img) {
		this.img = img;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
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
		return "User [id=" + id + ", userId=" + userId + ", name=" + name + ", email=" + email + ", birthday="
				+ birthday + ", loginName=" + loginName + ", password=" + password + ", signature=" + signature
				+ ", img=" + img + ", isAdmin=" + isAdmin + ", gmtCreate=" + gmtCreate + ", gmtModified="
				+ gmtModified + "]";
	}
	
}
