package chen.huai.jie.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import chen.huai.jie.system.dao.OrganDAO;
import chen.huai.jie.system.entity.OrganEntity;
import chen.huai.jie.system.service.OrganService;

/**
 * 机构服务-实现类
 * 
 * @author chenhuaijie
 * 
 */
@Service
public class OrganServiceImpl implements OrganService {
	@Resource
	private OrganDAO organDAO;

	/**
	 * 根据父节点获取机构列表
	 * 
	 * @param pid
	 * @return
	 */
	@Override
	public List<OrganEntity> findOrganByPid(String pid) {
		return organDAO.findOrganByPid(pid);
	}

	/**
	 * 增加机构
	 * 
	 * @param entity
	 */
	@Override
	public void addOrgan(OrganEntity entity) {
		organDAO.addOrgan(entity);
	}

	/**
	 * 根据id获取机构实体
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public OrganEntity findOrganById(String id) {
		return organDAO.findOrganById(id);
	}

	/**
	 * 根据id删除机构
	 */
	@Override
	public void deleteOrganById(String id) {
		organDAO.deleteOrganById(id);
	}

	/**
	 * 更新机构
	 * 
	 * @param entity
	 */
	@Override
	public void updateOrgan(OrganEntity entity) {
		organDAO.updateOrgan(entity);
	}
}
