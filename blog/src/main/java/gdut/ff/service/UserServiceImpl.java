package gdut.ff.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gdut.ff.domain.User;
import gdut.ff.mapper.UserMapper;

/**
 * 
 * @author liuffei
 * @date 2017-03-09
 */
@Service
@Transactional
public class UserServiceImpl {
	
	@Autowired
	private UserMapper userMapper;

	/**
	 * 查询全部的用户
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<User> findAllUser(){
		return userMapper.findAllUser();
	}
	
	/**
	 * 根据用户主键查找用户
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public User fingUserById(int id) {
		return userMapper.fingUserById(id);
	}
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public void saveUser(User user) {
		user.setGmtCreate(new Date());
		user.setGmtModified(new Date());
		user.setUserId(UUID.randomUUID().toString());
		userMapper.saveUser(user);
	}
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 */
	public void updateUser(User user) {
		user.setGmtModified(new Date());
		userMapper.updateUser(user);
	}
	
	/**
	 * 根据用户用户名或邮箱登录
	 * @param loginName 登录名
	 * @param password 密码
	 */
	@Transactional(readOnly = true)
	public User loginUser(User user) {
		return userMapper.loginUser(user);
	}
	
	/**
	 * 根据用户业务标识查找用户
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = true)
	public User findUserByUserId(String userId) {
		return userMapper.findUserByUserId(userId);
	}
	
}
