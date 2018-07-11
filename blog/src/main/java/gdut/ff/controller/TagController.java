package gdut.ff.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import gdut.ff.domain.Tag;
import gdut.ff.service.TagServiceImpl;
import gdut.ff.utils.JsonUtil;

/**
 * 用于获取我的标签的数据
 * @author liuffei
 * @date 2018-01-25
 */
@RestController
public class TagController extends CommController{
	
	@Autowired
	private TagServiceImpl tagServiceImpl;
	
	/**
	 * 添加标签记录
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/tag")
	public JSONObject insertTag(@RequestBody Tag tag,HttpServletRequest request) throws Exception {
		requireAuth(request);
		//添加tagId的值
		if(tag.getId() > 0) {
			tagServiceImpl.updateTag(tag);
		}else {
			tagServiceImpl.insertTag(tag);
		}
		return JsonUtil.successJson();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/tag/{id}")
	public JSONObject findTagById(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) {
		Tag tag = tagServiceImpl.fingOneById(id);
		JSONObject result = JsonUtil.successJson();
		result.put("content", tag);
		return result;
	}
	
	/**
	 * 查询指定的标签记录
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/tag/{id}")
	public JSONObject updateTagById(@PathVariable String id, @RequestBody Tag tag, HttpServletRequest request) throws Exception {
		requireAuth(request);
		tagServiceImpl.updateTag(tag);
		return JsonUtil.successJson();
	}
	
	/**
	 * 删除标签记录
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@DeleteMapping(value = "/tag/{id}")
	public JSONObject deleteTagById(@PathVariable Integer id, HttpServletRequest request) throws Exception {
		requireAuth(request);
		tagServiceImpl.deleteTagById(id);
		return JsonUtil.successJson();
	}
	
	/**
	 * 查询全部标签
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/tags")
	public JSONObject findAllTags(Tag tag) {
		List<Tag> tags = tagServiceImpl.findAllTag(tag);
		JSONObject result = JsonUtil.successJson();
		result.put("tags", tags);
		return result;
	}

}
