package gdut.ff.mapper;

import java.util.List;
import java.util.Map;

import gdut.ff.domain.Tag;
import gdut.ff.domain.TagRelation;

/**
 * 标签关系访问层接口
 * @author liuffei
 * @date 2017-07-10
 */
public interface TagRelationMapper {
	
	public int insertTagRelation(TagRelation tagRelation);
	
	public TagRelation findTagRelationById(Integer id);
	
	public int updateTagRelation(TagRelation tagRelation);
	
	public int deleteTagRelationById(Integer id);
	
	public List<TagRelation> findAllTagRelation(TagRelation tagRelation);
	
	public List<Map<String, Object>> findTagRelationDetail(Map<String,Object> params);
	
}
