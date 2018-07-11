package gdut.ff.mapper;

import java.util.List;
import java.util.Map;

import gdut.ff.domain.Tag;

/**
 * 博客数据访问层接口
 * @author liuffei
 * @date 2017-07-10
 */
public interface TagMapper {
	
	public int insertTag(Tag tag);
	
	public Tag findTagById(Integer id);
	
	public int updateTag(Tag tag);
	
	public int deleteTagById(Integer id);
	
	public List<Tag> findAllTag(Tag tag);
	
	public Tag findLastestTag();
	
	public void updateClickCount(Map<String, Integer> param);
}
