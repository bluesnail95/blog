package gdut.ff.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gdut.ff.domain.UserAccess;
import gdut.ff.mapper.UserAccessMapper;

@Service
@Transactional
public class UserAccessServiceImpl {
	
	@Autowired
	private UserAccessMapper userAccessMapper;
	
	/**
	 * 保存用户 访问记录
	 * @param userAccess 用户访问记录数据
	 */
	public void saveUserAccess(UserAccess userAccess) {
		userAccessMapper.saveUserAccess(userAccess);
	}
	
	
	/**
	 * 分析csdn、cnblogs、osc哪个网站的博客比较受欢迎
	 * @param param 过滤条件 minDate 日期下限 maxDate 日期上限
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<Map<String,String>> blogWebsiteAnalysis(Map<String,Object> param){
		return userAccessMapper.blogWebsiteAnalysis(param);
	}
	
	/**
	 * 统计一周内的访问量
	 * @param param
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<Map<String,Object>> analysisGroupByDateAndWebsiteType(Map<String,String> param){
		return userAccessMapper.analysisGroupByDateAndWebsiteType(param);
	}
	

}
