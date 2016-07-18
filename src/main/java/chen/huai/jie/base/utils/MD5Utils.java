package chen.huai.jie.base.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class MD5Utils 
 * @author liangjun
 * @version $Revision:0.1,$Date: 2015年8月26日$
 * Description: //MD5加密工具类
 * History: // 历史修改记录
 * <author> <time> <version > <desc>
 */
public class MD5Utils {
	
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5","6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	/**
	 * 功能:MD5加密
	 * @return
	 */
	public static String GetMD5Code(String strObj) {
	    String resultString = null;
	    try {
	        resultString = new String(strObj);
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        // md.digest() 该函数返回值为存放哈希值结果的byte数组
	        resultString = byteToString(md.digest(strObj.getBytes()));
	    } catch (NoSuchAlgorithmException ex) {
	        ex.printStackTrace();
	    }
	    return resultString;
	}
	
	/**
	 * 功能:转换字节数组为16进制字串
	 * @return
	 */
	private static String byteToString(byte[] bByte) {
	    StringBuffer sBuffer = new StringBuffer();
	    for (int i = 0; i < bByte.length; i++) {
	        sBuffer.append(byteToArrayString(bByte[i]));
	    }
	    return sBuffer.toString();
	}
    
	/**
	 * 功能:返回形式为数字跟字符串
	 * @return
	 */
	private static String byteToArrayString(byte bByte) {
	    int iRet = bByte;
	    if (iRet < 0) {
	        iRet += 256;
	    }
	    int iD1 = iRet / 16;
	    int iD2 = iRet % 16;
	    return strDigits[iD1] + strDigits[iD2];
	}
}
