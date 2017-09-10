package evdc.vianet.auth.service;

import java.util.List;

import evdc.vianet.auth.entity.UserRole;

/**
 * @author jaden
 *
 * 2017年9月8日上午11:32:37
 */
public interface UserRoleService {
	public List<UserRole> findAllUserRoles();
	public UserRole findUserRoleById(long id);
	public int insertUserRole(UserRole userRole);
	public void updateUserRole(UserRole userRole);
	public int deleteUserRole(long id);
}
