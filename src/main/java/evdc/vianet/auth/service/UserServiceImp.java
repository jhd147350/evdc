package evdc.vianet.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.mapper.UserMapper;

@Service("userService")
public class UserServiceImp implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(User u) {
		return userMapper.findUserByUsernameAndPassword(u);
	}

	@Override
	public int insertUser(User u) {
		return userMapper.insertUser(u);
	}

	@Override
	public int deleteUserById(int id) {
		return userMapper.deleteUserById(id);
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAllUsers();
	}

	@Override
	public User findUserById(int id) {
		// TODO
		return null;
	}

	@Override
	public int updateUserById(User u) {
		userMapper.updateUser(u);
		return 0;
	}

}
