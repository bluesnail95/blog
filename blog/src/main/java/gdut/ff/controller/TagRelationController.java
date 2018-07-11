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

import gdut.ff.domain.TagRelation;
import gdut.ff.service.TagRelationServiceImpl;
import gdut.ff.utils.JsonUtil;

/**
 * 用于获取我的标签关联关系的数据
 * @author liuffei
 * @date 2018-01-25
 */
@RestController
public class TagRelationController extends CommController{
	
	@Autowired
	private TagRelationServiceImpl tagRelationServiceImpl;
	
	/**
	 * 添加标签关联关系记录
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/tagRelation")
	public JSONObject insertTagRelation(@RequestBody TagRelation tagRelation,HttpServletRequest request) throws Exception {
		requireAuth(request);
		//添加tagRelationId的值
		if(tagRelation.getId() > 0) {
			tagRelationServiceImpl.updateTagRelation(tagRelation);
		}else {
			tagRelationServiceImpl.insertTagRelation(tagRelation);
		}
		return JsonUtil.successJson();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/tagRelation/{id}")
	public JSONObject findTagRelationById(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) {
		TagRelation tagRelation = tagRelationServiceImpl.fingOneById(id);
		JSONObject result = JsonUtil.successJson();
		result.put("content", tagRelation);
		return result;
	}
	
	/**
	 * 查询指定的标签关联关系记录
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/tagRelation/{id}")
	public JSONObject updateTagRelationById(@PathVariable String id, @RequestBody TagRelation tagRelation, HttpServletRequest request) throws Exception {
		requireAuth(request);
		tagRelationServiceImpl.updateTagRelation(tagRelation);
		return JsonUtil.successJson();
	}
	
	/**
	 * 删除标签关联关系记录
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@DeleteMapping(value = "/tagRelation/{id}")
	public JSONObject deleteTagRelationById(@PathVariable Integer id, HttpServletRequest request) throws Exception {
		requireAuth(request);
		tagRelationServiceImpl.deleteTagRelationById(id);
		return JsonUtil.successJson();
	}
	
	/**
	 * 查询全部标签关联关系
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/tagRelations")
	public JSONObject findAllTagRelations(TagRelation tagRelation) {
		List<TagRelation> tagRelations = tagRelationServiceImpl.findAllTagRelation(tagRelation);
		JSONObject result = JsonUtil.successJson();
		result.put("tagRelations", tagRelations);
		return result;
	}
	
}
