package base.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * ��������·���봦������Ӧ��ϵ
 * @author Tryin4Sage
 * @2018��3��3�� ����3:48:44
 */
public class HandlerMapping {
	private Map<String,Handler> handlerMap = new HashMap<String, Handler>();

	/**
	 * ����·�� ���� ������Ӧ��ϵ
	 * @param beans
	 */
	public void process(List<Object> beans) {
		for (Object obj : beans) {
			//�����ȡ�ֽ����ļ� ,�����ķ���
			Class<?> clazz = obj.getClass();
			Method[] methods = clazz.getDeclaredMethods();
			for (Method meth : methods) {
				//���ע�����
				RequestMapping rm = meth.getAnnotation(RequestMapping.class);
				if (rm==null) {
					continue;
				}
				//ע���valueֵ����·��
				String path = rm.value();
				Handler han = new Handler(obj, meth);
				//��Ӧ����map����
				handlerMap.put(path, han);
			}
		}
		System.out.println(handlerMap);
	}

	public Handler gethandler(String path) {
		return handlerMap.get(path);
	}
}
