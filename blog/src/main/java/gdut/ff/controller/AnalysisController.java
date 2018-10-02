package gdut.ff.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gdut.ff.domain.User;
import gdut.ff.redis.RedisService;
import gdut.ff.service.UserAccessServiceImpl;
import gdut.ff.utils.JsonUtil;
import gdut.ff.utils.NodeUtil;
import gdut.ff.utils.TokenUtil;

/**
 * 统计分析
 * @author liuffei
 *
 */
@RestController
@RequestMapping(value = "/analysis")
public class AnalysisController {
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 在线人数
	 * @return
	 */
	@RequestMapping(value = "onlineUser")
	public JSONObject onlineUserCount() {
		Set<Object> ips = redisService.getSetValue("ips");
		JSONObject result = new JSONObject();
		result.put("ips", ips);
		result.put("count", ips.size());
		return result;
	}
	
	
}
