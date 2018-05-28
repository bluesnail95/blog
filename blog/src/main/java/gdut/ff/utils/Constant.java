package gdut.ff.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * 定义一些共用的常量
 * @author liuffei
 *
 */

@Component
public class Constant {

	public static ArrayNode cnblogsNodes = NodeUtil.createArr();
	
	public static ArrayNode csdnNodes = NodeUtil.createArr();
	
	public static ArrayNode oscNodes = NodeUtil.createArr();
	
	/**
	 * 格式化日期
	 */
	public static SimpleDateFormat dateFormat = null;
	
	public static String dateFormatNow(String format,Date date) {
		dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	/**
	 * 存储博客的点击率 key是blog的id,value是一段时间的点击数，每天指定的时间将点击数刷新到数据库中
	 */
	public static Map<Integer,Integer> blogCountMap = new HashMap<Integer,Integer>();
	
	
}
