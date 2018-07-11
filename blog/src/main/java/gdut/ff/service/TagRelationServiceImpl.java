package gdut.ff.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gdut.ff.domain.TagRelation;
import gdut.ff.mapper.TagRelationMapper;
import gdut.ff.utils.Constant;

/**
 * 
 * @author liuffei
 * @date 2018-07-10
 */
@Service
@Transactional
public class TagRelationServiceImpl {
	
	@Autowired
	private TagRelationMapper tagRelationMapper;

	@Transactional(readOnly = true)
	public TagRelation fingOneById(Integer id) {
		return tagRelationMapper.findTagRelationById(id);
	}
	
	public int insertTagRelation(TagRelation tagRelation) {
		tagRelation.setRelationId(UUID.randomUUID().toString());
		tagRelation.setGmtCreate(new Date());
		tagRelation.setGmtModified(new Date());
		return tagRelationMapper.insertTagRelation(tagRelation);
	}
	
	public int updateTagRelation(TagRelation tagRelation) {
		tagRelation.setGmtModified(new Date());
		return tagRelationMapper.updateTagRelation(tagRelation);
	}

	public int deleteTagRelationById(Integer id) {
		return tagRelationMapper.deleteTagRelationById(id);
	}
	
	@Transactional(readOnly = true)
	public List<TagRelation> findAllTagRelation(TagRelation tagRelation) {
		return tagRelationMapper.findAllTagRelation(tagRelation);
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> findTagRelationDetail(Map<String,Object> params) {
		return tagRelationMapper.findTagRelationDetail(params);
	}
}
