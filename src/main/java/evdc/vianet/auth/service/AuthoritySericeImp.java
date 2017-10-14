package evdc.vianet.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.auth.entity.Authority;
import evdc.vianet.auth.mapper.AuthorityMapper;
import evdc.vianet.auth.service.AuthorityService;

/**
 * @author jaden
 *
 * 2017年9月8日上午10:57:36
 */
@Service("authorityService")
public class AuthoritySericeImp implements AuthorityService {
	
	@Autowired
	private AuthorityMapper authorityMapper;
	@Override
	public List<Authority> findAllAuthoritys() {
		// TODO Auto-generated method stub
		return authorityMapper.findAllAuthoritys();
	}
	@Override
	public List<Authority> findAuthoritysByPath(String path) {
		// TODO Auto-generated method stub
		return authorityMapper.findAuthsByPath(path);
	}
	@Override
	public List<Authority> findAuthoritysByType(String type) {
		// TODO Auto-generated method stub
		return authorityMapper.findAuthoritysByType(type);
	}
	@Override
	public List<Authority> findAllMainAuthoritys() {
		// TODO Auto-generated method stub
		return authorityMapper.findAllMainAuthoritys();
	}
	@Override
	public Authority findAuthById(long id) {
		// TODO Auto-generated method stub
		return authorityMapper.findAuthById(id);
	}

}
