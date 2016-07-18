package chen.huai.jie.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import chen.huai.jie.system.dao.TDicValueDAO;
import chen.huai.jie.system.entity.TDicValueEntity;
import chen.huai.jie.system.pager.TDicValuePager;
import chen.huai.jie.system.service.TDicValueService;

/**
 * 字典表-值-服务-实现类
 * 
 * @author chenhuaijie
 * 
 */
@Service
public class TDicValueServiceImpl implements TDicValueService {
	@Resource
	private TDicValueDAO tDicValueDAO;

	/**
	 * 分页查找
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public List<TDicValueEntity> findDicValueByPage(TDicValuePager pager) {
		return tDicValueDAO.findDicValueByPage(pager);
	}

	/**
	 * 统计数量
	 */
	@Override
	public Long countDicValueCnt(TDicValuePager pager) {
		return tDicValueDAO.countDicValueCnt(pager);
	}

	/**
	 * 增加字典表value
	 * 
	 * @param entity
	 */
	@Override
	public void addDicValue(TDicValueEntity entity) {
		tDicValueDAO.addDicValue(entity);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Override
	public void delDicValue(String id) {
		tDicValueDAO.delDicValue(id);
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 */
	@Override
	public void updateDicValue(TDicValueEntity entity) {
		tDicValueDAO.updateDicValue(entity);
	}

	/**
	 * 根据id查找实体类
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public TDicValueEntity findDicValueById(String id) {
		return tDicValueDAO.findDicValueById(id);
	}
}
