package evdc.vianet.auth.service;

import java.util.List;

import evdc.vianet.auth.entity.User;

public interface UserService {
	
	User login(User u);

	int insertUser(User u);

	int deleteUserById(int id);

	List<User> findAll();

	User findUserById(int id);

	int updateUserById(User u);
}
