package gdut.ff.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author liuffei
 * 2018-04-12
 */
public class JsonUtil {

	public static JSONObject errorJson(String msg) {
		JSONObject result = new JSONObject();
		result.put("status", 0);
		result.put("msg", msg);
		return result;
	}
	
	public static JSONObject successJson() {
		JSONObject result = new JSONObject();
		result.put("status", 1);
		result.put("msg", "操作成功");
		return result;
	}
}
