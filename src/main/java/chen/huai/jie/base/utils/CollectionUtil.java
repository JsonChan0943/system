package chen.huai.jie.base.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class CollectionUtil {
	/**
	 * 判断集合是否不为空
	 * @param collection
	 * @return
	 */
	public static boolean isNEmpty(Collection<?> collection){
	   if ((collection != null) && (collection.size() > 0)) {
		   return true;
	   }
	   return false;
	}

	/**
	 * 判断集合是否为空
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection){
	    return !isNEmpty(collection);
	}

	/**
	 * 判断Map是否不为空
	 * @param map
	 * @return
	 */
	public static boolean isNEmpty(Map<?, ?> map){
	    if ((map != null) && (map.size() > 0)) {
	    	return true;
	    }
	    return false;
	}

	/**
	 * 判断map是否为空
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> map){
	    return !isNEmpty(map);
	}

	/**
	 * 判断对象是不是集合
	 * @param obj
	 * @return
	 */
	public static boolean isCollection(Object obj){
	    if ((obj instanceof Collection)) {
	      return true;
	    }
	    return false;
	}

	/**
	 * 把Object转换为Collection
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Collection<Object> getCollection(Object obj){
	    if (obj == null){
	    	return null;
	    }
	    if (isCollection(obj)) {
	      return (Collection<Object>)obj;
	    }
	    return null;
	}

	/**
	 * 把Map转换成List
	 * @param map
	 * @return
	 */
	public static <T> List<T> convertMapToList(Map<?, T> map){
	    List list = new ArrayList();
	    for (Iterator localIterator = map.keySet().iterator(); localIterator.hasNext(); ) {
	    	Object o = localIterator.next();
	    	list.add(map.get(o));
	    }
	    return list;
	}

	/**
	 * 把数组转化为List
	 * @param list
	 * @return
	 */
	public static <T> T[] convertArrayToList(Collection<T> list) {
	    return (T[]) (list.size() > 0 ? list.toArray((Object[])Array.newInstance(list.iterator().next().getClass(), list.size())) : null);
	  }
}
