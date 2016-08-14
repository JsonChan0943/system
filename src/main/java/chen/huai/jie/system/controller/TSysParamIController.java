package chen.huai.jie.system.controller;

import chen.huai.jie.system.entity.TsysParamEntity;
import chen.huai.jie.system.pager.TsysParamPager;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenhuaijie on 2016/8/14.
 */
public interface TSysParamIController {
    /**
     * 主页面
     *
     * @return
     */
    public String index();

    /**
     * 系统参数列表
     */
    public void list(TsysParamPager pager, HttpServletResponse response);

    /**
     * 判断参数编码是否可用
     */
    public void canParamCodeUse(String param_code, HttpServletResponse response);

    /**
     * 增加系统参数
     */
    public void addParam(TsysParamEntity entity, HttpServletResponse response);

    /**
     * 修改系统参数
     */
    public void modifyParam(TsysParamEntity entity, HttpServletResponse response);

    /**
     * 删除系统参数
     *
     * @param id
     * @param response
     */
    public void delParam(String id, HttpServletResponse response);

}
