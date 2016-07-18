package chen.huai.jie.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import chen.huai.jie.base.controller.BaseController;
import chen.huai.jie.base.utils.JsonModel;
import chen.huai.jie.system.entity.TDicValueEntity;
import chen.huai.jie.system.pager.TDicValuePager;
import chen.huai.jie.system.service.TDicValueService;

/**
 * 字典表-值-控制层
 * 
 * @author chenhuaijie
 * 
 */
@Controller
@RequestMapping("/system/dicvalue")
public class TDicValueController extends BaseController {
	@Resource
	private TDicValueService tDicValueServiceImpl;

	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping("/index.html")
	public String index(String key_code, HttpServletRequest request) {
		request.setAttribute("key_code", key_code);
		return "system/dic/SystemDicValueIndex";
	}

	/**
	 * 列表数据
	 */
	@RequestMapping("/list.html")
	public void list(TDicValuePager pager, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TDicValueEntity> list = tDicValueServiceImpl.findDicValueByPage(pager);
		Long total = tDicValueServiceImpl.countDicValueCnt(pager);
		map.put(ROWS, list);
		map.put(TOTAL, total);
		writeJson(map, response);
	}

	/**
	 * 增加字典值
	 * 
	 * @param entity
	 * @param response
	 */
	@RequestMapping("/addDicValue.html")
	public void addDicValue(TDicValueEntity entity, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		entity.setId(UUID.randomUUID().toString());
		tDicValueServiceImpl.addDicValue(entity);
		jsonModel.setSuccess(true);
		jsonModel.setMsg("增加数据字典值记录成功.");
		writeJson(jsonModel, response);
	}

	/**
	 * 修改
	 */
	@RequestMapping("/modifyDicValue.html")
	public void modifyDicValue(TDicValueEntity entity, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		TDicValueEntity tDicValueEntity = tDicValueServiceImpl.findDicValueById(entity.getId());
		tDicValueEntity.setValue_code(entity.getValue_code());
		tDicValueEntity.setValue_name(entity.getValue_name());
		tDicValueEntity.setSort(entity.getSort());
		tDicValueEntity.setRemark(entity.getRemark());
		tDicValueServiceImpl.updateDicValue(tDicValueEntity);
		jsonModel.setSuccess(true);
		jsonModel.setMsg("更新数据字典值记录成功.");
		writeJson(jsonModel, response);
	}

	/**
	 * 删除字典值
	 */
	@RequestMapping("/delDicValue.html")
	public void delDicValue(String id, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		tDicValueServiceImpl.delDicValue(id);
		jsonModel.setSuccess(true);
		jsonModel.setMsg("删除数据字典值记录成功.");
		writeJson(jsonModel, response);
	}
}
