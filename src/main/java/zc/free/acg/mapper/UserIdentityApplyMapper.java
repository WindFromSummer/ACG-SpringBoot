package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.UserIdentityApply;
import zc.free.acg.model.UserIdentityApplyExample;

public interface UserIdentityApplyMapper {
    long countByExample(UserIdentityApplyExample example);

    int deleteByExample(UserIdentityApplyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserIdentityApply record);

    int insertSelective(UserIdentityApply record);

    List<UserIdentityApply> selectByExampleWithBLOBs(UserIdentityApplyExample example);

    List<UserIdentityApply> selectByExample(UserIdentityApplyExample example);

    UserIdentityApply selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserIdentityApply record, @Param("example") UserIdentityApplyExample example);

    int updateByExampleWithBLOBs(@Param("record") UserIdentityApply record, @Param("example") UserIdentityApplyExample example);

    int updateByExample(@Param("record") UserIdentityApply record, @Param("example") UserIdentityApplyExample example);

    int updateByPrimaryKeySelective(UserIdentityApply record);

    int updateByPrimaryKeyWithBLOBs(UserIdentityApply record);

    int updateByPrimaryKey(UserIdentityApply record);
}