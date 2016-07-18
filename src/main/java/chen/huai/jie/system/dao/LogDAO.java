package chen.huai.jie.system.dao;

import java.util.List;

import chen.huai.jie.base.annotation.MyBatisRepository;
import chen.huai.jie.system.entity.LogEntity;
import chen.huai.jie.system.pager.LogPager;

/**
 * 日志DAO
 * 
 * @author chenhuaijie
 * 
 */
@MyBatisRepository
public interface LogDAO {
	/**
	 * 分页查找日志
	 * 
	 * @param pager
	 * @return
	 */
	public List<LogEntity> findLogByPage(LogPager pager);

	/**
	 * 统计日志数量
	 * 
	 * @param pager
	 * @return
	 */
	public Long countLogCnt(LogPager pager);

	/**
	 * 记录日志
	 * 
	 * @param entity
	 */
	public void addLog(LogEntity entity);
}
