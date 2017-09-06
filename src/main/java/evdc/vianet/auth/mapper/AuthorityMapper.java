package evdc.vianet.auth.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import evdc.vianet.auth.entity.Authority;


public interface AuthorityMapper {
	@Select("SELECT * FROM "+Authority.TABLE_NAME+" where path =#{auth.path}")
	Authority findAuthByPath(@Param("auth") Authority auth);
}
