package chen.huai.jie.system.controller;

import chen.huai.jie.base.controller.BaseController;
import chen.huai.jie.base.enums.enumc.UserStateEnum;
import chen.huai.jie.base.system.ConstantFile;
import chen.huai.jie.base.utils.*;
import chen.huai.jie.system.bean.LoginBean;
import chen.huai.jie.system.bean.ModifyPwdBean;
import chen.huai.jie.system.entity.UserEntity;
import chen.huai.jie.system.service.LoginService;
import chen.huai.jie.system.service.UserService;
import chen.huai.jie.system.util.RandomValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 登陆控制器
 *
 * @author chenhuaijie
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
    @Resource
    private LoginService loginServiceImpl;
    @Resource
    private UserService userServiceImpl;

    /**
     * 密码最大输错次数
     */
    private static Integer allow_login_error_limit = Integer.valueOf(GlobalConst.getConfig("allow_login_error_limit"));

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @RequestMapping("/login.html")
    public String goLogin(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    /**
     * 登陆
     */
    @RequestMapping("/doLogin.html")
    public void doLogin(@Valid LoginBean loginBean, BindingResult errors, HttpServletRequest request,
                        HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        /*
         * 实体类校验
		 */
        if (errors.hasErrors()) {
            List<ObjectError> list = errors.getAllErrors();
            StringBuffer errorStr = new StringBuffer();
            for (ObjectError error : list) {
                String str = error.getDefaultMessage();
                errorStr.append(str + " ");
            }
            jsonModel.setSuccess(false);
            jsonModel.setMsg(errorStr.toString());
            logger.info("用户" + loginBean.getUser_login_name() + "登陆失败,原因：" + errorStr);
            writeJson(jsonModel, response);
            return;
        }
        // 从session获取验证码
        HttpSession session = request.getSession();
        String verifyCodeFromSession = (String) session.getAttribute(RandomValidateCode.RANDOMCODEKEY);
        if (StringUtils.endsWithIgnoreCase(verifyCodeFromSession, loginBean.getVerifyCode())) {
            UserEntity userEntity = loginServiceImpl.login(loginBean);
            if (userEntity == null) {
                String msg = "用户不存在";
                jsonModel.setSuccess(false);
                jsonModel.setMsg(msg);
                logger.info("用户" + loginBean.getUser_login_name() + "登陆失败,原因：" + msg);
            } else {
                // 密码正确
                if (StringUtils.equals(userEntity.getPassword(), MD5Utils.GetMD5Code(loginBean.getPassword()))) {
                    if (userEntity.getState() == UserStateEnum.LOCKED.getEnValue()) {
                        String msg = "账号已锁定";
                        jsonModel.setSuccess(false);
                        jsonModel.setMsg(msg);
                        logger.info("用户" + loginBean.getUser_login_name() + "登陆失败,原因：" + msg);
                    } else if (userEntity.getState() == UserStateEnum.STOPED.getEnValue()) {
                        String msg = "账号已停用";
                        jsonModel.setSuccess(false);
                        jsonModel.setMsg(msg);
                        logger.info("用户" + loginBean.getUser_login_name() + "登陆失败,原因：" + msg);
                    } else if (userEntity.getState() == UserStateEnum.CANCLE.getEnValue()) {
                        String msg = "账号已注销";
                        jsonModel.setSuccess(false);
                        jsonModel.setMsg(msg);
                        logger.info("用户" + loginBean.getUser_login_name() + "登陆失败,原因：" + msg);
                    } else {
                        jsonModel.setSuccess(true);
                        session.setAttribute(ConstantFile.SESSION_USER_BEAN, userEntity);
                        logger.info("用户" + loginBean.getUser_login_name() + "登陆成功.");
                        addLog(request, "登陆系统", "用户" + loginBean.getUser_login_name() + "登陆成功.");
                        /*
                         * 登陆成功,清空前面的错误登陆次数记录和自动解锁时间
						 */
                        userEntity.setAuto_unlock_time(null);
                        userEntity.setLogin_error_count(0);
                        userEntity.setLast_login_time(new Date());// 记录最近一次登陆时间
                        userServiceImpl.updateUser(userEntity);
                    }
                } else {// 密码错误
                    Integer login_error_cnt = userEntity.getLogin_error_count();// 获取密码输入错误次数
                    if (login_error_cnt == null || login_error_cnt == 0) {
                        login_error_cnt = 1;
                    }
                    Integer left_allow_count = allow_login_error_limit - login_error_cnt;// 剩余密码可输入次数
                    if (left_allow_count == 0) {
                        userEntity.setState(UserStateEnum.LOCKED.getEnValue());
                        userEntity.setAuto_unlock_time(DateUtils.addDays(new Date(), 1));
                        userServiceImpl.updateUser(userEntity);
                        jsonModel.setSuccess(false);
                        jsonModel.setMsg("账号已锁定.");
                        logger.info("用户" + loginBean.getUser_login_name() + "密码输错" + allow_login_error_limit
                                + "次,账号锁定.");
                    } else {
                        login_error_cnt++;
                        userEntity.setLogin_error_count(login_error_cnt);
                        userServiceImpl.updateUser(userEntity);
                        String msg = "密码错误,还有" + left_allow_count + "次输入机会.";
                        jsonModel.setSuccess(false);
                        jsonModel.setMsg(msg);
                        logger.info("用户" + loginBean.getUser_login_name() + "登陆失败,原因：" + msg);
                    }
                }
            }
        } else {
            String msg = "验证码错误.";
            jsonModel.setSuccess(false);
            jsonModel.setMsg(msg);
            logger.info("用户" + loginBean.getUser_login_name() + "登陆失败,原因：" + msg);
        }
        writeJson(jsonModel, response);
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping("/logOut.html")
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        addLog(request, "退出系统", "用户退出系统成功.");
        session.invalidate();
        return "login";
    }

    /**
     * 登陆跳转到主页面
     *
     * @return
     */
    @RequestMapping("/goIndex.html")
    public String goIndex() {
        return "index";
    }

    /**
     * 修改密码
     */
    @RequestMapping("/modifyPwd.html")
    public void modifyPwd(ModifyPwdBean bean, HttpServletRequest request, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        UserEntity loginUser = (UserEntity) request.getSession().getAttribute(ConstantFile.SESSION_USER_BEAN);
        UserEntity userEntity = userServiceImpl.findUserById(loginUser.getId());
        if (StringUtils.equals(userEntity.getPassword(), MD5Utils.GetMD5Code(bean.getOld_password()))) {
            if (StringUtils.equals(bean.getNew_password(), bean.getConfirm_new_password())) {
                if (StringUtils.equals(bean.getOld_password(), bean.getNew_password())) {
                    jsonModel.setSuccess(false);
                    jsonModel.setMsg("新密码不能与原密码一样.");
                } else {
                    userEntity.setPassword(MD5Utils.GetMD5Code(bean.getNew_password()));
                    userEntity.setLast_pwd_modify_time(new Date());// 记录最近一次密码修改时间
                    userServiceImpl.updateUser(userEntity);
                    jsonModel.setSuccess(true);
                    jsonModel.setMsg("密码修改成功.");
                    addLog(request, "修改登陆密码", "用户" + userEntity.getUser_name() + "修改登陆密码成功.");
                }
            } else {
                jsonModel.setSuccess(false);
                jsonModel.setMsg("两次密码输入不一致.");
            }
        } else {
            jsonModel.setSuccess(false);
            jsonModel.setMsg("原密码输入错误.");
        }
        writeJson(jsonModel, response);
    }

    /**
     * 获取验证码
     */
    @RequestMapping("/verifyCode.html")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        String verifyCodeString = randomValidateCode.getRandcode(request, response);
        HttpSession session = request.getSession();
        session.setAttribute(RandomValidateCode.RANDOMCODEKEY, verifyCodeString);
    }
}
