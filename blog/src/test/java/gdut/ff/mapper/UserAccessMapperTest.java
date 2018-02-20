package gdut.ff.mapper;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.JsonNode;

import gdut.ff.utils.NodeUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserAccessMapperTest {
	
	@Autowired
	private UserAccessMapper userAccessMapper;
	
	@Test
	public void testBlogWebsiteAnalysis() {
		List<Map<String,String>> analysis = userAccessMapper.blogWebsiteAnalysis(null);
		JsonNode content = NodeUtil.transFromList(analysis);
		System.out.println(content);
	}

}
