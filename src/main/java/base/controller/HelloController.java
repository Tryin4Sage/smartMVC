package base.controller;

import base.common.RequestMapping;

/**
 * 这是业务逻辑
 * @author Tryin4Sage
 * @2018年3月3日 下午3:22:58
 */
public class HelloController {

	public HelloController() {
		System.out.println("HelloController's is 实例化");
	}
	
	@RequestMapping("/hello.do")
	public String hello() {
		System.out.println("hello方法执行了");
		return "hello";
	}
}
