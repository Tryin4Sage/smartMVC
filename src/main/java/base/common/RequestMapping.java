package base.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * ע��, Ϊ��ʹ·���ʹ�������Ӧ����
 * @author Tryin4Sage
 * @2018��3��3�� ����3:40:55
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	
	public String value();
}
