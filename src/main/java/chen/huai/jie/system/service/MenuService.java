package chen.huai.jie.system.service;

import java.util.List;

import chen.huai.jie.system.bean.AllowMenuBean;
import chen.huai.jie.system.entity.MenuEntity;
import chen.huai.jie.system.pager.MenuPager;

/**
 * 菜单服务-接口
 * 
 * @author chenhuaijie
 * 
 */
public interface MenuService {
	/**
	 * 分页显示菜单
	 * 
	 * @param pager
	 * @return
	 */
	public List<MenuEntity> findMenuByPage(MenuPager pager);

	/**
	 * 查询菜单记录数
	 * 
	 * @param pager
	 * @return
	 */
	public Long countMenuCnt(MenuPager pager);

	/**
	 * 根据pid获取菜单
	 * 
	 * @param pid
	 * @return
	 */
	public List<MenuEntity> findMenuByPid(String pid);

	/**
	 * 增加菜单
	 * 
	 * @param entity
	 */
	public void addMenu(MenuEntity entity);

	/**
	 * 根据id查找菜单
	 * 
	 * @param id
	 * @return
	 */
	public MenuEntity findMenuById(String id);

	/**
	 * 删除菜单
	 * 
	 * @param id
	 */
	public void deleteMenuById(String id);

	/**
	 * 更新菜单
	 * 
	 * @param entity
	 */
	public void updateMenu(MenuEntity entity);

	/**
	 * 获取用户能够访问的菜单
	 * 
	 * @return
	 */
	public List<MenuEntity> getAllowMenuListByPId(AllowMenuBean allowMenuBean);
}
