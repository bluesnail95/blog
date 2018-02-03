package gdut.ff.clazz;

import java.lang.reflect.Field;

import org.junit.Test;

import gdut.ff.domain.User;

public class TestClassProperty {

	@Test
	public void testField() {
		Field[] fields = User.class.getDeclaredFields();
		String[] fieldArr = new String[fields.length];
        for(int i = 0;i < fields.length;i++) {
        	fieldArr[i] = fields[i].getName();
        }
        
        
	}
}
