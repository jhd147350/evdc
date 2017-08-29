package evdc.vianet.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.auth.entity.Team;
import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.mapper.TeamMapper;
import evdc.vianet.auth.mapper.UserMapper;

@Service("userService")
public class UserServiceImp implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private TeamMapper teamMapper;

	@Override
	public User login(String loginId, String password) {
		User u = new User();
		u.setLoginId(loginId);
		u.setPassword(password);
		return userMapper.findUserByLoginIdAndPassword(u);
	}

	@Override
	public int insertUser(User u) {
		return userMapper.insertUser(u);
	}

	@Override
	public int deleteUserById(long id) {
		return userMapper.deleteUserById(id);
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAllUsers();
	}

	@Override
	public User findUserById(long id) {
		// TODO
		return null;
	}

	@Override
	public int updateUserById(User u) {
		userMapper.updateUser(u);
		return 0;
	}

	@Override
	public boolean isClient(long teamId) {
		Team t = teamMapper.findTeamById(teamId);
		return t.isClient();
	}

}
