package chen.huai.jie.system.service;

import java.util.List;

import chen.huai.jie.system.entity.TDicKeyEntity;
import chen.huai.jie.system.pager.TDicKeyPager;

/**
 * 字典表-键-服务-接口
 * 
 * @author chenhuaijie
 * 
 */
public interface TDicKeyService {
	/**
	 * 分页查找
	 * 
	 * @param pager
	 * @return
	 */
	public List<TDicKeyEntity> findDicKeyByPage(TDicKeyPager pager);

	/**
	 * 统计数量
	 * 
	 * @param pager
	 * @return
	 */
	public Long countDicKeyCnt(TDicKeyPager pager);

	/**
	 * 数据字典Key增加
	 * 
	 * @param entity
	 */
	public void addDicKey(TDicKeyEntity entity);

	/**
	 * 修改数据字典键
	 * 
	 * @param entity
	 */
	public void updateDicKey(TDicKeyEntity entity);

	/**
	 * 删除数据字典键
	 * 
	 * @param id
	 */
	public void delDicKey(String id);

}
