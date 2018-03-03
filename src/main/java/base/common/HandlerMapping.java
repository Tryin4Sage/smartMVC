package base.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 处理请求路径与处理器对应关系
 * @author Tryin4Sage
 * @2018年3月3日 下午3:48:44
 */
public class HandlerMapping {
	private Map<String,Handler> handlerMap = new HashMap<String, Handler>();

	/**
	 * 处理路径 对象 方法对应关系
	 * @param beans
	 */
	public void process(List<Object> beans) {
		for (Object obj : beans) {
			//反射获取字节码文件 ,和他的方法
			Class<?> clazz = obj.getClass();
			Method[] methods = clazz.getDeclaredMethods();
			for (Method meth : methods) {
				//获得注解对象
				RequestMapping rm = meth.getAnnotation(RequestMapping.class);
				if (rm==null) {
					continue;
				}
				//注解的value值就是路径
				String path = rm.value();
				Handler han = new Handler(obj, meth);
				//对应存入map集合
				handlerMap.put(path, han);
			}
		}
		System.out.println(handlerMap);
	}

	public Handler gethandler(String path) {
		return handlerMap.get(path);
	}
}
