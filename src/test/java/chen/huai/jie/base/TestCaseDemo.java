package chen.huai.jie.base;

import chen.huai.jie.base.exception.SystemException;
import junit.framework.TestCase;

/**
 * Created by chenhuaijie on 2016/7/18.
 */
public class TestCaseDemo extends TestCase {
    /**
     * test1
     */
    public void test1() {
        System.out.println("TestCase类测试.");
    }

    /**
     * test2
     */
    public void test2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }
}
