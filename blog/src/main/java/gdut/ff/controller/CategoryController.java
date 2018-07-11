package gdut.ff.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import gdut.ff.domain.Category;
import gdut.ff.domain.Message;
import gdut.ff.exception.LoginException;
import gdut.ff.service.CategoryServiceImpl;
import gdut.ff.utils.Constant;
import gdut.ff.utils.JsonUtil;
import gdut.ff.utils.NodeUtil;

/**
 *  文章分类
 * @author liuffei
 * @date 2018-07-05
 */
@RestController
public class CategoryController extends CommController{
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	/**
	 * 添加文章分类记录
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value = "/category")
	public JSONObject insertCategory(@RequestBody Category category,HttpServletRequest request) throws Exception {
		requireAuth(request);
		//添加categoryId的值
		if(0 != category.getId()) {
			categoryServiceImpl.updateCategory(category);
		}else {
			categoryServiceImpl.insertCategory(category);
		}
		//服务端推送消息
		JSONObject categoryJson = new JSONObject();
		categoryJson.put("category", category);
		return JsonUtil.successJson();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/category/{id}")
	public JSONObject findCategoryById(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) {
		Category category = categoryServiceImpl.fingCategoryById(id);
		JSONObject result = JsonUtil.successJson();
		result.put("content", category);
		return result;
	}
	
	/**
	 * 查询指定的文章分类记录
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/category/{id}")
	public JSONObject updateCategoryById(@PathVariable String id, @RequestBody Category category, HttpServletRequest request) {
		categoryServiceImpl.updateCategory(category);
		return JsonUtil.successJson();
	}
	
	/**
	 * 删除文章分类记录
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@DeleteMapping(value = "/category/{id}")
	public JSONObject deleteCategoryById(@PathVariable Integer id, HttpServletRequest request) throws Exception {
		requireAuth(request);
		categoryServiceImpl.deleteCategoryById(id);
		return JsonUtil.successJson();
	}
	
	/**
	 * 查询全部文章分类
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/categorys")
	public JSONObject findAllCategorys(Category category) {
		List<Category> categorys = categoryServiceImpl.findAllCategory(category);
		JSONObject result = JsonUtil.successJson();
		result.put("categorys", categorys);
		return result;
	}
	
}
