package chen.huai.jie.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import chen.huai.jie.system.bean.LoginBean;
import chen.huai.jie.system.dao.UserDAO;
import chen.huai.jie.system.entity.UserEntity;
import chen.huai.jie.system.service.LoginService;

/**
 * 登陆服务-实现类
 * 
 * @author chenhuaijie
 * 
 */
@Service
public class LoginServiceImpl implements LoginService {
	@Resource
	private UserDAO userDAO;

	/**
	 * 登陆
	 */
	@Override
	public UserEntity login(LoginBean loginBean) {
		return userDAO.findUserByUserLoginName(loginBean.getUser_login_name());
	}

}
