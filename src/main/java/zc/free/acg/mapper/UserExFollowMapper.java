package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.UserExFollow;
import zc.free.acg.model.UserExFollowExample;

public interface UserExFollowMapper {
    long countByExample(UserExFollowExample example);

    int deleteByExample(UserExFollowExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserExFollow record);

    int insertSelective(UserExFollow record);

    List<UserExFollow> selectByExample(UserExFollowExample example);

    UserExFollow selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserExFollow record, @Param("example") UserExFollowExample example);

    int updateByExample(@Param("record") UserExFollow record, @Param("example") UserExFollowExample example);

    int updateByPrimaryKeySelective(UserExFollow record);

    int updateByPrimaryKey(UserExFollow record);
}