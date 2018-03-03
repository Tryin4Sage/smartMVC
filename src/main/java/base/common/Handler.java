package base.common;

import java.lang.reflect.Method;
/**
 * 这是个包装对象,整合obj和其方法
 * @author Tryin4Sage
 * @2018年3月3日 下午4:05:48
 */
public class Handler {
	private Object obj;
	private Method meth;
	
	public Handler(Object obj, Method meth) {
		this.obj = obj;
		this.meth = meth;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Method getMeth() {
		return meth;
	}
	public void setMeth(Method meth) {
		this.meth = meth;
	}
	
}
