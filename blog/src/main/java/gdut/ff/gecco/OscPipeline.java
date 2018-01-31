package gdut.ff.gecco;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;

import gdut.ff.utils.Constant;
import gdut.ff.utils.NodeUtil;

/**
 * 
 * @author liuffei
 * @date 2017-01-31
 */
@PipelineName("oscPipeline")
public class OscPipeline implements Pipeline<OscBeanList> {
	
	@Autowired
	private Constant constant;

	@Override
	public void process(OscBeanList beanList) {
		List<OscBean> blogs = beanList.getBeanList();
		if(null != blogs && blogs.size() > 0) {
			constant.oscNodes.addAll(NodeUtil.transFromList(blogs));
			for(int i = 0;i < blogs.size();i++) {
				System.out.println("url :"+blogs.get(i).getUrl()+"->"+"title:"+blogs.get(i).getTitle());
			}
		}	
	}

}
