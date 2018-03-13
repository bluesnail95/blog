package gdut.ff.mapper;

import java.util.HashMap;
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
	
	@Test
	public void testSelectTop10History() {
		Map<String,String> param = new HashMap<String,String>();
		param.put("userId", "8a2153bb-d5ec-4678-9f50-0709126b2a73");
		List<Map<String,Object>> list = userAccessMapper.selectTop10History(param);
		if(null != list && list.size() > 0) {
			for(int i = 0;i < list.size();i++) {
				Map<String,Object> map = list.get(i);
				for(Map.Entry<String, Object> entry:map.entrySet()) {
					System.out.print(entry.getKey() + "==>" + entry.getValue());
				}
				System.out.println();
			}
		}
	}

}
