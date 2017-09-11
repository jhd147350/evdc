package evdc.vianet.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import evdc.vianet.auth.entity.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class AuthFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		String uri = req.getRequestURI();

		//静态资源与登录页面
		if (uri.endsWith("login") || uri.indexOf("evdc/static/")!=-1 ) {
			System.out.println("skip uri: " + uri);
			chain.doFilter(request, response);
			return;
		}
		// 简单判断缓存中是否有用户
		if (u == null) {// 没有用户

			System.out.println("no user has login: " + uri);
			// chain.doFilter(request, response);
			resp.sendRedirect(req.getContextPath() + "/user/login");
		} else {// 有用户
			
			System.out.println("use has login: " + uri);
			String requestURL = req.getRequestURL().toString();
			System.out.println("当前用户的请求路径"+requestURL);
			if(requestURL.endsWith("/evdc/")||requestURL.endsWith("evdc")){
				resp.sendRedirect(req.getContextPath() + "/user/indexPage");
			}else{
				//权限判断
				int auth = haveAuth(u, req, resp);
				if(auth==0) {
					chain.doFilter(request, response);
				}else if(auth==2) {
					request.getRequestDispatcher("/user/permissionDenied").include(request, response); 
					//resp.sendRedirect(req.getContextPath() + "/static/jsp/PermissionDenied.html");
				}else{
					
				}
			}
			
		}

	}
	
	protected abstract int haveAuth(User u, HttpServletRequest request, HttpServletResponse response);
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
