package evdc.vianet.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import evdc.vianet.auth.entity.Authority;


/**
 * @author jaden
 *
 * 2017年9月8日上午10:56:07
 */
public interface AuthorityMapper {

	@Select("SELECT * FROM "+Authority.TABLE_NAME+" where path =#{path}")
	List<Authority> findAuthsByPath(@Param("path") String path);
	@Select("select * from " + Authority.TABLE_NAME)
	List<Authority> findAllAuthoritys();
	@Select("select * from " + Authority.TABLE_NAME+" where type =#{type}")
	List<Authority> findAuthoritysByType(String type);
	@Select("select * from " + Authority.TABLE_NAME+" where fatherAuthId='0'")
	List<Authority> findAllMainAuthoritys();
	@Select("SELECT * FROM "+Authority.TABLE_NAME+" where id =#{id}")
	Authority findAuthById(@Param("id") long id);
}
