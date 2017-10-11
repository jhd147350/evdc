package evdc.vianet.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import evdc.vianet.auth.entity.Authority;
import evdc.vianet.auth.entity.Team;
import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.mapper.AuthorityMapper;
import evdc.vianet.auth.mapper.TeamMapper;
import evdc.vianet.auth.mapper.UserMapper;
import evdc.vianet.auth.mapper.UserRoleMapper;

@Service("userService")
public class UserServiceImp implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private TeamMapper teamMapper;
	@Autowired
	private AuthorityMapper authorityMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	
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
	//权限判断
	@Override
	public List<Authority> getUserAuthsById(long userId) {
		// TODO Auto-generated method stub
	
		List<Authority> userAuths = new ArrayList<Authority>();
		long userAuthValue = userRoleMapper.findUserRoleById(userMapper.findUserById(userId).getRole()).getAuthValue();
		List<Authority> allAuth = authorityMapper.findAllMainAuthoritys();
		
		for (Authority authority : allAuth) {
			if(userAuthValue%authority.getAuthValue()==0){
				userAuths.add(authority);
			}
		}
		return null;
	}
}
