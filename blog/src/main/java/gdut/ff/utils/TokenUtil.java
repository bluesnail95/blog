package gdut.ff.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

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
		
		return JWT.create()
				.withHeader(header)
				//.withClaim("loginName",user.getLoginName())
				.withClaim("email", user.getEmail())
				.withExpiresAt(expireDate)
				.withIssuedAt(iatDate)
				.sign(Algorithm.HMAC256(secret));
	}
	
	/**
	 * 
	 * @param token
	 * @param secert 密钥
	 * @return
	 * @throws Exception
	 */
	public static User verifyUser(String token,String secert) throws Exception{
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secert)).build();
		DecodedJWT jwt = null;
		try {
			jwt = verifier.verify(token);
			Map<String,Claim> claims = jwt.getClaims();
			User user = new User();
			user.setEmail(claims.get("email").asString());
			//需要处理login_name与loginName的映射问题
			//user.setLoginName(claims.get("loginName").asString());
			return user;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
