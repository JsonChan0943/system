package chen.huai.jie.base.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheUtils {
	public void setObject() {
		CacheManager cacheManager = CacheManager.getInstance();
		// 获取ehcache配置文件中的一个cache
		Cache sample = cacheManager.getCache("sample");
		// 添加数据到缓存中
		Element element = new Element("key", "val");
		sample.put(element);
		// 获取缓存中的对象，注意添加到cache中对象要序列化 实现Serializable接口
		Element result = sample.get("key");
		System.out.println(result.getObjectValue());
	}
}
