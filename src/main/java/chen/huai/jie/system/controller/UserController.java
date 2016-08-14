package chen.huai.jie.system.controller;

import chen.huai.jie.base.controller.BaseController;
import chen.huai.jie.base.enums.enumc.UserStateEnum;
import chen.huai.jie.base.utils.*;
import chen.huai.jie.system.bean.OrganBeanForTree;
import chen.huai.jie.system.bean.RoleBeanForTree;
import chen.huai.jie.system.bean.export.UserBeanForExport;
import chen.huai.jie.system.entity.OrganEntity;
import chen.huai.jie.system.entity.RoleEntity;
import chen.huai.jie.system.entity.UserEntity;
import chen.huai.jie.system.entity.UserRoleEntity;
import chen.huai.jie.system.pager.UserPager;
import chen.huai.jie.system.service.OrganService;
import chen.huai.jie.system.service.RoleService;
import chen.huai.jie.system.service.TsysParamService;
import chen.huai.jie.system.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户管理
 *
 * @author chenhuaijie
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController implements UserIController {
    @Resource
    private UserService userServiceImpl;
    @Resource
    private RoleService roleServiceImpl;
    @Resource
    private OrganService organServiceImpl;
    @Resource
    private TsysParamService tsysParamService;

    /**
     * 主页面
     *
     * @return
     */
    @Override
    @RequestMapping("/index.html")
    public String index() {
        return "system/user/SystemUserIndex";
    }

    /**
     * 用户列表
     */
    @Override
    @RequestMapping("/list.html")
    public void list(UserPager pager, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<UserEntity> list = userServiceImpl.findUserByPage(pager);
        Long total = userServiceImpl.countUserCnt(pager);
        map.put(ROWS, list);
        map.put(TOTAL, total);
        writeJson(map, response);
    }

    /**
     * 增加用户获取角色列表
     *
     * @param response
     */
    @RequestMapping("/addUserGetRoles.html")
    public void addUserGetRoles(HttpServletResponse response) {
        List<RoleBeanForTree> roleBeanList = new ArrayList<RoleBeanForTree>();
        List<RoleEntity> roles = roleServiceImpl.findAllRoles();
        for (RoleEntity entity : roles) {
            RoleBeanForTree roleReanForTree = new RoleBeanForTree(entity);
            roleBeanList.add(roleReanForTree);
        }
        writeJson(roleBeanList, response);
    }

    /**
     * 增加用户获取机构列表
     *
     * @param response
     */
    @RequestMapping("/addUserGetOrgans.html")
    public void addUserGetOrgans(HttpServletResponse response) {
        OrganEntity rooEntity = organServiceImpl.findOrganById("1");
        List<OrganBeanForTree> rootLevelOrganBeanList = new ArrayList<OrganBeanForTree>();
        OrganBeanForTree rootOrganBeanForTree = new OrganBeanForTree(rooEntity);
        // 一级机构集合
        List<OrganBeanForTree> firstLevelOrganBeanList = new ArrayList<OrganBeanForTree>();
        // 获取所有的一级菜单
        List<OrganEntity> firstLevelOrganEntities = organServiceImpl.findOrganByPid("1");// 一级菜单
        for (OrganEntity entity : firstLevelOrganEntities) {
            OrganBeanForTree firstLevelOrganBean = new OrganBeanForTree(entity);
            List<OrganEntity> secondLevelOrganEntities = organServiceImpl.findOrganByPid(entity.getId());// 二级菜单
            List<OrganBeanForTree> secondLevelOrganBeanList = new ArrayList<OrganBeanForTree>();
            for (OrganEntity subEntity : secondLevelOrganEntities) {
                OrganBeanForTree subBbean = new OrganBeanForTree(subEntity);
                secondLevelOrganBeanList.add(subBbean);
            }
            firstLevelOrganBean.setChildren(secondLevelOrganBeanList);
            firstLevelOrganBeanList.add(firstLevelOrganBean);
        }
        rootOrganBeanForTree.setChildren(firstLevelOrganBeanList);
        rootLevelOrganBeanList.add(rootOrganBeanForTree);
        writeJson(rootLevelOrganBeanList, response);
    }

    /**
     * 增加用户
     */
    @Override
    @RequestMapping("/addUser")
    public void addUser(UserEntity entity, String roles, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        try {
            // 主键
            String userPk = UUID.randomUUID().toString();
            /*
			 * 增加用户
			 */
            entity.setId(userPk);
            String password = tsysParamService.findParamByCode("0001").getParam_value();
            entity.setPassword(MD5Utils.GetMD5Code(password));// 初始密码
            entity.setState(UserStateEnum.NORMAL.getEnValue());// 初始状态
            entity.setCreateTime(new Date());// 创建时间
            userServiceImpl.addUser(entity);
			/*
			 * 用用户增加权限
			 */
            if (StringUtils.isNoneBlank(roles)) {
                String[] roleIdArry = roles.split(",");
                for (String roleId : roleIdArry) {
                    UserRoleEntity userRoleEntity = new UserRoleEntity();
                    userRoleEntity.setId(UUID.randomUUID().toString());
                    userRoleEntity.setUser_id(userPk);
                    userRoleEntity.setRole_id(roleId);
                    roleServiceImpl.addRoleForUser(userRoleEntity);
                }
            }
            jsonModel.setSuccess(true);
            jsonModel.setMsg("用户添加成功.");
        } catch (Exception e) {
            e.printStackTrace();
            jsonModel.setSuccess(false);
            jsonModel.setMsg("用户添加失败.");
        }
        writeJson(jsonModel, response);
    }

    /**
     * 修改用户获取角色列表
     */
    @RequestMapping("/modifyUserGetRoles.html")
    public void modifyUserGetRoles(String userId, HttpServletResponse response) {
        List<RoleBeanForTree> roleBeanList = new ArrayList<RoleBeanForTree>();
        List<RoleEntity> roles = roleServiceImpl.findAllRoles();
        List<UserRoleEntity> userRoleEntities = roleServiceImpl.getUserRoleEntityByUserId(userId);
        for (RoleEntity entity : roles) {
            RoleBeanForTree roleReanForTree = new RoleBeanForTree(entity);
            roleReanForTree.setChecked(checkUserRole(entity.getId(), userRoleEntities));
            roleBeanList.add(roleReanForTree);
        }
        writeJson(roleBeanList, response);
    }

    /**
     * 增加用户获取机构列表
     *
     * @param response
     */
    @RequestMapping("/modifyUserGetOrgans.html")
    public void modifyUserGetOrgans(HttpServletRequest request, HttpServletResponse response) {
        OrganEntity rooEntity = organServiceImpl.findOrganById("1");
        List<OrganBeanForTree> rootLevelOrganBeanList = new ArrayList<OrganBeanForTree>();
        OrganBeanForTree rootOrganBeanForTree = new OrganBeanForTree(rooEntity);
        rootOrganBeanForTree.setChecked(true);
        // 一级机构集合
        List<OrganBeanForTree> firstLevelOrganBeanList = new ArrayList<OrganBeanForTree>();
        // 获取所有的一级菜单
        List<OrganEntity> firstLevelOrganEntities = organServiceImpl.findOrganByPid("1");// 一级菜单
        for (OrganEntity entity : firstLevelOrganEntities) {
            OrganBeanForTree firstLevelOrganBean = new OrganBeanForTree(entity);
            List<OrganEntity> secondLevelOrganEntities = organServiceImpl.findOrganByPid(entity.getId());// 二级菜单
            List<OrganBeanForTree> secondLevelOrganBeanList = new ArrayList<OrganBeanForTree>();
            for (OrganEntity subEntity : secondLevelOrganEntities) {
                OrganBeanForTree subBbean = new OrganBeanForTree(subEntity);
                secondLevelOrganBeanList.add(subBbean);
            }
            firstLevelOrganBean.setChildren(secondLevelOrganBeanList);
            firstLevelOrganBeanList.add(firstLevelOrganBean);
        }
        rootOrganBeanForTree.setChildren(firstLevelOrganBeanList);
        rootLevelOrganBeanList.add(rootOrganBeanForTree);
        writeJson(rootLevelOrganBeanList, response);
    }

    /**
     * 检查角色是否具有权限
     *
     * @param roleId
     * @param userRoles
     * @return
     */
    private boolean checkUserRole(String roleId, List<UserRoleEntity> userRoles) {
        for (UserRoleEntity userRole : userRoles) {
            if (StringUtils.equals(roleId, userRole.getRole_id())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 修改用户
     */
    @Override
    @RequestMapping("/mofifyUser.html")
    public void mofifyUser(UserEntity entity, String roles, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        String userId = entity.getId();
		/*
		 * 更新用户
		 */
        UserEntity userEntity = userServiceImpl.findUserById(userId);
        userEntity.setUserLoginName(entity.getUserLoginName());
        userEntity.setUserName(entity.getUserName());
        userEntity.setOrganCode(entity.getOrganCode());
        userEntity.setOrganName(entity.getOrganName());
        userServiceImpl.updateUser(userEntity);
		/*
		 * 删除角色绑定信息
		 */
        roleServiceImpl.deleteAllUserRoleInfoByUserId(userId);
        if (StringUtils.isNoneBlank(roles)) {
            String[] roleIdArry = roles.split(",");
            for (String roleId : roleIdArry) {
                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setId(UUID.randomUUID().toString());
                userRoleEntity.setUser_id(userId);
                userRoleEntity.setRole_id(roleId);
                roleServiceImpl.addRoleForUser(userRoleEntity);
            }
        }
        jsonModel.setSuccess(true);
        jsonModel.setMsg("用户修改成功.");
        writeJson(jsonModel, response);
    }

    /**
     * 根绝userId删除用户
     *
     * @param userId
     */
    @Override
    @RequestMapping("/delUser.html")
    public void delUser(String userId, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        // 删除用户
        userServiceImpl.deleteUserById(userId);
        // 删除用户绑定角色
        roleServiceImpl.deleteAllUserRoleInfoByUserId(userId);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("用户删除成功.");
        writeJson(jsonModel, response);
    }

    /**
     * 解锁用户
     *
     * @param userId
     * @param response
     */
    @Override
    @RequestMapping("/unlockUser.html")
    public void unlockUser(String userId, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        UserEntity userEntity = userServiceImpl.findUserById(userId);
        userEntity.setLoginErrorCount(0);
        userEntity.setState(UserStateEnum.NORMAL.getEnValue());
        userEntity.setAutoUnlockTime(null);
        userServiceImpl.updateUser(userEntity);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("用户解锁成功.");
        writeJson(jsonModel, response);
    }

    /**
     * 停用
     */
    @Override
    @RequestMapping("/stopUser.html")
    public void stopUser(String userId, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        UserEntity userEntity = userServiceImpl.findUserById(userId);
        userEntity.setState(UserStateEnum.STOPED.getEnValue());
        userEntity.setAutoUnlockTime(null);
        userServiceImpl.updateUser(userEntity);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("用户停用成功.");
        writeJson(jsonModel, response);
    }

    /**
     * 启用用户
     */
    @Override
    @RequestMapping("/startUser.html")
    public void startUser(String userId, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        UserEntity userEntity = userServiceImpl.findUserById(userId);
        userEntity.setState(UserStateEnum.NORMAL.getEnValue());
        userEntity.setAutoUnlockTime(null);
        userServiceImpl.updateUser(userEntity);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("用户启用成功.");
        writeJson(jsonModel, response);
    }

    /**
     * 注销用户
     *
     * @param userId
     * @param response
     */
    @Override
    @RequestMapping("/cancleUser.html")
    public void cancleUser(String userId, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        UserEntity userEntity = userServiceImpl.findUserById(userId);
        userEntity.setState(UserStateEnum.CANCLE.getEnValue());
        userEntity.setAutoUnlockTime(null);
        userServiceImpl.updateUser(userEntity);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("用户注销成功.");
        writeJson(jsonModel, response);
    }

    /**
     * 重置用户登陆密码
     *
     * @param userId
     * @param response
     */
    @Override
    @RequestMapping("/resetLoginPwd.html")
    public void resetLoginPwd(String userId, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        UserEntity userEntity = userServiceImpl.findUserById(userId);
        userEntity.setPassword(MD5Utils.GetMD5Code(tsysParamService.findParamByCode("0001").getParam_value()));
        userServiceImpl.updateUser(userEntity);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("用户密码重置成功.");
        writeJson(jsonModel, response);
    }

    /**
     * 导出用户列表
     *
     * @param pager
     * @param response
     * @throws IOException
     */
    @RequestMapping("/exportUserList.html")
    public void exportUserList(UserPager pager, HttpServletResponse response) throws IOException {
        List<UserEntity> list = userServiceImpl.findUserByPage(pager);
        List<UserBeanForExport> userBeanForExportsList = new ArrayList<UserBeanForExport>();
        for (UserEntity userEntity : list) {
            UserBeanForExport userBeanForExport = new UserBeanForExport();
            try {
                BeanUtils.copyBean2Bean(userBeanForExport, userEntity);
                userBeanForExport.setCreate_time(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            userBeanForExportsList.add(userBeanForExport);
        }
        String fileFolder = System.getProperty("java.io.tmpdir");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = "UserList" + sdf.format(new Date()) + ".xlsx";
        String filePath = fileFolder + fileName;
        String pattern = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        String sheetTitle = "用户列表";
        String[] strings = {"用户登陆名", "用户名", "用户状态", "用户所属机构", "所属机构名", "账号创建时间"};
        List<String[]> headerList = new ArrayList<String[]>();
        headerList.add(strings);
        ExportExcelUtil<UserBeanForExport> excelUtil = new ExportExcelUtil<UserBeanForExport>();
        Workbook wk = new SXSSFWorkbook();
        Workbook workbook = excelUtil.doExportExcel1(wk, sheetTitle, headerList, userBeanForExportsList, pattern, 0,
                false);
        OutputStream out = null;
        out = new FileOutputStream(filePath);
        workbook.write(out);
        out.flush();
        out.close();
        writeJson(fileName, response);
    }

    /**
     * 下载文件
     *
     * @param fileName
     * @param response
     * @throws IOException
     */
    @RequestMapping("/downLoadFile.html")
    public void downLoadFile(String fileName, HttpServletResponse response) throws IOException {
        download(fileName, response);
    }
}
