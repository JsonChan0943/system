package chen.huai.jie.system.dao;

import java.util.List;

import chen.huai.jie.base.annotation.MyBatisRepository;
import chen.huai.jie.system.entity.OrganEntity;

/**
 * 机构实体DAO
 * 
 * @author chenhuaijie
 * 
 */
@MyBatisRepository
public interface OrganDAO {
	/**
	 * 根据父节点获取机构列表
	 * 
	 * @param pid
	 * @return
	 */
	public List<OrganEntity> findOrganByPid(String pid);

	/**
	 * 增加机构
	 * 
	 * @param entity
	 */
	public void addOrgan(OrganEntity entity);

	/**
	 * 根据id获取机构实体
	 * 
	 * @param id
	 * @return
	 */
	public OrganEntity findOrganById(String id);

	/**
	 * 根据id删除机构
	 */
	public void deleteOrganById(String id);

	/**
	 * 更新机构
	 * 
	 * @param entity
	 */
	public void updateOrgan(OrganEntity entity);

}
