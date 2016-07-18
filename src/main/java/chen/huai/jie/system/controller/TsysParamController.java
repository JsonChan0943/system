package chen.huai.jie.system.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import chen.huai.jie.base.controller.BaseController;
import chen.huai.jie.base.utils.BeanUtils;
import chen.huai.jie.base.utils.DateFormatUtils;
import chen.huai.jie.base.utils.ExportExcelUtil;
import chen.huai.jie.base.utils.JsonModel;
import chen.huai.jie.system.bean.export.ParamBeanForExport;
import chen.huai.jie.system.entity.TsysParamEntity;
import chen.huai.jie.system.pager.TsysParamPager;
import chen.huai.jie.system.service.TsysParamService;

/**
 * 系统参数服务
 * 
 * @author chenhuaijie
 * 
 */
@Controller
@RequestMapping("/system/param")
public class TsysParamController extends BaseController {
	@Resource
	private TsysParamService tsysParamService;

	/**
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping("/index.html")
	public String index() {
		return "system/param/SystemParamIndex";
	}

	/**
	 * 系统参数列表
	 */
	@RequestMapping("/list.html")
	public void list(TsysParamPager pager, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TsysParamEntity> list = tsysParamService.findParamByPage(pager);
		Long total = tsysParamService.countParamCnt(pager);
		map.put(ROWS, list);
		map.put(TOTAL, total);
		writeJson(map, response);
	}

	/**
	 * 判断参数编码是否可用
	 */
	@RequestMapping("/canParamCodeUse.html")
	public void canParamCodeUse(String param_code, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		TsysParamEntity tsysParamEntity = tsysParamService.findParamByCode(param_code);
		if (tsysParamEntity == null) {
			jsonModel.setSuccess(true);
			jsonModel.setMsg("参数编码可用.");
		} else {
			jsonModel.setSuccess(false);
			jsonModel.setMsg("参数编码不可用.");
		}
		writeJson(jsonModel, response);
	}

	/**
	 * 增加系统参数
	 */
	@RequestMapping("/addParam.html")
	public void addParam(TsysParamEntity entity, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		String pk = UUID.randomUUID().toString();
		entity.setId(pk);// 主键
		entity.setCreate_time(new Date());// 创建时间
		tsysParamService.addParam(entity);
		jsonModel.setSuccess(true);
		jsonModel.setMsg("新增系统参数成功.");
		writeJson(jsonModel, response);
	}

	/**
	 * 修改系统参数
	 */
	@RequestMapping("/modifyParam.html")
	public void modifyParam(TsysParamEntity entity, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		TsysParamEntity tsysParamEntity = tsysParamService.findParamById(entity.getId());
		tsysParamEntity.setParam_name(entity.getParam_name());
		tsysParamEntity.setParam_value(entity.getParam_value());
		tsysParamEntity.setRemark(entity.getRemark());
		tsysParamEntity.setModify_time(new Date());
		tsysParamService.updateParam(tsysParamEntity);
		jsonModel.setSuccess(true);
		jsonModel.setMsg("修改系统参数成功.");
		writeJson(jsonModel, response);
	}

	/**
	 * 删除系统参数
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/delParam.html")
	public void delParam(String id, HttpServletResponse response) {
		JsonModel jsonModel = new JsonModel();
		tsysParamService.deleteParamById(id);
		jsonModel.setSuccess(true);
		jsonModel.setMsg("删除系统参数成功.");
		writeJson(jsonModel, response);
	}

	/**
	 * 导出参数列表
	 * 
	 * @param pager
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/exportParamList.html")
	public void exportUserList(TsysParamPager pager, HttpServletResponse response) throws IOException {
		List<TsysParamEntity> list = tsysParamService.findParamByPage(pager);
		List<ParamBeanForExport> paramBeanForExportList = new ArrayList<ParamBeanForExport>();
		for (TsysParamEntity tsysParamEntity : list) {
			ParamBeanForExport userBeanForExport = new ParamBeanForExport();
			try {
				BeanUtils.copyBean2Bean(userBeanForExport, tsysParamEntity);
				userBeanForExport.setCreate_time(DateFormatUtils.format(tsysParamEntity.getCreate_time(),
						"yyyy-MM-dd HH:mm:ss"));
				if (tsysParamEntity.getModify_time() != null) {
					userBeanForExport.setModify_time(DateFormatUtils.format(tsysParamEntity.getModify_time(),
							"yyyy-MM-dd HH:mm:ss"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(e.getMessage());
			}
			paramBeanForExportList.add(userBeanForExport);
		}
		String fileFolder = System.getProperty("java.io.tmpdir");
		String fileName = "SystemParamsList.xlsx";
		String filePath = fileFolder + fileName;
		String pattern = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		String sheetTitle = "系统参数列表";
		String[] strings = { "参数编码", "参数名", "参数值", "创建时间", "修改时间", "备注" };
		List<String[]> headerList = new ArrayList<String[]>();
		headerList.add(strings);
		ExportExcelUtil<ParamBeanForExport> excelUtil = new ExportExcelUtil<ParamBeanForExport>();
		Workbook wk = new SXSSFWorkbook();
		Workbook workbook = excelUtil.doExportExcel1(wk, sheetTitle, headerList, paramBeanForExportList, pattern, 0,
				false);
		OutputStream out = null;
		out = new FileOutputStream(filePath);
		workbook.write(out);
		out.flush();
		out.close();
		writeJson(fileName, response);
	}

	/**
	 * 下载文件
	 * 
	 * @param fileName
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/downLoadFile.html")
	public void downLoadFile(String fileName, HttpServletResponse response) throws IOException {
		download(fileName, response);
	}
}
