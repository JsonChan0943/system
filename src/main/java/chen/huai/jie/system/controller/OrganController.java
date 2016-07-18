package chen.huai.jie.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import chen.huai.jie.base.controller.BaseController;
import chen.huai.jie.base.enums.enumc.OrganLevelEnum;
import chen.huai.jie.base.utils.JsonModel;
import chen.huai.jie.system.bean.OrganBeanForTree;
import chen.huai.jie.system.entity.OrganEntity;
import chen.huai.jie.system.service.OrganService;

/**
 * 机构管理
 * 
 * @author chenhuaijie
 * 
 */
@Controller
@RequestMapping("/system/organ")
public class OrganController extends BaseController {
	@Resource
	private OrganService organServiceImpl;

	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping("/index.html")
	public String index() {
		return "system/organ/SystemOrganIndex";
	}

	/**
	 * 获取所有机构
	 */
	@RequestMapping("/getAllOrgan.html")
	public void getAllOrgan(HttpServletResponse response) {
		OrganEntity rooEntity = organServiceImpl.findOrganById("1");
		List<OrganBeanForTree> rootLevelOrganBeanList = new ArrayList<OrganBeanForTree>();
		OrganBeanForTree rootOrganBeanForTree = new OrganBeanForTree(rooEntity);
		// 一级机构集合
		List<OrganBeanForTree> firstLevelOrganBeanList = new ArrayList<OrganBeanForTree>();
		// 获取所有的一级菜单
		List<OrganEntity> firstLevelOrganEntities = organServiceImpl.findOrganByPid("1");// 一级菜单
		for (OrganEntity entity : firstLevelOrganEntities) {
			OrganBeanForTree firstLevelOrganBean = new OrganBeanForTree(entity);
			List<OrganEntity> secondLevelOrganEntities = organServiceImpl.findOrganByPid(entity.getId());// 二级菜单
			List<OrganBeanForTree> secondLevelOrganBeanList = new ArrayList<OrganBeanForTree>();
			for (OrganEntity subEntity : secondLevelOrganEntities) {
				OrganBeanForTree subBbean = new OrganBeanForTree(subEntity);
				secondLevelOrganBeanList.add(subBbean);
			}
			firstLevelOrganBean.setChildren(secondLevelOrganBeanList);
			firstLevelOrganBeanList.add(firstLevelOrganBean);
		}
		rootOrganBeanForTree.setChildren(firstLevelOrganBeanList);
		rootLevelOrganBeanList.add(rootOrganBeanForTree);
		writeJson(rootLevelOrganBeanList, response);
	}

	/**
	 * 判断当前节点能否创建自己够
	 */
	@RequestMapping("/canCreateSubOrgan.html")
	public void canCreateSubOrgan(String id, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		OrganEntity entity = organServiceImpl.findOrganById(id);
		// 三级菜单不能创建子菜单
		if (entity.getOrgan_level() == OrganLevelEnum.LEVEL_THREE.getEnValue()) {
			jsonModel.setSuccess(false);
			jsonModel.setMsg("三级机构不能创建子机构.");
		} else {
			jsonModel.setSuccess(true);
			jsonModel.setMsg("一、二级机构可以创建子机构.");
		}
		writeJson(jsonModel, response);
	}

	/**
	 * 增加机构
	 */
	@RequestMapping("/addOrgan.html")
	public void addOrgan(OrganEntity entity, Integer parent_organ_level, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		entity.setId(UUID.randomUUID().toString());
		if (parent_organ_level == OrganLevelEnum.LEVEL_ONE.getEnValue()) {
			entity.setOrgan_level(OrganLevelEnum.LEVEL_TWO.getEnValue());
		} else if (parent_organ_level == OrganLevelEnum.LEVEL_TWO.getEnValue()) {
			entity.setOrgan_level(OrganLevelEnum.LEVEL_THREE.getEnValue());
		}
		organServiceImpl.addOrgan(entity);
		jsonModel.setSuccess(true);
		jsonModel.setMsg("新增菜单成功.");
		writeJson(jsonModel, response);
	}

	/**
	 * 删除机构
	 * 
	 * @param id
	 */
	@RequestMapping("/delOrgan.html")
	public void delOrgan(String id, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		List<OrganEntity> list = organServiceImpl.findOrganByPid(id);
		if (list != null && list.size() > 0) {
			jsonModel.setSuccess(false);
			jsonModel.setMsg("该机构下还有子机构,请先删除子机构.");
		} else {
			organServiceImpl.deleteOrganById(id);
			jsonModel.setSuccess(true);
			jsonModel.setMsg("机构删除成功.");
		}
		writeJson(jsonModel, response);
	}

	/**
	 * 修改机构
	 * 
	 * @param entity
	 * @param response
	 */
	@RequestMapping("/modifyOrgan.html")
	public void modifyOrgan(OrganEntity entity, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		OrganEntity organEntity = organServiceImpl.findOrganById(entity.getId());
		/*
		 * 更新字段
		 */
		organEntity.setOrgan_code(entity.getOrgan_code());// 菜单编码
		organEntity.setOrgan_name(entity.getOrgan_name());// 菜单名字
		organEntity.setSort(entity.getSort());//
		organEntity.setDescription(entity.getDescription());// 描述
		organEntity.setRemark(entity.getRemark());// 备注
		organServiceImpl.updateOrgan(organEntity);
		jsonModel.setSuccess(true);
		jsonModel.setMsg("机构更新成功.");
		writeJson(jsonModel, response);
	}
}
