package gdut.ff.utils;

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
	
	public static  ArrayNode crete() {
		return objectMapper.createArrayNode();
	}

}
