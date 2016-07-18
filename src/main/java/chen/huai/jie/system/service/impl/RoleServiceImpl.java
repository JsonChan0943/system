package chen.huai.jie.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import chen.huai.jie.system.dao.RoleDAO;
import chen.huai.jie.system.entity.RoleEntity;
import chen.huai.jie.system.entity.RoleMenuEntity;
import chen.huai.jie.system.entity.UserRoleEntity;
import chen.huai.jie.system.pager.RolePager;
import chen.huai.jie.system.service.RoleService;

/**
 * 角色服务-实现类
 * 
 * @author chenhuaijie
 * 
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleDAO roleDAO;

	/**
	 * 分页查找角色信息
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public List<RoleEntity> findRoleByPage(RolePager pager) {
		return roleDAO.findRoleByPage(pager);
	}

	/**
	 * 统计角色个数
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public Long countRoleCnt(RolePager pager) {
		return roleDAO.countRoleCnt(pager);
	}

	/**
	 * 增加角色
	 * 
	 * @param entity
	 */
	@Override
	public void addRole(RoleEntity entity) {
		roleDAO.addRole(entity);
	}

	/**
	 * 为角色 绑定菜单
	 * 
	 * @param entity
	 */
	@Override
	public void addMenuForRole(RoleMenuEntity entity) {
		roleDAO.addMenuForRole(entity);
	}

	/**
	 * 根据角色id获取RoleMenuList信息
	 * 
	 * @param roleId
	 * @return
	 */
	@Override
	public List<RoleMenuEntity> getRoleMenuInfoByRoleId(String roleId) {
		return roleDAO.getRoleMenuInfoByRoleId(roleId);
	}

	/**
	 * 更新角色
	 * 
	 * @param entity
	 */
	@Override
	public void updateRole(RoleEntity entity) {
		roleDAO.updateRole(entity);
	}

	/**
	 * 根据id获取角色信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public RoleEntity findRoleById(String id) {
		return roleDAO.findRoleById(id);
	}

	/**
	 * 根据角色id删除所有的角色菜单信息
	 * 
	 * @param roleId
	 */
	@Override
	public void deleteAllRoleMenuInfoByRoleId(String roleId) {
		roleDAO.deleteAllRoleMenuInfoByRoleId(roleId);
	}

	/**
	 * 根据角色id删除角色信息
	 * 
	 * @param id
	 */
	@Override
	public void deleteRoleById(String id) {
		roleDAO.deleteRoleById(id);
	}

	/**
	 * 查找所有的角色
	 * 
	 * @return
	 */
	@Override
	public List<RoleEntity> findAllRoles() {
		return roleDAO.findAllRoles();
	}

	/**
	 * 为用户绑定角色信息
	 * 
	 * @param entity
	 */
	@Override
	public void addRoleForUser(UserRoleEntity entity) {
		roleDAO.addRoleForUser(entity);
	}

	/**
	 * 根据用户id获取用户绑定的角色
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public List<UserRoleEntity> getUserRoleEntityByUserId(String userId){
		return roleDAO.getUserRoleEntityByUserId(userId);
	}
	
	/**
	 * 根据UserId删除UserRole
	 * @param userId
	 */
	public void deleteAllUserRoleInfoByUserId(String userId){
		roleDAO.deleteAllUserRoleInfoByUserId(userId);
	}
}
