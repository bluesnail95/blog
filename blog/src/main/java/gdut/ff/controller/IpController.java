package gdut.ff.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import gdut.ff.domain.Ip;
import gdut.ff.service.IpServiceImpl;
import gdut.ff.utils.JsonUtil;

/**
 * 
 * @author liuffei
 * @date 
 */
@RestController
public class IpController {
	
	@Autowired
	private IpServiceImpl ipServiceImpl;
	
	/**
	 * 添加记录
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/ip")
	public JSONObject insertIp(@RequestBody Ip ip,HttpServletRequest request) throws Exception {
		if(ip.getId() > 0) {
			ipServiceImpl.updateIp(ip);
		}else {
			ipServiceImpl.insertIp(ip);
		}
		return JsonUtil.successJson();
	}
	
	/**
	 * 查询指定记录
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/ip/{id}")
	public JSONObject findIpById(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) {
		Ip ip = ipServiceImpl.findIpById(id);
		JSONObject result = JsonUtil.successJson();
		result.put("content", ip);
		return result;
	}
	
	/**
	 * 更新指定的记录
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/ip/{id}")
	public JSONObject updateIpgById(@PathVariable String id, @RequestBody Ip ip) {
		ipServiceImpl.updateIp(ip);
		return JsonUtil.successJson();
	}
	
	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/ip/{id}")
	public JSONObject deleteIpById(@PathVariable String id) {
		ipServiceImpl.deleteIpById(id);
		return JsonUtil.successJson();
	}
	
	/**
	 * 查询全部
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/ips")
	public JSONObject finaAllIps() {
		List<Ip> ips = ipServiceImpl.findAllIp(null);
		JSONObject result = JsonUtil.successJson();
		result.put("ips", ips);
		return result;
	}
}
