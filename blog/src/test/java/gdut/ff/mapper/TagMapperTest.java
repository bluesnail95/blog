package gdut.ff.mapper;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gdut.ff.domain.Tag;
import gdut.ff.domain.TagRelation;
import gdut.ff.service.BlogServiceImpl;
import gdut.ff.service.TagRelationServiceImpl;
import gdut.ff.service.TagServiceImpl;

/**
 * 
 * @author liuffei
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TagMapperTest {
	
	@Autowired
	private TagServiceImpl tagServiceImpl;
	
	@Autowired
	private TagRelationServiceImpl tagRelationServiceImpl;
	
	@Test
	public void testInsertTag() {
		Tag tag1 = new Tag();
		tag1.setTagName("分布式事务");
		tagServiceImpl.insertTag(tag1);
		
		Tag tag2 = new Tag();
		tag2.setTagName("同步原语");
		tagServiceImpl.insertTag(tag2);
		
		Tag tag3 = new Tag();
		tag3.setTagName("线程API");
		tagServiceImpl.insertTag(tag3);
	}
	
	@Test
	public void testInsertTagRelation() {
		TagRelation relation1 = new TagRelation();
		relation1.setRelationType("1");
		relation1.setTagId("3ba62228-6811-4d23-a5a6-4f7a5ae5d034");
		relation1.setTypeId("2610fcd7-715b-4e5c-b256-c8e88183ff92");
		tagRelationServiceImpl.insertTagRelation(relation1);
		
		TagRelation relation2 = new TagRelation();
		relation2.setRelationType("1");
		relation2.setTagId("3a255e79-4686-4688-9d89-72828f367ebf");
		relation2.setTypeId("2610fcd7-715b-4e5c-b256-c8e88183ff92");
		tagRelationServiceImpl.insertTagRelation(relation2);
	}
	
	@Test
	public void testFindAll() {
		List<Tag> tags = tagServiceImpl.findAllTag(null);
		if(null != tags && tags.size() > 0) {
			for(int i = 0;i < tags.size();i++) {
				System.out.println(tags.get(i));
			}
		}
		
		List<TagRelation> relations = tagRelationServiceImpl.findAllTagRelation(null);
		if(null != relations && relations.size() > 0) {
			for(int i = 0;i < relations.size();i++) {
				System.out.println(relations.get(i));
			}
		}
	}
	

}
