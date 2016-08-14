package chen.huai.jie.system.controller;

import chen.huai.jie.system.pager.LogPager;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by chenhuaijie on 2016/8/14.
 */
public interface SysLogIController {
    /**
     * 主页面
     *
     * @return
     */
    public String index();

    /**
     * 获取数据
     *
     * @param pager
     * @param response
     */
    public void list(LogPager pager, HttpServletResponse response);

}
