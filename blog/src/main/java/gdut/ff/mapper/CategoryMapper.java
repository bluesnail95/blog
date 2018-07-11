package gdut.ff.mapper;

import java.util.List;

import gdut.ff.domain.Category;

/**
 * @date 2018-07-05  
 * @author liuffei 文章数据访问层接口
 *
 */
public interface CategoryMapper {
	
	public int insertCategory(Category Category);
	
	public Category findCategoryById(Integer id);
	
	public int updateCategory(Category Category);
	
	public int deleteCategoryById(Integer id);
	
	public List<Category> findAllCategory(Category Category);

	public Category findCategoryByCategoryId(String categoryId);
	
}
