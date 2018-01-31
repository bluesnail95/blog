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
@PipelineName("csdnPipeline")
public class CsdnPipeline implements Pipeline<CsdnBeanList> {
	
	@Autowired
	private Constant constant;

	@Override
	public void process(CsdnBeanList beanList) {
		List<CsdnBean> blogs = beanList.getBeanList();
		if(null != blogs && blogs.size() > 0) {
			constant.csdnNodes.addAll(NodeUtil.transFromList(blogs));
			for(int i = 0;i < blogs.size();i++) {
				System.out.println("url :"+blogs.get(i).getUrl()+"->"+"title:"+blogs.get(i).getTitle());
			}
		}	
	}

}
