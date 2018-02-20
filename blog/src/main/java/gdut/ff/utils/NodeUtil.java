package gdut.ff.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
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
		ObjectNode objectNode = objectMapper.createObjectNode();
		objectNode.put("status", 0);
		return objectNode;
	}
	
	public static  ArrayNode createArr() {
		return objectMapper.createArrayNode();
	}
	
	/**
	 * 将List转成ArrayNode
	 * @param list
	 * @return
	 */
	public static ArrayNode transFromList(List list) {
		return objectMapper.convertValue(list, ArrayNode.class);
	}
	
	/**
	 * 将POJO转成JsonNode
	 * @param pojo
	 * @return
	 */	
	public static JsonNode transFromPOJO(Object pojo) {
		return objectMapper.convertValue(pojo, JsonNode.class);
	}
	
	/**
	 * 将Map转成JsonNode
	 * @param map
	 * @return
	 */
	public static JsonNode transFromMap(Map map) {
		return objectMapper.convertValue(map, JsonNode.class);
	}
	
	/**
	 * 转成Bean实体
	 * @param fromValue
	 * @param toClass
	 * @return
	 */
	public static <T> T transToPOJO(JsonNode fromValue,Class<T> toClass) {
		Field[] fields = toClass.getDeclaredFields();
		String[] fieldArr = new String[fields.length];
        for(int i = 0;i < fields.length;i++) {
        	fieldArr[i] = fields[i].getName();
        }
		JsonNode filterValue = new AjaxResult(fromValue).whitesProps(fieldArr).filter();
		return objectMapper.convertValue(filterValue,toClass);
	}
	
	/**
	 * 将输入的JsonNode转成Map
	 * @param fromValue
	 * @return
	 */
	public static Map transToMap(JsonNode fromValue) {
		return objectMapper.convertValue(fromValue, Map.class);
	}

	
}
