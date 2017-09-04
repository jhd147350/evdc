package evdc.vianet.shift.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import evdc.vianet.auth.entity.User;
import evdc.vianet.shift.entity.Cover;
import evdc.vianet.shift.entity.Staff;

public interface CoverMapper {

	@Insert("insert into " + User.TABLE_NAME + " (username,password) values(#{u.username},#{u.password})")
	int insertCover(Cover cover);

	@Delete("")
	int deleteCoverById(long id);

	@Update("")
	int updateCover(Cover cover);

	@Select("")
	List<Cover> selectCoverByScheduleId(long scheduleId);

}
