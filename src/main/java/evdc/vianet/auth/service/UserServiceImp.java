package evdc.vianet.auth.service;

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

		return userMapper.findUserByEmailAndPassword(u);

	}

}
