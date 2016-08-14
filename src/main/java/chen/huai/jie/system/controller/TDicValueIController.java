package chen.huai.jie.system.controller;

import chen.huai.jie.system.entity.TDicValueEntity;
import chen.huai.jie.system.pager.TDicValuePager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenhuaijie on 2016/8/14.
 */
public interface TDicValueIController {
    /**
     * 主页面
     *
     * @return
     */
    public String index(String key_code, HttpServletRequest request);

    /**
     * 列表数据
     */
    public void list(TDicValuePager pager, HttpServletResponse response);

    /**
     * 增加字典值
     *
     * @param entity
     * @param response
     */
    public void addDicValue(TDicValueEntity entity, HttpServletResponse response);

    /**
     * 修改
     */
    public void modifyDicValue(TDicValueEntity entity, HttpServletResponse response);

    /**
     * 删除字典值
     */
    public void delDicValue(String id, HttpServletResponse response);
}
