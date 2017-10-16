package evdc.vianet.auth.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import evdc.vianet.auth.entity.ClientConfig;

/**
 * @author jaden
 *
 * @date	2017年10月14日上午10:26:48
 */
public interface ClientConfigMapper {
	@Select("select * from " + ClientConfig.TABLE_NAME + " where teamRoleId=#{teamRoleId}")
	ClientConfig findMeansByTeamRoleId(@Param("teamRoleId") long teamRoleId);
}
