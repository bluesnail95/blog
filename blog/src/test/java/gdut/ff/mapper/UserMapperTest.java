package gdut.ff.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gdut.ff.domain.User;
import gdut.ff.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	
	@Autowired
	private UserMapper userMapper;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void testFindAllXml() {
		List<User> users = userMapper.findAll();
		if(null != users && users.size() > 0) {
			for(int i = 0;i < users.size();i++) {
				User user = users.get(i);
				System.out.println("id = "+user.getId()+",name = "+user.getName()
				+",birthday="+sdf.format(user.getBirthday())+",loginName="+user.getLoginName()
				+",passWord="+user.getPassWord()+",signature="+user.getSignature());
			}
		}
	}
	
	@Test
	public void testFindOneUser() {
		long id = 1;
		User user = userMapper.fingOneById(id);
		System.out.println("id = "+user.getId()+",name = "+user.getName()
		+",birthday="+sdf.format(user.getBirthday())+",loginName="+user.getLoginName()
		+",passWord="+user.getPassWord()+",signature="+user.getSignature());
	}
	
	@Test
	public void insertOneUser() throws ParseException {
		String birthdate = "1995-08-21";
		String name = "liuffei";
		String img = "/fileupload/img/img01.jpg";
		User user = new User();
		user.setBirthday(sdf.parse(birthdate));
		user.setImg(img);
		user.setLoginName("liuffei");
		user.setPassWord("123");
		user.setSignature("坚持做自己");
		user.setName("liuffei");
		userMapper.saveUser(user);
	}
	
	@Test
	public void updateOneUser() throws ParseException {
		String birthdate = "1995-06-15";
		String name = "hequn";
		String img = "/fileupload/img/img01.jpg";
		User user = new User();
		user.setBirthday(sdf.parse(birthdate));
		user.setImg(img);
		user.setLoginName("hequn");
		user.setPassWord("123");
		user.setSignature("坚持做自己");
		user.setName("hequn");
		userMapper.saveUser(user);
	}
}
