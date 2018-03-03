package base.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * 注解, 为了使路径和处理器对应起来
 * @author Tryin4Sage
 * @2018年3月3日 下午3:40:55
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	
	public String value();
}
