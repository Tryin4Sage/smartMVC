package base.common;

import java.lang.reflect.Method;
/**
 * ���Ǹ���װ����,����obj���䷽��
 * @author Tryin4Sage
 * @2018��3��3�� ����4:05:48
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
