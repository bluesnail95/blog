package gdut.ff.mapper;

import java.util.List;
import java.util.Map;

import gdut.ff.domain.UserAccess;

/**
 * 用户访问    数据访问层
 * @author liuffei
 * @date 2018-02-07
 *
 */
public interface UserAccessMapper {

	/**
	 * 保存用户 访问记录
	 * @param userAccess 用户访问记录数据
	 */
	public void saveUserAccess(UserAccess userAccess);
	
	
	/**
	 * 分析csdn、cnblogs、osc哪个网站的博客比较受欢迎
	 * @param param 过滤条件 minDate 日期下限 maxDate 日期上限
	 * @return
	 */
	public List<Map<String,String>> blogWebsiteAnalysis(Map<String,Object> param);
	
}
