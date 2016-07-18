package chen.huai.jie.system.service;

import java.util.List;

import chen.huai.jie.system.entity.TDicValueEntity;
import chen.huai.jie.system.pager.TDicValuePager;

/**
 * 字典表-值-服务-接口
 * 
 * @author chenhuaijie
 * 
 */
public interface TDicValueService {

	/**
	 * 分页查找
	 * 
	 * @param pager
	 * @return
	 */
	public List<TDicValueEntity> findDicValueByPage(TDicValuePager pager);

	/**
	 * 统计数量
	 */
	public Long countDicValueCnt(TDicValuePager pager);

	/**
	 * 增加字典表value
	 * 
	 * @param entity
	 */
	public void addDicValue(TDicValueEntity entity);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delDicValue(String id);

	/**
	 * 更新
	 * 
	 * @param entity
	 */
	public void updateDicValue(TDicValueEntity entity);

	/**
	 * 根据id查找实体类
	 * 
	 * @param id
	 * @return
	 */
	public TDicValueEntity findDicValueById(String id);
}
