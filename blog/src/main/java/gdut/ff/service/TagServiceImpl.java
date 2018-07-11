package gdut.ff.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gdut.ff.domain.Tag;
import gdut.ff.mapper.TagMapper;
import gdut.ff.utils.Constant;

/**
 * 
 * @author liuffei
 * @date 2018-07-10
 */
@Service
@Transactional
public class TagServiceImpl {
	
	@Autowired
	private TagMapper tagMapper;

	@Transactional(readOnly = true)
	public Tag fingOneById(Integer id) {
		return tagMapper.findTagById(id);
	}
	
	public int insertTag(Tag tag) {
		tag.setTagId(UUID.randomUUID().toString());
		tag.setGmtCreate(new Date());
		tag.setGmtModified(new Date());
		return tagMapper.insertTag(tag);
	}
	
	public int updateTag(Tag tag) {
		tag.setGmtModified(new Date());
		return tagMapper.updateTag(tag);
	}

	public int deleteTagById(Integer id) {
		return tagMapper.deleteTagById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Tag> findAllTag(Tag tag) {
		return tagMapper.findAllTag(tag);
	}
	
}
