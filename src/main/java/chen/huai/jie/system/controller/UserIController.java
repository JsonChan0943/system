package chen.huai.jie.system.controller;

import chen.huai.jie.system.entity.UserEntity;
import chen.huai.jie.system.pager.UserPager;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenhuaijie on 2016/8/14.
 */
public interface UserIController {
    /**
     * 主页面
     *
     * @return
     */
    public String index();

    /**
     * 用户列表
     */
    public void list(UserPager pager, HttpServletResponse response);


    /**
     * 增加用户
     */
    public void addUser(UserEntity entity, String roles, HttpServletResponse response);


    /**
     * 修改用户
     */
    public void mofifyUser(UserEntity entity, String roles, HttpServletResponse response);

    /**
     * 根绝userId删除用户
     *
     * @param userId
     */
    public void delUser(String userId, HttpServletResponse response);

    /**
     * 解锁用户
     *
     * @param userId
     * @param response
     */
    public void unlockUser(String userId, HttpServletResponse response);

    /**
     * 停用
     */
    public void stopUser(String userId, HttpServletResponse response);

    /**
     * 启用用户
     */
    public void startUser(String userId, HttpServletResponse response);

    /**
     * 注销用户
     *
     * @param userId
     * @param response
     */
    public void cancleUser(String userId, HttpServletResponse response);

    /**
     * 重置用户登陆密码
     *
     * @param userId
     * @param response
     */
    public void resetLoginPwd(String userId, HttpServletResponse response);


}
