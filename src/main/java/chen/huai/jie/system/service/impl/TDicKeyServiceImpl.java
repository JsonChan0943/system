package chen.huai.jie.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import chen.huai.jie.system.dao.TDicKeyDAO;
import chen.huai.jie.system.entity.TDicKeyEntity;
import chen.huai.jie.system.pager.TDicKeyPager;
import chen.huai.jie.system.service.TDicKeyService;

/**
 * 字典表-键-服务-实现类
 * 
 * @author chenhuaijie
 * 
 */
@Service
public class TDicKeyServiceImpl implements TDicKeyService {

	@Resource
	private TDicKeyDAO tDicKeyDAO;

	/**
	 * 分页查找
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public List<TDicKeyEntity> findDicKeyByPage(TDicKeyPager pager) {
		return tDicKeyDAO.findDicKeyByPage(pager);
	}

	/**
	 * 统计数量
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public Long countDicKeyCnt(TDicKeyPager pager) {
		return tDicKeyDAO.countDicKeyCnt(pager);
	}

	/**
	 * 数据字典Key增加
	 * 
	 * @param entity
	 */
	@Override
	public void addDicKey(TDicKeyEntity entity) {
		tDicKeyDAO.addDicKey(entity);
	}

	/**
	 * 修改数据字典键
	 * 
	 * @param entity
	 */
	@Override
	public void updateDicKey(TDicKeyEntity entity) {
		tDicKeyDAO.updateDicKey(entity);
	}

	/**
	 * 删除数据字典键
	 * 
	 * @param id
	 */
	@Override
	public void delDicKey(String id) {
		tDicKeyDAO.delDicKey(id);
	}

}
