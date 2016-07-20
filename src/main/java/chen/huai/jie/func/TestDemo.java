package chen.huai.jie.func;

import chen.huai.jie.base.utils.MD5Utils;

import java.util.UUID;

/**
 * Created by Administrator on 2016/7/19.
 */
public class TestDemo {
    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(MD5Utils.GetMD5Code("123456"));
    }
}
