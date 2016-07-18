package chen.huai.jie.system.dao;

import java.util.List;

import chen.huai.jie.base.annotation.MyBatisRepository;
import chen.huai.jie.system.entity.TsysParamEntity;
import chen.huai.jie.system.pager.TsysParamPager;

/**
 * 系统参数表实体类
 * 
 * @author chenhuaijie
 * 
 */
@MyBatisRepository
public interface TsysParamDAO {
	/**
	 * 分页查找系统参数
	 * 
	 * @param pager
	 * @return
	 */
	public List<TsysParamEntity> findParamByPage(TsysParamPager pager);

	/**
	 * 统计系统参数数量
	 * 
	 * @param pager
	 * @return
	 */
	public Long countParamCnt(TsysParamPager pager);

	/**
	 * 增加系统参数
	 * 
	 * @param entity
	 */
	public void addParam(TsysParamEntity entity);

	/**
	 * 根据id查找系统参数
	 * 
	 * @param id
	 */
	public TsysParamEntity findParamById(String id);

	/**
	 * 更新系统参数
	 * 
	 * @param entity
	 */
	public void updateParam(TsysParamEntity entity);

	/**
	 * 删除系统参数
	 * 
	 * @param id
	 */
	public void deleteParamById(String id);

	/**
	 * 根据参数编码获取参数实体
	 * 
	 * @param param_code
	 * @return
	 */
	public TsysParamEntity findParamByCode(String param_code);

}
