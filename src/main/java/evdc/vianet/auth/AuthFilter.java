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
		String requestURL = req.getRequestURI();

		// 静态资源与登录页面
		if (requestURL.endsWith("login") || requestURL.indexOf("/static/") != -1) {
			System.out.println("无需过滤: " + requestURL);
			chain.doFilter(request, response);
			return;
		}
		// 简单判断缓存中是否有用户
		if (u == null) {// 没有用户
			String queryParam = req.getQueryString();
			String fullUrl = queryParam == null ? requestURL : requestURL + "?" + queryParam;
			req.getSession().setAttribute("redirectURL", fullUrl);
			System.out.println("未登录 即将重定向到登录页面: " + fullUrl);
			// chain.doFilter(request, response);
			resp.sendRedirect(req.getContextPath() + "/user/login");
		} else {// 有用户
			String redirectURL = (String) req.getSession().getAttribute("redirectURL");// 之前的url
			if (redirectURL != null && redirectURL.length() > 0) {
				req.getSession().setAttribute("redirectURL", null);
				System.out.println("登录成功 即将重定向到原页面: " + redirectURL);
				resp.sendRedirect(redirectURL);
				return;
			}

			if (requestURL.endsWith("/evdc/") || requestURL.endsWith("evdc")) {
				System.out.println("已登录  即将重定向到首页" + requestURL);
				resp.sendRedirect(req.getContextPath() + "/user/indexPage");
				return;
			} else {
				// 权限判断
				int auth = haveAuth(u, req, resp);
				if (auth == 0) {
					chain.doFilter(request, response);
				} else if (auth == 2) {
					request.getRequestDispatcher("/user/permissionDenied").include(request, response);
					// resp.sendRedirect(req.getContextPath() +
					// "/static/jsp/PermissionDenied.html");
				} else {
				}
			}
			System.out.println("已登录: " + requestURL);
		}
	}

	protected abstract int haveAuth(User u, HttpServletRequest request, HttpServletResponse response);

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
