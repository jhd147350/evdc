package evdc.vianet.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.auth.entity.User;

public interface UserMapper {

	@Select("select * from " + User.TABLE_NAME + " where email=#{u.email} and password=#{u.password}")
	User findUserByEmailAndPassword(@Param("u") User u);

	@Select("select * from " + User.TABLE_NAME)
	List<User> findAllUsers();

	@Insert("insert into " + User.TABLE_NAME + " (username,password) values(#{u.username},#{u.password})")
	int insertUser(User u);

	@Update("update " + User.TABLE_NAME
			+ " set name=#{name}, role=#{role}, teamId=#{teamId}, phone=#{phone}, email=#{email}, password=#{password} where id=#{id}")
	void updateUser(User u);

	@Delete("delete from" + User.TABLE_NAME + " where id=#{id}")
	int deleteUserById(int id);
}
