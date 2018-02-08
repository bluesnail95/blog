package gdut.ff.mapper;

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
	
}
