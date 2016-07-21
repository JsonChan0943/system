package chen.huai.jie.system.controller;

import chen.huai.jie.base.controller.BaseController;
import chen.huai.jie.base.enums.enumc.MenuLevelEnum;
import chen.huai.jie.base.system.ConstantFile;
import chen.huai.jie.base.utils.JsonModel;
import chen.huai.jie.system.bean.AllowMenuBean;
import chen.huai.jie.system.bean.MenuBean;
import chen.huai.jie.system.bean.MenuBeanForTree;
import chen.huai.jie.system.entity.MenuEntity;
import chen.huai.jie.system.entity.UserEntity;
import chen.huai.jie.system.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 菜单管理
 *
 * @author chenhuaijie
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController {
    @Resource
    private MenuService menuServiceImpl;

    /**
     * 主页面
     *
     * @return
     */
    @RequestMapping("/index.html")
    public String index() {
        return "system/menu/SystemMenuIndex";
    }

    /**
     * 获取所有菜单
     */
    @RequestMapping("/getAllMenu.html")
    public void getAllMenu(HttpServletResponse response) {
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
     * 获取我能访问的菜单
     */
    @RequestMapping("getMyMenus")
    public void getMyMenus(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String userId = ((UserEntity) session.getAttribute(ConstantFile.SESSION_USER_BEAN)).getId();
        List<MenuBean> menulist = new ArrayList<MenuBean>();
        // 获取所有的一级菜单
        List<MenuEntity> firstLevelMenuEntityList = menuServiceImpl
                .getAllowMenuListByPId(new AllowMenuBean("1", userId));
        for (MenuEntity entity : firstLevelMenuEntityList) {
            MenuBean firstLevelMenuBean = new MenuBean(entity.getMenu_name(), entity.getMenu_url());// 一级菜单Bean
            List<MenuBean> secondLevelMenuBeanlist = new ArrayList<MenuBean>();// 该一级菜单那下的二级菜单Bean
            List<MenuEntity> secondLevelMenuEntityList = menuServiceImpl.getAllowMenuListByPId(new AllowMenuBean(entity
                    .getId(), userId));// 该一级菜单下的所有二级菜单
            for (MenuEntity menuEntity : secondLevelMenuEntityList) {
                MenuBean secondMenuBean = new MenuBean(menuEntity.getMenu_name(), menuEntity.getMenu_url());
                secondLevelMenuBeanlist.add(secondMenuBean);
            }
            firstLevelMenuBean.setSubMenus(secondLevelMenuBeanlist);
            menulist.add(firstLevelMenuBean);
        }
        writeJson(menulist, response);
    }

    /**
     * 新增菜单
     */
    @RequestMapping("/addMenu.html")
    public void addMenu(MenuEntity entity, Integer parent_menu_level, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        entity.setId(UUID.randomUUID().toString());
        if (parent_menu_level == MenuLevelEnum.LEVEL_ONE.getEnValue()) {
            entity.setMenu_level(MenuLevelEnum.LEVEL_TWO.getEnValue());
        } else if (parent_menu_level == MenuLevelEnum.LEVEL_TWO.getEnValue()) {
            entity.setMenu_level(MenuLevelEnum.LEVEL_THREE.getEnValue());
        }
        menuServiceImpl.addMenu(entity);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("新增菜单成功.");
        writeJson(jsonModel, response);
    }

    /**
     * 删除菜单
     */
    @RequestMapping("/delMenu.html")
    public void delMenu(String id, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        List<MenuEntity> list = menuServiceImpl.findMenuByPid(id);
        if (list != null && list.size() > 0) {
            jsonModel.setSuccess(false);
            jsonModel.setMsg("该菜单下还有子菜单,请先删除子菜单.");
        } else {
            menuServiceImpl.deleteMenuById(id);
            jsonModel.setSuccess(true);
            jsonModel.setMsg("菜单删除成功.");
        }
        writeJson(jsonModel, response);
    }

    /**
     * 判断菜单能否创建子菜单
     */
    @RequestMapping("/canCreateSubMenu")
    public void canCreateSubMenu(String id, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        MenuEntity entity = menuServiceImpl.findMenuById(id);
        // 三级菜单不能创建子菜单
        if (entity.getMenu_level() == MenuLevelEnum.LEVEL_THREE.getEnValue()) {
            jsonModel.setSuccess(false);
            jsonModel.setMsg("三级菜单不能创建子菜单.");
        } else {
            jsonModel.setSuccess(true);
            jsonModel.setMsg("一、二级菜单可以创建子菜单.");
        }
        writeJson(jsonModel, response);
    }

    /**
     * 修改菜单
     *
     * @param entity
     * @param response
     */
    @RequestMapping("/modifyMenu.html")
    public void modifyMenu(MenuEntity entity, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        MenuEntity menuEntity = menuServiceImpl.findMenuById(entity.getId());
        menuEntity.setMenu_code(entity.getMenu_code());// 菜单编码
        menuEntity.setMenu_name(entity.getMenu_name());// 菜单名字
        menuEntity.setMenu_url(entity.getMenu_url());// 菜单url
        menuEntity.setDescription(entity.getDescription());// 描述
        menuEntity.setRemark(entity.getRemark());// 备注
        menuServiceImpl.updateMenu(menuEntity);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("菜单更新成功.");
        writeJson(jsonModel, response);
    }
}
