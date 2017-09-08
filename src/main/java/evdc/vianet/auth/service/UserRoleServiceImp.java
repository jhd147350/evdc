package evdc.vianet.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import evdc.vianet.auth.entity.UserRole;
import evdc.vianet.auth.mapper.UserRoleMapper;


/**
 * @author jaden
 *
 * 2017年9月8日上午11:36:57
 */
@Service("userRoleService")
public class UserRoleServiceImp implements UserRoleService {
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Override
	public List<UserRole> findAllUserRoles() {
		// TODO Auto-generated method stub
		return userRoleMapper.findAllUserRoles();
	}

	@Override
	public UserRole findUserRoleById(long id) {
		// TODO Auto-generated method stub
		return userRoleMapper.findUserRoleById(id);
	}

	@Override
	public int insertUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		return userRoleMapper.insertUserRole(userRole);
	}

	@Override
	public void updateUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		userRoleMapper.updateUserRole(userRole);
	}

	@Override
	public int deleteUserRole(long id) {
		// TODO Auto-generated method stub
		UserRole userRole = userRoleMapper.findUserRoleById(id);
		if(userRole.getDelete()==1) {
			return 1;
		}else {
			return userRoleMapper.deleteUserRoleById(id);
		}
	}

}
