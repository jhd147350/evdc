package evdc.vianet.auth.service;
import java.util.List;

import evdc.vianet.auth.entity.Authority;

/**
 * @author jaden
 *
 * 2017年9月8日上午10:56:16
 */
public interface AuthorityService {
	List<Authority> findAllAuthoritys();
	List<Authority> findAuthoritysByPath(String path);
	List<Authority> findAuthoritysByType(String type);
	List<Authority> findAllMainAuthoritys();
}
