package base.controller;

import base.common.RequestMapping;

/**
 * ����ҵ���߼�
 * @author Tryin4Sage
 * @2018��3��3�� ����3:22:58
 */
public class HelloController {

	public HelloController() {
		System.out.println("HelloController's is ʵ����");
	}
	
	@RequestMapping("/hello.do")
	public String hello() {
		System.out.println("hello����ִ����");
		return "hello";
	}
}
