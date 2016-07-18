package chen.huai.jie.system;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import chen.huai.jie.system.entity.MenuEntity;
import chen.huai.jie.system.pager.MenuPager;
import chen.huai.jie.system.service.MenuService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-*.xml" })
public class MenuServiceTest {
	@Resource
	private MenuService menuServiceImpl;

	@Test
	public void findMenuByPage() {
		MenuPager pager = new MenuPager();
		pager.setPage(1L);
		pager.setRows(100L);
		pager.setSort("menu_name");
		pager.setOrder("asc");
		List<MenuEntity> menus = menuServiceImpl.findMenuByPage(pager);
		for (MenuEntity entity : menus) {
			System.out.println(entity.getMenu_name());
		}
	}

	@Test
	public void addMenu() {
		MenuEntity entity = null;
		for (int i = 0; i < 100; i++) {
			entity = new MenuEntity();
			entity.setId(UUID.randomUUID().toString());
			entity.setPid(UUID.randomUUID().toString());
			entity.setMenu_name("菜单" + i);
			menuServiceImpl.addMenu(entity);
		}
	}

	@Test
	public void findMenuById() {
		String id = "1";
		MenuEntity entity = menuServiceImpl.findMenuById(id);
		System.out.println(entity.getMenu_name());
	}
}
