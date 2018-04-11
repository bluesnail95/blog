package gdut.ff.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;


import gdut.ff.domain.User;
import gdut.ff.utils.TokenUtil;

/**
 * 
 * @author liuffei
 * @date 2018-02-10
 */
public class CommController {
	
	@Value("${blog.user.expire-minutes}")
	private int expireMinutes;
	           
	@Value("${blog.user.secret}")
	private String SECERT;
	
	/**
	 * 登录验证 2018-04-09
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public void requireAuth(HttpServletRequest request){
		String token = request.getHeader("token");
		User user = null;
		try {
			if(StringUtils.isBlank(token)) throw new Exception("请传入token");
			user = TokenUtil.verifyUser(token, SECERT);
			if(null == user) throw new Exception("用户登录失败，请重新登录");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public User getUser(HttpServletRequest request) {
		String token = request.getHeader("token");
		if(StringUtils.isBlank(token)) return null;
		User user = null;
		try {
			user = TokenUtil.verifyUser(token, SECERT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
