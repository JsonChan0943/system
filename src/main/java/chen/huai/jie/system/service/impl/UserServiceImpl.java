package chen.huai.jie.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import chen.huai.jie.system.dao.UserDAO;
import chen.huai.jie.system.entity.UserEntity;
import chen.huai.jie.system.pager.UserPager;
import chen.huai.jie.system.service.UserService;

/**
 * 用户服务-实现类
 * 
 * @author chenhuaijie
 * 
 */
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDAO userDAO;

	/**
	 * 分页查找用户信息
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public List<UserEntity> findUserByPage(UserPager pager) {
		return userDAO.findUserByPage(pager);
	}

	/**
	 * 统计用户个数
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public Long countUserCnt(UserPager pager) {
		return userDAO.countUserCnt(pager);
	}

	/**
	 * 增加用户
	 * 
	 * @param entity
	 */
	@Override
	public void addUser(UserEntity entity) {
		userDAO.addUser(entity);
	}

	/**
	 * 根据主键查找用户
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public UserEntity findUserById(String id) {
		return userDAO.findUserById(id);
	}

	/**
	 * 更新用户
	 * 
	 * @param entity
	 */
	@Override
	public void updateUser(UserEntity entity) {
		userDAO.updateUser(entity);
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	@Override
	public void deleteUserById(String id) {
		userDAO.deleteUserById(id);
	}

	/**
	 * 根据用户状态获取用户信息
	 * 
	 * @param state
	 * @return
	 */
	@Override
	public List<UserEntity> findUsersByState(Integer state) {
		return userDAO.findUsersByState(state);
	}
}
