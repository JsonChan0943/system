package chen.huai.jie.system.controller;

import chen.huai.jie.base.controller.BaseController;
import chen.huai.jie.base.utils.JsonModel;
import chen.huai.jie.base.utils.StringUtils;
import chen.huai.jie.system.bean.MenuBeanForTree;
import chen.huai.jie.system.entity.MenuEntity;
import chen.huai.jie.system.entity.RoleEntity;
import chen.huai.jie.system.entity.RoleMenuEntity;
import chen.huai.jie.system.pager.RolePager;
import chen.huai.jie.system.service.MenuService;
import chen.huai.jie.system.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 角色管理
 * 
 * @author chenhuaijie
 * 
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {
	@Resource
	private RoleService roleServiceImpl;
	@Resource
	private MenuService menuServiceImpl;

	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping("/index.html")
	public String index() {
		return "system/role/SystemRoleIndex";
	}

	/**
	 * 角色列表
	 */
	@RequestMapping("/list.html")
	public void list(RolePager pager, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<RoleEntity> list = roleServiceImpl.findRoleByPage(pager);
		Long total = roleServiceImpl.countRoleCnt(pager);
		map.put(ROWS, list);
		map.put(TOTAL, total);
		writeJson(map, response);
	}

	/**
	 * 增加角色获取所有菜单
	 */
	@RequestMapping("/addRoleGetAllMenu.html")
	public void addRoleGetAllMenu(HttpServletResponse response) {
		MenuEntity rooEntity = menuServiceImpl.findMenuById("1");
		List<MenuBeanForTree> rootLevelMeanBeanList = new ArrayList<MenuBeanForTree>();
		MenuBeanForTree rootMenuBeanForTree = new MenuBeanForTree(rooEntity);
		// 一级菜单集合
		List<MenuBeanForTree> firstLevelMeanBeanList = new ArrayList<MenuBeanForTree>();
		// 获取所有的一级菜单
		List<MenuEntity> firstLevelMenuEntities = menuServiceImpl.findMenuByPid("1");// 一级菜单
		for (MenuEntity entity : firstLevelMenuEntities) {
			MenuBeanForTree firstLevelMenuBean = new MenuBeanForTree(entity);
			List<MenuEntity> secondLevelMenuEntities = menuServiceImpl.findMenuByPid(entity.getId());// 二级菜单
			List<MenuBeanForTree> secondLevelMenuBeanList = new ArrayList<MenuBeanForTree>();
			for (MenuEntity subEntity : secondLevelMenuEntities) {
				MenuBeanForTree subBbean = new MenuBeanForTree(subEntity);
				secondLevelMenuBeanList.add(subBbean);
			}
			firstLevelMenuBean.setChildren(secondLevelMenuBeanList);
			firstLevelMeanBeanList.add(firstLevelMenuBean);
		}
		rootMenuBeanForTree.setChildren(firstLevelMeanBeanList);
		rootLevelMeanBeanList.add(rootMenuBeanForTree);
		writeJson(rootLevelMeanBeanList, response);
	}

	/**
	 * 增加角色
	 * 
	 * @param entity
	 * @param menus
	 * @param response
	 */
	@RequestMapping("/addRole.html")
	public void addRole(RoleEntity entity, String menus, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		String rolePk = UUID.randomUUID().toString();// 角色记录主键
		/*
		 * 增加角色
		 */
		entity.setId(rolePk);
		roleServiceImpl.addRole(entity);
		/*
		 * 为角色绑定菜单
		 */
		if (StringUtils.isNotBlank(menus)) {
			String[] ids = menus.split(",");
			if (ids.length > 0) {
				for (String menuId : ids) {
					RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
					roleMenuEntity.setId(UUID.randomUUID().toString());
					roleMenuEntity.setRole_id(rolePk);
					roleMenuEntity.setMenu_id(menuId);
					roleServiceImpl.addMenuForRole(roleMenuEntity);
				}
			}
			jsonModel.setSuccess(true);
			jsonModel.setMsg("新增角色成功.");
		}
		writeJson(jsonModel, response);
	}

	/**
	 * 增加角色获取所有菜单
	 */
	@RequestMapping("/modifyRoleGetAllMenu.html")
	public void modifyRoleGetAllMenu(String roleId, HttpServletResponse response) {
		MenuEntity rooEntity = menuServiceImpl.findMenuById("1");
		List<MenuBeanForTree> rootLevelMeanBeanList = new ArrayList<MenuBeanForTree>();
		MenuBeanForTree rootMenuBeanForTree = new MenuBeanForTree(rooEntity);
		// 一级菜单集合
		List<MenuBeanForTree> firstLevelMeanBeanList = new ArrayList<MenuBeanForTree>();
		// 获取所有的一级菜单
		List<MenuEntity> firstLevelMenuEntities = menuServiceImpl.findMenuByPid("1");// 一级菜单
		// 查询当前角色拥有的所有菜单
		List<RoleMenuEntity> roleMenuEntities = roleServiceImpl.getRoleMenuInfoByRoleId(roleId);
		for (MenuEntity entity : firstLevelMenuEntities) {
			MenuBeanForTree firstLevelMenuBean = new MenuBeanForTree(entity);
			List<MenuEntity> secondLevelMenuEntities = menuServiceImpl.findMenuByPid(entity.getId());// 二级菜单
			List<MenuBeanForTree> secondLevelMenuBeanList = new ArrayList<MenuBeanForTree>();
			for (MenuEntity subEntity : secondLevelMenuEntities) {
				MenuBeanForTree subBean = new MenuBeanForTree(subEntity);
				subBean.setChecked(checkRoleMenu(subEntity.getId(), roleMenuEntities));// 默认选中
				secondLevelMenuBeanList.add(subBean);
			}
			firstLevelMenuBean.setChildren(secondLevelMenuBeanList);
			firstLevelMeanBeanList.add(firstLevelMenuBean);
		}
		rootMenuBeanForTree.setChildren(firstLevelMeanBeanList);
		rootLevelMeanBeanList.add(rootMenuBeanForTree);
		writeJson(rootLevelMeanBeanList, response);
	}

	/**
	 * 检查角色是否具有权限
	 * 
	 * @param menuId
	 * @param roleMenus
	 * @return
	 */
	private boolean checkRoleMenu(String menuId, List<RoleMenuEntity> roleMenus) {
		for (RoleMenuEntity roleMenu : roleMenus) {
			if (StringUtils.equals(menuId, roleMenu.getMenu_id())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 修改角色
	 */
	@RequestMapping("/modifyRole")
	public void modifyRole(RoleEntity entity, String menus, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		String roleId = entity.getId();
		/*
		 * 更新角色信息
		 */
		RoleEntity roleEntity = roleServiceImpl.findRoleById(roleId);
		roleEntity.setRole_code(entity.getRole_code());
		roleEntity.setRole_name(entity.getRole_name());
		roleEntity.setDescription(entity.getDescription());
		roleEntity.setRemark(entity.getRemark());
		roleServiceImpl.updateRole(roleEntity);
		/*
		 * 删除角色菜单表里此角色对应所有记录
		 */
		roleServiceImpl.deleteAllRoleMenuInfoByRoleId(roleId);
		/*
		 * 为角色绑定菜单
		 */
		if (StringUtils.isNotBlank(menus)) {
			String[] ids = menus.split(",");
			if (ids.length > 0) {
				for (String menuId : ids) {
					RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
					roleMenuEntity.setId(UUID.randomUUID().toString());
					roleMenuEntity.setRole_id(roleId);
					roleMenuEntity.setMenu_id(menuId);
					roleServiceImpl.addMenuForRole(roleMenuEntity);
				}
			}
		}
		jsonModel.setSuccess(true);
		jsonModel.setMsg("角色信息更新成功.");
		writeJson(jsonModel, response);
	}

	/**
	 * 删除角色
	 */
	@RequestMapping("/delRole.html")
	public void delRole(String roleId, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		// 删除角色信息
		roleServiceImpl.deleteRoleById(roleId);
		// 删除角色菜单表里与此角色所有对应的记录
		roleServiceImpl.deleteAllRoleMenuInfoByRoleId(roleId);
		jsonModel.setSuccess(true);
		jsonModel.setMsg("角色删除成功.");
		writeJson(jsonModel, response);
	}
}
