package evdc.vianet.auth.service;

import java.util.List;

import evdc.vianet.auth.entity.Authority;
import evdc.vianet.auth.entity.User;

public interface UserService {

	User login(String loginId, String password);
	
	User findUserByLoginId(String loginId);
	
	int insertUser(User u);

	int deleteUserById(long id);

	List<User> findAllUsers();

	User findUserById(long id);

	List<User> findAllUsersByTeamId(long teamId);

	void updateUserById(User u);
	
	List<Authority> getUserAuthsById(long userId); 
}
