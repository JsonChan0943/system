package chen.huai.jie.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import chen.huai.jie.system.dao.LogDAO;
import chen.huai.jie.system.entity.LogEntity;
import chen.huai.jie.system.pager.LogPager;
import chen.huai.jie.system.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	@Resource
	private LogDAO logDAO;

	/**
	 * 分页查找日志
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public List<LogEntity> findLogByPage(LogPager pager) {
		return logDAO.findLogByPage(pager);
	}

	/**
	 * 统计日志数量
	 * 
	 * @param pager
	 * @return
	 */
	@Override
	public Long countLogCnt(LogPager pager) {
		return logDAO.countLogCnt(pager);
	}

	/**
	 * 记录日志
	 * 
	 * @param entity
	 */
	@Override
	public void addLog(LogEntity entity) {
		logDAO.addLog(entity);
	}
}
