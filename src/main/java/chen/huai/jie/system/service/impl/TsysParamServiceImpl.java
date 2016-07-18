package chen.huai.jie.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import chen.huai.jie.system.dao.TsysParamDAO;
import chen.huai.jie.system.entity.TsysParamEntity;
import chen.huai.jie.system.pager.TsysParamPager;
import chen.huai.jie.system.service.TsysParamService;

/**
 * 系统参数服务-实现类
 * 
 * @author chenhuaijie
 * 
 */
@Service
public class TsysParamServiceImpl implements TsysParamService {
	@Resource
	private TsysParamDAO tsysParamDAO;

	/**
	 * 分页查找系统参数
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public List<TsysParamEntity> findParamByPage(TsysParamPager pager) {
		return tsysParamDAO.findParamByPage(pager);
	}

	/**
	 * 统计系统参数数量
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public Long countParamCnt(TsysParamPager pager) {
		return tsysParamDAO.countParamCnt(pager);
	}

	/**
	 * 增加系统参数
	 * 
	 * @param entity
	 */
	@Override
	public void addParam(TsysParamEntity entity) {
		tsysParamDAO.addParam(entity);
	}

	/**
	 * 根据id查找系统参数
	 * 
	 * @param id
	 */
	@Override
	public TsysParamEntity findParamById(String id) {
		return tsysParamDAO.findParamById(id);
	}

	/**
	 * 更新系统参数
	 * 
	 * @param entity
	 */
	@Override
	public void updateParam(TsysParamEntity entity) {
		tsysParamDAO.updateParam(entity);
	}

	/**
	 * 删除系统参数
	 * 
	 * @param id
	 */
	@Override
	public void deleteParamById(String id) {
		tsysParamDAO.deleteParamById(id);
	}

	/**
	 * 根据参数编码获取参数实体
	 * 
	 * @param param_code
	 * @return
	 */
	@Override
	public TsysParamEntity findParamByCode(String param_code) {
		return tsysParamDAO.findParamByCode(param_code);
	}
}
