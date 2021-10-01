package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.UserFollower;
import zc.free.acg.model.UserFollowerExample;

public interface UserFollowerMapper {
    long countByExample(UserFollowerExample example);

    int deleteByExample(UserFollowerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserFollower record);

    int insertSelective(UserFollower record);

    List<UserFollower> selectByExample(UserFollowerExample example);

    UserFollower selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserFollower record, @Param("example") UserFollowerExample example);

    int updateByExample(@Param("record") UserFollower record, @Param("example") UserFollowerExample example);

    int updateByPrimaryKeySelective(UserFollower record);

    int updateByPrimaryKey(UserFollower record);
}