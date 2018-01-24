package gdut.ff.mapper;

import java.util.List;

import gdut.ff.domain.User;

/**
 * 用户数据访问层操作接口
 * @author Administrator
 *
 */
public interface UserMapper {

	/**
	 * 查询全部的用户
	 * @return
	 */
	public List<User> findAll();
	
	/**
	 * 根据用户主键查找用户
	 * @param id
	 * @return
	 */
	public User fingOneById(long id);
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public void saveUser(User user);
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public void updateUser(User user);
}
