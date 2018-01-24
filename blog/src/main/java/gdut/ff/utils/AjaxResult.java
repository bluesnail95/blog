package gdut.ff.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 过滤返回结果工具类
 * @author liuffei
 * @date 2017年12月26日
 * @description
 */
public class AjaxResult {
	
	private JsonNode node = null;
	//白名单
	private List<String> whites = new ArrayList<String>();
	//黑名单
	private List<String> blacks = new ArrayList<String>();
	
	public AjaxResult(JsonNode node) {
		this.node = node;
	}
	
	/**
	 * 加入白名单属性
	 * @param props
	 * @return
	 */
	public AjaxResult whitesProps(String...props) {
		for(String prop:props) {
			if(whites.contains(prop)) continue;
			this.whites.add(prop);
		}
		return this;
	}
	
	/**
	 * 加入黑名单属性
	 * @param props
	 * @return
	 */
	public AjaxResult blacksProps(String...props) {
		for(String prop:props) {
			if(blacks.contains(prop)) continue;
			this.blacks.add(prop);
		}
		return this;
	}
	
	
	/**
	 * 过滤
	 * @return
	 */
	public JsonNode filter() {
	    //判断JsonNode是ObjectNode还是ArrayNode
		if(this.node instanceof ArrayNode) {
			node = (ArrayNode)node;
			ArrayNode copyNodes = ((ArrayNode) node).deepCopy();
			for(int i = 0;i < copyNodes.size();i++) {
				JsonNode copyNode = copyNodes.get(i);
				whitesFilter((ObjectNode)copyNode);
				blacksFilter((ObjectNode)copyNode);
			}
			return copyNodes;
		}else {
			node = (ObjectNode)node;
			ObjectNode copyNode = node.deepCopy();
			whitesFilter((ObjectNode)copyNode);
			blacksFilter((ObjectNode)copyNode);
			return copyNode;
		}		
	}
	
	/**
	 * 白名单过滤
	 * @param objectNode
	 * @return 判断objectNode中的属性是否在whites中，如果不存在，就把这个元素从objectNode中移除
	 */
	public void whitesFilter(ObjectNode objectNode) {
		if(this.whites.isEmpty()) return;
	    Iterator<String> iter = objectNode.fieldNames();
	    while(iter.hasNext()) {
	    	String fieldName = iter.next();
	    	if(!this.whites.contains(fieldName)) {
	    	    iter.remove();	
	    	}
	    }
	}
	
	/**
	 * 黑名单过滤
	 * @param objectNode
	 * @return 判断objectNode中的属性是否在blacks中，如果存在，就把这个元素从objectNode中移除
	 */
	public void blacksFilter(ObjectNode objectNode) {
		if(this.blacks.isEmpty()) return;
		Iterator<String> iter = objectNode.fieldNames();
		while(iter.hasNext()) {
			String fieldName = iter.next();
			if(this.blacks.contains(fieldName)) {
				iter.remove();
			}
		}
	}

}
