package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.UserFollowed;
import zc.free.acg.model.UserFollowedExample;

public interface UserFollowedMapper {
    long countByExample(UserFollowedExample example);

    int deleteByExample(UserFollowedExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserFollowed record);

    int insertSelective(UserFollowed record);

    List<UserFollowed> selectByExample(UserFollowedExample example);

    UserFollowed selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserFollowed record, @Param("example") UserFollowedExample example);

    int updateByExample(@Param("record") UserFollowed record, @Param("example") UserFollowedExample example);

    int updateByPrimaryKeySelective(UserFollowed record);

    int updateByPrimaryKey(UserFollowed record);
}