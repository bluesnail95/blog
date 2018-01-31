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
@PipelineName("cnblogsPipeline")
public class CnblogsPipeline implements Pipeline<CnblogsBeanList> {
	
	@Autowired
	private Constant constant;

	@Override
	public void process(CnblogsBeanList beanList) {
		List<CnblogsBean> blogs = beanList.getBeanList();
		if(null != blogs && blogs.size() > 0) {
			constant.cnblogsNodes.addAll(NodeUtil.transFromList(blogs));
			for(int i = 0;i < blogs.size();i++) {
				CnblogsBean blog = blogs.get(i);
				System.out.println("url:"+blog.getUrl()+"->title:"+blog.getTitle());
			}
		}
	}

}
