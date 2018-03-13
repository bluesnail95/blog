package gdut.ff.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gdut.ff.domain.User;
import gdut.ff.service.UserAccessServiceImpl;
import gdut.ff.utils.NodeUtil;
import gdut.ff.utils.TokenUtil;

/**
 * 对用户，博客网站进行一些简单的分析
 * @author liuffei
 *
 */
@RestController
public class AnalysisController {
	
	@Autowired
	private UserAccessServiceImpl userAccessServiceImpl;
	
	@Value("${blog.user.secret}")
	private String SECERT;

	/**
	 * 分析csdn、cnblogs、osc哪个网站的博客比较受欢迎
	 * @return
	 */
	@PostMapping(value = "/analysis/website")
	public ObjectNode blogWebsiteAnalysis(@RequestBody JsonNode param) {
	    ObjectNode result = NodeUtil.create();
	    List<Map<String,String>> data = userAccessServiceImpl.blogWebsiteAnalysis(NodeUtil.transToMap(param));
	    result.putPOJO("analysis", NodeUtil.transFromList(data));
	    result.put("status", 1);
	    return result;
	}
	
	/**
	 * 分析当前用户比较喜欢哪个网站的内容
	 * @param param
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/analysis/user")
	public ObjectNode blogUserAnalysis(@RequestBody JsonNode param,HttpServletRequest req) {
		ObjectNode result = NodeUtil.create();
		String token = req.getHeader("token");
		try {
			User user = TokenUtil.verifyUser(token, SECERT);
			//校验用户是否登录
			if(null == user) {
				result.put("status", 2);
				result.put("msg","请登录！！！");
				return result;
			}
			String userId = user.getId();
			Map<String,Object> params = NodeUtil.transToMap(param);
			params.put("userId", userId);
			List<Map<String,String>> data = userAccessServiceImpl.blogWebsiteAnalysis(params);
			if(null != data && data.size() > 0) {
				result.putPOJO("analysis", NodeUtil.transFromList(data));
			}else {
				result.put("status",3);
				result.put("msg", "暂时不存在用户数据");
				return result;
			}
		} catch (Exception e) {
			result.put("error","error");
			e.printStackTrace();
			return result;
		}
		result.put("status", 1);
		return result;
	}
	
	/**
	 * 统计一周内的访问量
	 * @param param
	 * @param req
	 * @return
	 */
	@PostMapping(value = "/analysis/website/week")
	public ObjectNode analysisGroupByDateAndWebsiteType(@RequestBody JsonNode param,HttpServletRequest req) {
		ObjectNode result = NodeUtil.create();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String maxDate = sdf.format(calendar.getTime());
		calendar.add(calendar.DATE, -7);
		String minDate = sdf.format(calendar.getTime());
		
		Map<String,String> params = NodeUtil.transToPOJO(param, Map.class);
		params.put("minDate", minDate);
		params.put("maxDate", maxDate);
		List<Map<String,Object>> data = userAccessServiceImpl.analysisGroupByDateAndWebsiteType(params);
		
		result.putPOJO("data",data);
		result.put("status", 1);
		
		return result;
	}
	
	/**
	 * 查找登录用户的前10条浏览记录
	 */
	@GetMapping(value = "/analysis/history")
	public ObjectNode history() {
		ObjectNode result = NodeUtil.create();
		
		
		return result;
	}
}
