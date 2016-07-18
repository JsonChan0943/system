package chen.huai.jie.system.service;

import java.util.List;

import chen.huai.jie.system.entity.UserEntity;
import chen.huai.jie.system.pager.UserPager;

/**
 * 用户服务-接口
 * 
 * @author chenhuaijie
 * 
 */
public interface UserService {
	/**
	 * 分页查找用户信息
	 * 
	 * @param pager
	 * @return
	 */
	public List<UserEntity> findUserByPage(UserPager pager);

	/**
	 * 统计用户个数
	 * 
	 * @param pager
	 * @return
	 */
	public Long countUserCnt(UserPager pager);

	/**
	 * 增加用户
	 * 
	 * @param entity
	 */
	public void addUser(UserEntity entity);

	/**
	 * 根据主键查找用户
	 * 
	 * @param id
	 * @return
	 */
	public UserEntity findUserById(String id);

	/**
	 * 更新用户
	 * 
	 * @param entity
	 */
	public void updateUser(UserEntity entity);

	/**
	 * 删除用户
	 * 
	 * @param id
	 */
	public void deleteUserById(String id);

	/**
	 * 根据用户状态获取用户信息
	 * 
	 * @param state
	 * @return
	 */
	public List<UserEntity> findUsersByState(Integer state);
}
