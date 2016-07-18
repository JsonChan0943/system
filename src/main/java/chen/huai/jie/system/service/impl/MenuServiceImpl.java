package chen.huai.jie.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import chen.huai.jie.system.bean.AllowMenuBean;
import chen.huai.jie.system.dao.MenuDAO;
import chen.huai.jie.system.entity.MenuEntity;
import chen.huai.jie.system.pager.MenuPager;
import chen.huai.jie.system.service.MenuService;

/**
 * 菜单服务-实现类
 * 
 * @author Administrator
 * 
 */
@Service
public class MenuServiceImpl implements MenuService {
	@Resource
	private MenuDAO menuDAO;

	/**
	 * 分页查找菜单
	 */
	@Override
	public List<MenuEntity> findMenuByPage(MenuPager pager) {
		List<MenuEntity> menus = menuDAO.findMenuByPage(pager);
		return menus;
	}

	/**
	 * 统计菜单数量
	 */
	@Override
	public Long countMenuCnt(MenuPager pager) {
		Long count = menuDAO.countMenuCnt(pager);
		return count;
	}

	/**
	 * 根据pid获取菜单
	 * 
	 * @param pid
	 * @return
	 */
	public List<MenuEntity> findMenuByPid(String pid) {
		return menuDAO.findMenuByPid(pid);
	}

	/**
	 * 增加菜单
	 */
	@Override
	public void addMenu(MenuEntity entity) {
		menuDAO.addMenu(entity);
	}

	/**
	 * 根据id查找菜单
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public MenuEntity findMenuById(String id) {
		return menuDAO.findMenuById(id);
	}

	/**
	 * 删除菜单
	 * 
	 * @param id
	 */
	@Override
	public void deleteMenuById(String id) {
		menuDAO.deleteMenuById(id);
	}

	/**
	 * 更新菜单
	 * 
	 * @param entity
	 */
	@Override
	public void updateMenu(MenuEntity entity) {
		menuDAO.updateMenu(entity);
	}

	/**
	 * 获取用户能够访问的菜单
	 * 
	 * @return
	 */
	@Override
	public List<MenuEntity> getAllowMenuListByPId(AllowMenuBean allowMenuBean) {
		return menuDAO.getAllowMenuListByPId(allowMenuBean);
	}
}
