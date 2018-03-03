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
 * 总控制器; 
 * 配两个秘书分别处理(路径和处理器,视图和jsp)
 * @author Tryin4Sage
 * @2018年3月3日 下午2:44:57
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Object> beans = new ArrayList<Object>();
	private HandlerMapping hm;
	private ViewResolve vr;
	
	@Override
	public void init() throws ServletException {
		//通过初始化参数,获得配置文件名
		ServletConfig config = getServletConfig();
		String fileName = config.getInitParameter("config");
		System.out.println(fileName);
		
		//dom4j 的读取xml文件对象
		SAXReader sax = new SAXReader();
		//通过当前class获得配置文件流文件
		InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
		
		try {
			//sax读取流文件到空白文档
			Document doc = sax.read(in);
			//获取根标签
			Element root = doc.getRootElement();
			//获取所有子标签
			@SuppressWarnings("unchecked")
			List<Element> elements = root.elements();
			for (Element e : elements) {
				//获取标签里的class属性值
				String className = e.attributeValue("class");
				//实例化
				Object obj = Class.forName(className).newInstance();
				beans.add(obj);
			}
			System.out.println(beans);
			
			hm = new HandlerMapping();
			vr = new ViewResolve();
			//加载对应关系
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
		
		//获取请求资源路径
		String uri = request.getRequestURI();
		//获取应用路径
		String contextPath = request.getContextPath();
		//切掉应用名所占用的长度
		String path = uri.substring(contextPath.length());
		System.out.println(path);
		
		//通过路径获取对应对象,方法
		Handler hand = hm.gethandler(path);
		Object obj = hand.getObj();
		Method meth = hand.getMeth();
		
		//方法返回值
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
