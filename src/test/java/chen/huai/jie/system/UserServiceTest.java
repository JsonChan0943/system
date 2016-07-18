package chen.huai.jie.system;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import chen.huai.jie.base.utils.DateFormatUtils;
import chen.huai.jie.system.entity.UserEntity;
import chen.huai.jie.system.pager.UserPager;
import chen.huai.jie.system.service.UserService;

/**
 * 用户Service单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-*.xml" })
public class UserServiceTest extends TestCase{
	@Resource
	private UserService userServiceImpl;

	@Test
	public void findUserByPage() {
		UserPager pager = new UserPager();
		pager.setPage(1L);
		pager.setRows(100L);
		// pager.setSort("menu_name");
		// pager.setOrder("asc");
		List<UserEntity> users = userServiceImpl.findUserByPage(pager);
		for (UserEntity entity : users) {
			System.out.println(DateFormatUtils.format(entity.getCreate_time(), "yyyy-MM-dd HH:mm:ss"));
			System.out.println(entity.getUser_login_name());
		}
	}

	@Test
	public void addUser() {
		UserEntity entity = null;
		for (int i = 0; i < 100; i++) {
			entity = new UserEntity();
			entity.setId(UUID.randomUUID().toString());
			entity.setUser_login_name("登陆名" + i);
			entity.setUser_name("用户名" + i);
			entity.setPassword("123456");
			entity.setState(1);
			userServiceImpl.addUser(entity);
		}
	}
}
