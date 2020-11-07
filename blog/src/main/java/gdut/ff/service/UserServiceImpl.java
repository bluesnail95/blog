package gdut.ff.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gdut.ff.domain.User;
import gdut.ff.mapper.UserMapper;

import javax.annotation.Resource;

/**
 * 
 * @author liuffei
 * @date 2017-03-09
 */
@Service
@Transactional
public class UserServiceImpl {
	
	@Resource
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
	 * 根据用户名查找用户
	 * @param loginName
	 * @return
	 */
	@Transactional(readOnly = true)
	public User fingUserByName(String username) {
		return userMapper.fingUserByName(username);
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
	 * @param user 用户
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
