package chen.huai.jie.system.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import chen.huai.jie.base.enums.enumc.UserStateEnum;
import chen.huai.jie.system.entity.UserEntity;
import chen.huai.jie.system.service.UserService;

/**
 * 定时任务-账户自动解锁
 * 
 * @author chenhuaijie
 * 
 */
@Component
public class AutoUnlockUser {
	private Logger logger = LoggerFactory.getLogger(AutoUnlockUser.class);
	@Resource
	private UserService userServiceImpl;

	/**
	 * 自动解锁用户
	 */
	public void autoUnlockUser() {
		logger.info("定时任务开始--自动解锁用户");
		Date now = new Date();
		// 获取所有的锁定的用户信息
		List<UserEntity> userEntities = userServiceImpl.findUsersByState(UserStateEnum.LOCKED.getEnValue());
		for (UserEntity userEntity : userEntities) {
			if (now.after(userEntity.getAuto_unlock_time())) {
				userEntity.setState(UserStateEnum.NORMAL.getEnValue());
				userEntity.setAuto_unlock_time(null);
				userEntity.setLogin_error_count(0);
				userServiceImpl.updateUser(userEntity);
				logger.info("成功解锁用户：" + userEntity.getUser_name());
			}
		}
		logger.info("定时任务结束--自动解锁用户");
	}
}
