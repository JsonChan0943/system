package chen.huai.jie.system;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import chen.huai.jie.system.entity.RoleEntity;
import chen.huai.jie.system.pager.RolePager;
import chen.huai.jie.system.service.RoleService;

/**
 * 角色Service单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-*.xml" })
public class RoleServiceTest {
	@Resource
	private RoleService roleServiceImpl;;

	@Test
	public void findRoleByPage() {
		RolePager pager = new RolePager();
		pager.setPage(1L);
		pager.setRows(100L);
		// pager.setSort("menu_name");
		// pager.setOrder("asc");
		List<RoleEntity> roles = roleServiceImpl.findRoleByPage(pager);
		for (RoleEntity entity : roles) {
			System.out.println(entity.getRole_name());
		}
	}

	@Test
	public void addRole() {
		RoleEntity entity = null;
		for (int i = 0; i < 100; i++) {
			entity = new RoleEntity();
			entity.setId(UUID.randomUUID().toString());
			entity.setRole_code("角色code" + i);
			entity.setRole_name("角色名" + i);
			entity.setDescription("描述" + i);
			entity.setRemark("备注" + i);
			roleServiceImpl.addRole(entity);
		}
	}
}
