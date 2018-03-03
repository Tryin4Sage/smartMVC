package base.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 负责处理视图和jsp
 * @author Tryin4Sage
 * @2018年3月3日 下午4:28:12
 */
public class ViewResolve {

	public void process(Object returnVal, String contextPath, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = returnVal.toString();
		
		//判断路径是什么
		if (viewName.startsWith("redirect:")) {
			String path2 = contextPath+"/"+viewName.substring("redirect:".length());
			response.sendRedirect(path2);
		} else {
			String path1 = "WEB-INF/"+viewName+".jsp";
			request.getRequestDispatcher(path1).forward(request, response);
		}
		
	}

}
