package gdut.ff.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gdut.ff.domain.Category;
import gdut.ff.mapper.CategoryMapper;

/**
 * 
 * @author liuffei
 * @date 2018-07-05
 */
@Service
@Transactional
public class CategoryServiceImpl {
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Transactional(readOnly = true)
	public Category fingCategoryById(Integer id) {
		return categoryMapper.findCategoryById(id);
	}
	
	public int insertCategory(Category category) {
		category.setGmtCreate(new Date());
		category.setGmtModified(new Date());
		category.setCategoryId(UUID.randomUUID().toString());
		return categoryMapper.insertCategory(category);
	}
	
	public int updateCategory(Category category) {
		category.setGmtModified(new Date());
		return categoryMapper.updateCategory(category);
	}

	public int deleteCategoryById(Integer id) {
		return categoryMapper.deleteCategoryById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Category> findAllCategory(Category category) {
		return categoryMapper.findAllCategory(category);
	}
	
	public Category fingCategoryByCategoryId(String categoryId) {
		return categoryMapper.findCategoryByCategoryId(categoryId);
	}

}
