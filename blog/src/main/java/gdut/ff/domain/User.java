package gdut.ff.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 用户实体层
 * @author liuffei
 * @date 2018年1月16日
 * @description
 */
@Component
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;
	
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
	private String img;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", birthday=" + birthday + ", loginName="
				+ loginName + ", password=" + password + ", signature=" + signature + ", img=" + img + "]";
	}
	
	
	
}
