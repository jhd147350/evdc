package evdc.vianet.auth;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author jaden
 *
 * 2017年9月4日下午11:41:07
 */
public class EVDCAuthFilter extends AuthFilter {

	@Override
	protected boolean haveAuth(ServletRequest request, ServletResponse response) {
		// TODO Auto-generated method stub
		
		return false;
	}

}
