package gdut.ff.clazz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class TestDate {
	
	@Test
	public void testWeekDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		System.out.println(sdf.format(calendar.getTime()));
		
		calendar.add(calendar.DATE, -7);
		System.out.println(sdf.format(calendar.getTime()));
	}

}
