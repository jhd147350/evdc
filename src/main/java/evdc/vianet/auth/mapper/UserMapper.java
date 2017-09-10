package evdc.vianet.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.auth.entity.User;

/**
 * @author jaden
 *
 * @date	2017年9月10日下午2:15:19
 */
public interface UserMapper {

	@Select("select * from " + User.TABLE_NAME + " where `loginId`=#{u.loginId} and `password`=#{u.password}")
	User findUserByLoginIdAndPassword(@Param("u") User u);

	@Select("select * from " + User.TABLE_NAME + " where `id`=#{id}")
	User findUserById(@Param("id") long id);
	
	@Select("select * from " + User.TABLE_NAME + " where `loginId`=#{u.loginId}")
	List<User> findUsersByLoginId(@Param("u") User u);
	
	@Select("select * from " + User.TABLE_NAME)
	List<User> findAllUsers();
	
	@Select("select * from " + User.TABLE_NAME+" where `teamId`=#{teamId} ")
	List<User> findAllUsersByTeamId(@Param("teamId") long teamId);

	@Insert("insert into " + User.TABLE_NAME + " (`name`,`role`,`teamId`,`phone`,`email`,`password`,`loginId`) values(#{u.name},#{u.role},#{u.teamId},#{u.phone},#{u.email},#{u.password},#{u.loginId})")
	int insertUser(@Param("u") User u);

	@Update("update " + User.TABLE_NAME
			+ " set `name`=#{u.name}, `role`=#{u.role}, `teamId`=#{u.teamId}, `phone`=#{u.phone}, `email`=#{u.email}, `password`=#{u.password} where `id`=#{u.id}")
	void updateUser(@Param("u") User u);

	@Delete("delete from " + User.TABLE_NAME + " where `id`=#{id}")
	int deleteUserById(long id);
}
