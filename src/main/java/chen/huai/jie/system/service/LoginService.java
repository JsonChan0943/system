package chen.huai.jie.system.service;

import chen.huai.jie.system.bean.LoginBean;
import chen.huai.jie.system.entity.UserEntity;

/**
 * 登陆服务-接口
 * 
 * @author chenhuaijie
 * 
 */
public interface LoginService {
	/**
	 * 登陆
	 * 
	 * @return
	 */
	public UserEntity login(LoginBean loginBean);
}
