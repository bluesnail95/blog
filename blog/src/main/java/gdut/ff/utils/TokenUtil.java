package gdut.ff.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.DelayQueue;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gdut.ff.domain.User;

/**
 * http://blog.csdn.net/u011277123/article/details/78918390
 * @author liuffei
 * @date 2018-02-04
 */
public class TokenUtil {
	
	/**
	 * 获取用户登录token
	 * @param secret 密钥
	 * @param user 用户信息
	 * @return
	 * @throws Exception
	 */
	public static String token(String secret,User user,int minutes) throws Exception{
		
		Date iatDate = new Date();
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.MINUTE,minutes);
		Date expireDate = nowTime.getTime();
		
		Map<String,Object> header = new HashMap<String,Object>();
		header.put("alg", "HS256");
		header.put("typ", "JWT");
		
		String token =  JWT.create()
				.withHeader(header)
				.withClaim("loginName",user.getLoginName())
				.withClaim("password", user.getPassword())
				.withClaim("email", user.getEmail())
				.withClaim("id", user.getId())
				.withExpiresAt(expireDate)
				//.withIssuedAt(iatDate)
				.sign(Algorithm.HMAC256(secret));
		
		return token;
	}
	
	/**
	 * 
	 * @param token
	 * @param secert 密钥
	 * @return
	 * @throws Exception
	 */
	public static User verifyUser(String token,String secert) throws Exception{
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secert)).acceptExpiresAt(60).build();
		DecodedJWT jwt = verifier.verify(token);
		Map<String,Claim> claims = jwt.getClaims();
		User user = new User();
		user.setEmail(claims.get("email").asString());
		user.setId(claims.get("id").asInt());
		//需要处理login_name与loginName的映射问题
		user.setLoginName(claims.get("loginName").asString());
		user.setPassword(claims.get("password").asString());
		return user;
	}

	
}
