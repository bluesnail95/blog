package gdut.ff.utils;

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
	
}
