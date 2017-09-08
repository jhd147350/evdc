package evdc.vianet.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.auth.entity.Team;

public interface TeamMapper {
	// @Select("select * from " + Team.TABLE_NAME + " where email=#{u.email} and
	// password=#{u.password}")
	// Team findUserByEmailAndPassword(@Param("u") User u);

	@Select("select * from " + Team.TABLE_NAME)
	List<Team> findAllTeams();

	@Insert("insert into " + Team.TABLE_NAME
			+ " (name,isClient,companyName,code,description) values(#{name},#{role},#{companyName},#{code},#{description})")
	int insertTeam(Team t);

	@Update("update " + Team.TABLE_NAME
			+ " set name=#{name}, isClient=#{isClient}, companyName=#{companyName}, code=#{code}, description=#{description} where id=#{id}")
	void updateTeam(Team t);

	@Delete("delete from" + Team.TABLE_NAME + " where id=#{id}")
	int deleteTeamById(long id);

	@Select("select * from " + Team.TABLE_NAME + " where id=#{id}")
	Team findTeamById(long id);

}
