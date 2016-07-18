package chen.huai.jie.system;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import chen.huai.jie.system.entity.TDicValueEntity;
import chen.huai.jie.system.service.TDicValueService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-*.xml" })
public class TDicValueServiceTest {
	@Resource
	private TDicValueService tDicValueServiceImpl;

	@Test
	public void addDicValue() {
		for (int i = 0; i < 100; i++) {
			TDicValueEntity entity = new TDicValueEntity();
			entity.setId(UUID.randomUUID().toString());
			entity.setKey_code(i + "");
			entity.setValue_name("value" + i);
			entity.setSort(i);
			entity.setRemark("备注");
			tDicValueServiceImpl.addDicValue(entity);
		}
	}
}
