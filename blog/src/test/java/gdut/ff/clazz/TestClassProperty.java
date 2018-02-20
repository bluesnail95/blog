package gdut.ff.clazz;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.JsonNode;

import gdut.ff.domain.User;
import gdut.ff.utils.NodeUtil;

public class TestClassProperty {

	@Test
	public void testField() {
		Field[] fields = User.class.getDeclaredFields();
		String[] fieldArr = new String[fields.length];
        for(int i = 0;i < fields.length;i++) {
        	fieldArr[i] = fields[i].getName();
        }
        
	}
	
	@Test
	public void testPath() throws FileNotFoundException {
		String root = ResourceUtils.getURL("classpath:").getPath();
		System.out.println(root);
		
		String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		System.out.println(path);
	}
	
	@Test
	public void testJsonNode() {
	    User user = new User();
	    user.setEmail("123@qq.com");
	    JsonNode jsonNode = NodeUtil.transFromPOJO(user);
	    System.out.println(jsonNode);
	}

}
