package gdut.ff.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户实体层
 * @author liuffei
 * @date 2018年1月16日
 * @description
 */
@Entity
@Table
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	//用户名
	@Column
	private String name;
	
	//出生日期
	@Column
	private Date birthday;
	
	//登录名
	@Column(name = "login_name")
	private String loginName;
	
	//密码
	@Column(name = "pass_word")
	private String passWord;
	
	//个性签名
	@Column
	private String signature;

	//图片
	@Column
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

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
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
	
	

}
