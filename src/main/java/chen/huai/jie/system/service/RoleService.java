package chen.huai.jie.system.service;

import java.util.List;

import chen.huai.jie.system.entity.RoleEntity;
import chen.huai.jie.system.entity.RoleMenuEntity;
import chen.huai.jie.system.entity.UserRoleEntity;
import chen.huai.jie.system.pager.RolePager;

/**
 * 角色服务-接口
 * 
 * @author chenhuaijie
 * 
 */
public interface RoleService {
	/**
	 * 分页查找角色信息
	 * 
	 * @param pager
	 * @return
	 */
	public List<RoleEntity> findRoleByPage(RolePager pager);

	/**
	 * 统计角色个数
	 * 
	 * @param pager
	 * @return
	 */
	public Long countRoleCnt(RolePager pager);

	/**
	 * 增加角色
	 * 
	 * @param entity
	 */
	public void addRole(RoleEntity entity);

	/**
	 * 为角色 绑定菜单
	 * 
	 * @param entity
	 */
	public void addMenuForRole(RoleMenuEntity entity);

	/**
	 * 根据角色id获取RoleMenuList信息
	 * 
	 * @param roleId
	 * @return
	 */
	public List<RoleMenuEntity> getRoleMenuInfoByRoleId(String roleId);

	/**
	 * 更新角色
	 * 
	 * @param entity
	 */
	public void updateRole(RoleEntity entity);

	/**
	 * 根据id获取角色信息
	 * 
	 * @param id
	 * @return
	 */
	public RoleEntity findRoleById(String id);

	/**
	 * 根据角色id删除所有的角色菜单信息
	 * 
	 * @param roleId
	 */
	public void deleteAllRoleMenuInfoByRoleId(String roleId);

	/**
	 * 根据角色id删除角色信息
	 * 
	 * @param id
	 */
	public void deleteRoleById(String id);

	/**
	 * 查找所有的角色
	 * 
	 * @return
	 */
	public List<RoleEntity> findAllRoles();

	/**
	 * 为用户绑定角色信息
	 * 
	 * @param entity
	 */
	public void addRoleForUser(UserRoleEntity entity);

	/**
	 * 根据用户id获取用户绑定的角色
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserRoleEntity> getUserRoleEntityByUserId(String userId);
	
	/**
	 * 根据UserId删除UserRole
	 * @param userId
	 */
	public void deleteAllUserRoleInfoByUserId(String userId);
}
