package gdut.ff.http;

import org.junit.Test;

import gdut.ff.domain.User;
import gdut.ff.utils.TokenUtil;

public class TestToken {
	
	public static String SECRET = "liuffei";
	
	@Test
	public void testToken() throws Exception {
		User user1 = new User();
		user1.setEmail("1192905443@163.com");
		String secret = "liuffei";
		String token = TokenUtil.token(secret,user1,30);
		System.out.println(token);
		User user2 = TokenUtil.verifyUser(token,secret);
		System.out.println(user2);
	}
	
}
