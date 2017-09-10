package evdc.vianet.auth.service;

import java.util.List;

import evdc.vianet.auth.entity.User;

public interface UserService {

	User login(String loginId, String password);

	int insertUser(User u);

	int deleteUserById(long id);

	List<User> findAll();

	User findUserById(long id);

	int updateUserById(User u);

}
