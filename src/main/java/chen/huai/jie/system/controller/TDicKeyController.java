package chen.huai.jie.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import chen.huai.jie.base.controller.BaseController;
import chen.huai.jie.base.utils.JsonModel;
import chen.huai.jie.system.entity.TDicKeyEntity;
import chen.huai.jie.system.pager.TDicKeyPager;
import chen.huai.jie.system.service.TDicKeyService;

/**
 * 字典表-键-控制层
 *
 * @author chenhuaijie
 */
@Controller
@RequestMapping("/system/dickey")
public class TDicKeyController extends BaseController {
    @Resource
    private TDicKeyService tDicKeyServiceImpl;

    /**
     * 主页面
     *
     * @return
     */
    @RequestMapping("/index.html")
    public String index() {
        return "system/dic/SystemDicKeyIndex";
    }

    /**
     * 列表数据
     *
     * @param pager
     * @param response
     */
    @RequestMapping("/list.html")
    public void list(TDicKeyPager pager, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<TDicKeyEntity> list = tDicKeyServiceImpl.findDicKeyByPage(pager);
        Long total = tDicKeyServiceImpl.countDicKeyCnt(pager);
        map.put(ROWS, list);
        map.put(TOTAL, total);
        writeJson(map, response);
    }

    /**
     * 增加数据字典键
     *
     * @param entity
     */
    @RequestMapping("/addDicKey")
    public void addDicKey(TDicKeyEntity entity, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        entity.setId(UUID.randomUUID().toString());
        tDicKeyServiceImpl.addDicKey(entity);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("增加数据字典键记录成功.");
        writeJson(jsonModel, response);
    }

    /**
     * 修改数据字典键
     */
    @RequestMapping("/modifyDicKey.html")
    public void modifyDicKey(TDicKeyEntity entity, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        tDicKeyServiceImpl.updateDicKey(entity);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("修改数据字典键记录成功.");
        writeJson(jsonModel, response);
    }

    /**
     * @param id
     */
    @RequestMapping("/delDicKey.html")
    public void delDicKey(String id, HttpServletResponse response) {
        JsonModel jsonModel = new JsonModel();
        tDicKeyServiceImpl.delDicKey(id);
        jsonModel.setSuccess(true);
        jsonModel.setMsg("删除数据字典键记录成功.");
        writeJson(jsonModel, response);
    }
}
