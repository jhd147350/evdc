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

		//登录和 js和css资源直接放行、包括admin也直接跳过
		if (uri.endsWith("login") || uri.endsWith(".js") || uri.endsWith(".css") || uri.contains("admin")) {
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
			if(haveAuth(request, response)) {
				chain.doFilter(request, response);
			}else {
				request.getRequestDispatcher("/static/jsp/PermissionDenied.jsp").include(request, response); 
				//resp.sendRedirect(req.getContextPath() + "/static/jsp/PermissionDenied.html");
			}
		}

	}
	protected abstract boolean haveAuth(ServletRequest request, ServletResponse response);
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
