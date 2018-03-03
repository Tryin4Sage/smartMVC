package base.web;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import base.common.Handler;
import base.common.HandlerMapping;
import base.common.ViewResolve;
/**
 * �ܿ�����; 
 * ����������ֱ���(·���ʹ�����,��ͼ��jsp)
 * @author Tryin4Sage
 * @2018��3��3�� ����2:44:57
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Object> beans = new ArrayList<Object>();
	private HandlerMapping hm;
	private ViewResolve vr;
	
	@Override
	public void init() throws ServletException {
		//ͨ����ʼ������,��������ļ���
		ServletConfig config = getServletConfig();
		String fileName = config.getInitParameter("config");
		System.out.println(fileName);
		
		//dom4j �Ķ�ȡxml�ļ�����
		SAXReader sax = new SAXReader();
		//ͨ����ǰclass��������ļ����ļ�
		InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
		
		try {
			//sax��ȡ���ļ����հ��ĵ�
			Document doc = sax.read(in);
			//��ȡ����ǩ
			Element root = doc.getRootElement();
			//��ȡ�����ӱ�ǩ
			@SuppressWarnings("unchecked")
			List<Element> elements = root.elements();
			for (Element e : elements) {
				//��ȡ��ǩ���class����ֵ
				String className = e.attributeValue("class");
				//ʵ����
				Object obj = Class.forName(className).newInstance();
				beans.add(obj);
			}
			System.out.println(beans);
			
			hm = new HandlerMapping();
			vr = new ViewResolve();
			//���ض�Ӧ��ϵ
			hm.process(beans);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//��ȡ������Դ·��
		String uri = request.getRequestURI();
		//��ȡӦ��·��
		String contextPath = request.getContextPath();
		//�е�Ӧ������ռ�õĳ���
		String path = uri.substring(contextPath.length());
		System.out.println(path);
		
		//ͨ��·����ȡ��Ӧ����,����
		Handler hand = hm.gethandler(path);
		Object obj = hand.getObj();
		Method meth = hand.getMeth();
		
		//��������ֵ
		Object returnVal = null;
		try {
			returnVal = meth.invoke(obj);
			vr.process(returnVal,contextPath,request,response);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
