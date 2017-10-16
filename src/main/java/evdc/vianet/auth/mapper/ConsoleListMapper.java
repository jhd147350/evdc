package evdc.vianet.auth.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import evdc.vianet.auth.entity.ConsoleList;

public interface ConsoleListMapper {
	@Select("select * from " + ConsoleList.TABLE_NAME + " where father=`0`")
	List<ConsoleList> findMainMeans();
	@Select("select * from " + ConsoleList.TABLE_NAME + " where father=#{id}")
	List<ConsoleList> findMeansByFather(@Param("id") long id);
	@Select("select * from " + ConsoleList.TABLE_NAME + " where id=#{id}")
	ConsoleList findMeanById(@Param("id") long id);
}
