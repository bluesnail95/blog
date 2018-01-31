package gdut.ff.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.geccocrawler.gecco.GeccoEngine;

import gdut.ff.utils.Constant;
import gdut.ff.utils.NodeUtil;

@RestController
public class GeccoBlogController {
	
	@Autowired
	private Constant constant;
	
	static {
		GeccoEngine.create()
	        .classpath("gdut.ff.gecco")
	        .start("https://www.cnblogs.com/")
	        .thread(5)
	        .interval(2000)
	        .run();
		
		GeccoEngine.create()
	        .classpath("gdut.ff.gecco")
	        .start("https://www.oschina.net/")
	        .thread(5)
	        .interval(2000)
	        .run();
		
		GeccoEngine.create()
	        .classpath("gdut.ff.gecco")
	        .start("https://www.csdn.net/")
	        .thread(5)
	        .interval(2000)
	        .run();
	}
	
	@GetMapping(value = "/csdn/blogs")
	public ObjectNode csdnBlogs() {
		ObjectNode result = NodeUtil.create();
		ArrayNode content = result.putArray("blogs");
		content.addAll(constant.csdnNodes);
		result.put("status",1);
		return result;
	}
	
	@GetMapping(value = "/cnblogs/blogs")
	public ObjectNode cnblogsBlogs() {
		ObjectNode result = NodeUtil.create();
		ArrayNode content = result.putArray("blogs");
		content.addAll(constant.cnblogsNodes);
		result.put("status",1);
		return result;
	}
	
	@GetMapping(value = "/osc/blogs")
	public ObjectNode oscBlogs() {
		ObjectNode result = NodeUtil.create();
		ArrayNode content = result.putArray("blogs");
		content.addAll(constant.oscNodes);
		result.put("status",1);
		return result;
	}

}
