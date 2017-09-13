package evdc.vianet.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.auth.entity.Team;
import evdc.vianet.auth.entity.TeamRole;

/**
 * @author jaden
 *
 * 2017年9月9日下午6:19:58
 */
public interface TeamMapper {
	// @Select("select * from " + Team.TABLE_NAME + " where email=#{u.email} and
	// password=#{u.password}")
	// Team findUserByEmailAndPassword(@Param("u") User u);
	@Select("select * from " + Team.TABLE_NAME + " where id=#{id}")
	Team findTeamById(@Param("id") long id);
	
	@Select("select * from " + Team.TABLE_NAME)
	List<Team> findAllTeams();

	@Insert("insert into " + Team.TABLE_NAME
			+ " (`name`,`role`,`companyName`,`code`,`delete`,`description`) values(#{t.name},#{t.role},#{t.companyName},#{t.code},#{t.delete},#{t.description})")
	int insertTeam(@Param("t") Team t);

	@Update("update " + Team.TABLE_NAME
			+ " set `name`=#{t.name}, `role`=#{t.role}, `companyName`=#{t.companyName}, `code`=#{t.code}, `delete`=#{t.delete}, `description`=#{t.description} where `id`=#{t.id}")
	void updateTeam(@Param("t") Team t);

	@Delete("delete from " + Team.TABLE_NAME + " where `id`=#{id}")
	int deleteTeamById(@Param("id") long id);
}
