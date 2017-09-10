package evdc.vianet.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.auth.entity.TeamRole;
import evdc.vianet.auth.entity.UserRole;

/**
 * @author jaden
 *
 * 2017年9月8日上午10:41:22
 */
public interface UserRoleMapper {
	
	@Select("select * from " + UserRole.TABLE_NAME + " where id=#{id}")
	UserRole findUserRoleById(@Param("id") long id);
	@Select("select * from " + UserRole.TABLE_NAME)
	List<UserRole> findAllUserRoles();
	
	@Select("select * from " + UserRole.TABLE_NAME + " where roleTeamId=#{roleTeamId}")
	List<UserRole> findAllUserRolesByTeamId(@Param("roleTeamId") long roleTeamId);
	
	@Insert("insert into " + UserRole.TABLE_NAME + " (`roleName`,`roleTeamId`,`authValue`,`delete`,`describe`) values(#{userRole.roleName},#{userRole.roleTeamId},#{userRole.authValue},#{userRole.delete},#{userRole.describe})")
	int insertUserRole(@Param("userRole") UserRole userRole);
	@Update("update " + UserRole.TABLE_NAME
			+ " set `roleName`=#{userRole.roleName}, `authValue`=#{userRole.authValue}, `delete`=#{userRole.delete}, `describe`=#{userRole.describe} where `id`=#{userRole.id}")
	void updateUserRole(@Param("userRole") UserRole userRole);
	@Delete("delete from " + UserRole.TABLE_NAME + " where `id`=#{id}")
	int deleteUserRoleById(@Param("id") long id);
}
