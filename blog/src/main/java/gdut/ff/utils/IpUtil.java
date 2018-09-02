package gdut.ff.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import gdut.ff.domain.Ip;

@Service
public class IpUtil {

	@Value("${api.mob.key}")
	private String mobKey;
	
	@Value("${api.mob.ip.address}")
	private String mobIpAddress;
	
	@Value("${api.mob.ip.method}")
	private String mobIpMethod;
	
	/**
	 * 查询的IP地址
	 * @param queryIpAddress
	 */
	public Ip queryIpDetail(String queryIpAddress) {
		//http://apicloud.mob.com/ip/query?key=appkey&ip=222.73.199.34
		String requestUrl = mobIpAddress + "?key=" + mobKey + "&ip="+queryIpAddress;
		String result = HttpUtil.httpRequest(requestUrl);
		JSONObject data = (JSONObject) JSONObject.parse(result);
		String retCode = data.getString("retCode");
		if("200".equals(retCode)) {
			JSONObject detail = data.getJSONObject("result");
			Ip ip = JSONObject.toJavaObject(detail, Ip.class);
			return ip;
		}
		return null;
	}
	
	
}
