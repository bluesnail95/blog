package gdut.ff.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gdut.ff.utils.BlogUtil;
import gdut.ff.utils.HttpUtil;
import gdut.ff.utils.NodeUtil;

/**
 * 用于获取主页的数据
 * @author liuffei
 * @date 2018-01-25
 */
@RestController
@RequestMapping(value="/blog")
public class BlogController {
    
	/**
	 * 加载主页轮播图的数据(来自csdn或是自己写的博客)
	 * @return
	 */
	@RequestMapping(value = "/csdn/list",method = RequestMethod.GET)
	public ObjectNode csdnBlog(HttpServletResponse resp) {
		ObjectNode result = NodeUtil.create();
		//现在是写死的的链接，后期要修改成前端传入的参数
		String html = HttpUtil.httpRequest("https://www.csdn.net/");
		ArrayNode blogs = new BlogUtil().filterHtml(html);
		ArrayNode content = result.putArray("blogs");
		content.addAll(blogs);
		result.put("status",1);
		//resp.setHeader("Access-Control-Allow-Origin","*");
		return result;
	}
}
