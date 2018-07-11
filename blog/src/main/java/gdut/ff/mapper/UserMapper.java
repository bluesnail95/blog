package gdut.ff.mapper;

import java.util.List;

import gdut.ff.domain.User;

/**
 * 用户数据访问层操作接口
 * @author liuffei
 *
 */
public interface UserMapper {

	/**
	 * 查询全部的用户
	 * @return
	 */
	public List<User> findAllUser();
	
	/**
	 * 根据用户主键查找用户
	 * @param id
	 * @return
	 */
	public User fingUserById(int id);
	
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
	
	/**
	 * 根据用户用户名或邮箱登录
	 * @param loginName 登录名
	 * @param password 密码
	 */
	public User loginUser(User user);
	
	/**
	 * 根据用户的userId创建用户
	 * @param userId
	 * @return
	 */
	public User findUserByUserId(String userId);
}
