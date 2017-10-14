package evdc.vianet.auth;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import evdc.vianet.auth.entity.Authority;
import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.entity.UserRole;
import evdc.vianet.auth.service.AuthorityService;
import evdc.vianet.auth.service.UserRoleService;
import evdc.vianet.auth.service.UserService;

/**
 * @author jaden
 *
 * 2017年9月4日下午11:41:07
 */
@Component
public class EvdcAuthFilter extends AuthFilter {
	@Autowired
	@Qualifier("authorityService")
	private AuthorityService authorityService;
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	@Override
	protected int haveAuth(User u, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		String path = request.getRequestURL().toString();
		System.out.println(path);
		//权限判断
		UserRole userRole = userRoleService.findUserRoleById(u.getRole());
		List<Authority> authoritys = authorityService.findAuthoritysByPath(path.split("evdc")[1]);
		if(authoritys.size()>=1){
			if(userRole.getAuthValue()==1||(userRole.getAuthValue()&authoritys.get(0).getAuthValue())>0){
				return 0;
			}else{
				return 2;
			}
		}else{
			return 0;
		}
		
	}
	
	

}
