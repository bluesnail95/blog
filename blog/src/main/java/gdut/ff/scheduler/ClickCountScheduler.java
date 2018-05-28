package gdut.ff.scheduler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import gdut.ff.service.BlogServiceImpl;
import gdut.ff.utils.Constant;

/**
 * 
 * @author liuffei
 * @date 2018-05-28
 *
 */
@Component
public class ClickCountScheduler {
	
	@Autowired
	private BlogServiceImpl blogServiceImpl;
	
	/**
	 * 定时器 0 0 3 * * ? 凌晨3点执行一次
	 */
	@Scheduled(cron = "0 0 3 * * ?")
	public void blogClickCount() {
		for(Map.Entry<Integer, Integer> entry: Constant.blogCountMap.entrySet()) {
			Integer id = entry.getKey();
			Integer clickCount = entry.getValue();
			blogServiceImpl.updateClickCount(clickCount, id);
		}
		Constant.blogCountMap.clear();
	}

}
