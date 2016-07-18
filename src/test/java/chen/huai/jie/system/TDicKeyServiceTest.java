package chen.huai.jie.system;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import chen.huai.jie.system.entity.TDicKeyEntity;
import chen.huai.jie.system.service.TDicKeyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-*.xml" })
public class TDicKeyServiceTest {
	@Resource
	private TDicKeyService tDicKeyServiceImpl;

	@Test
	public void addDicKey() {
		for (int i = 0; i < 100; i++) {
			TDicKeyEntity entity = new TDicKeyEntity();
			entity.setId(UUID.randomUUID().toString());
			entity.setKey_code(i + "");
			entity.setKey_name("key" + i);
			entity.setRemark("备注");
			tDicKeyServiceImpl.addDicKey(entity);
		}
	}
}
