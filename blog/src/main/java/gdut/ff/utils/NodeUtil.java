package gdut.ff.utils;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @author liuffei
 * @date 2018-01-25
 */
public class NodeUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static ObjectNode create() {
		return objectMapper.createObjectNode();
	}
	
	public static  ArrayNode createArr() {
		return objectMapper.createArrayNode();
	}
	
	public static ArrayNode transFromList(List list) {
		return objectMapper.convertValue(list, ArrayNode.class);
	}

}
