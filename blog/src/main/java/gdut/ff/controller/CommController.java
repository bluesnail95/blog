package gdut.ff.controller;

import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import gdut.ff.utils.NodeUtil;

/**
 * 
 * @author liuffei
 * @date 2018-02-10
 */
@RestController
public class CommController {

	/**
	 * 获取项目根路径
	 * @return
	 */
	@GetMapping(value="/comm/path")
	public ObjectNode rootPath() {
		ObjectNode result = NodeUtil.create();
		try {
			String root = ResourceUtils.getURL("classpath:").getPath();
			result.put("root", root);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		result.put("status", 1);
		return result;
	}
}
