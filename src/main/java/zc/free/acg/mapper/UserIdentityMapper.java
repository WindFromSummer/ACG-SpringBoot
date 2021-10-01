package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.UserIdentity;
import zc.free.acg.model.UserIdentityExample;

public interface UserIdentityMapper {
    long countByExample(UserIdentityExample example);

    int deleteByExample(UserIdentityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserIdentity record);

    int insertSelective(UserIdentity record);

    List<UserIdentity> selectByExample(UserIdentityExample example);

    UserIdentity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserIdentity record, @Param("example") UserIdentityExample example);

    int updateByExample(@Param("record") UserIdentity record, @Param("example") UserIdentityExample example);

    int updateByPrimaryKeySelective(UserIdentity record);

    int updateByPrimaryKey(UserIdentity record);
}