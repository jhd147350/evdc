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
		List<User> users = userMapper.findUsersByLoginId(u);
		if(users.size()>=1){
			return 2;
		}else{
			return userMapper.insertUser(u);
		}
		
	}

	@Override
	public int deleteUserById(long id) {
		return userMapper.deleteUserById(id);
	}

	@Override
	public List<User> findAllUsers() {
		return userMapper.findAllUsers();
	}

	@Override
	public User findUserById(long id) {
		// TODO
		return userMapper.findUserById(id);
	}

	@Override
	public void updateUserById(User u) {
		
		userMapper.updateUser(u);
	}

	@Override
	public List<User> findAllUsersByTeamId(long teamId) {
		// TODO Auto-generated method stub
		return userMapper.findAllUsersByTeamId(teamId);
	}



}
