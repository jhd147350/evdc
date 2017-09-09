package evdc.vianet.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.auth.entity.TeamRole;
import evdc.vianet.auth.entity.User;

/**
 * @author jaden
 *
 * 2017年9月8日上午10:41:10
 */
public interface TeamRoleMapper {
	
	@Select("select * from " + TeamRole.TABLE_NAME + " where id=#{id}")
	TeamRole findTeamRoleById(@Param("id") long id);
	
	@Select("select * from " + TeamRole.TABLE_NAME)
	List<TeamRole> findAllTeamRoles();
	
	@Insert("insert into " + TeamRole.TABLE_NAME + " (`roleName`,`authValue`,`delete`,`describe`) values(#{teamRole.roleName},#{teamRole.authValue},#{teamRole.delete},#{teamRole.describe})")
	int insertTeamRole(@Param("teamRole") TeamRole teamRole);
	
	@Update("update " + TeamRole.TABLE_NAME
			+ " set `roleName`=#{teamRole.roleName}, `authValue`=#{teamRole.authValue}, `delete`=#{teamRole.delete}, `describe`=#{teamRole.describe} where `id`=#{teamRole.id}")
	void updateTeamRole(@Param("teamRole") TeamRole teamRole);
	
	@Delete("delete from " + TeamRole.TABLE_NAME + " where id=#{id}")
	int deleteTeamRoleById(@Param("id") long id);
}
