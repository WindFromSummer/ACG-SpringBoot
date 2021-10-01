package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.RoleAccess;
import zc.free.acg.model.RoleAccessExample;

public interface RoleAccessMapper {
    long countByExample(RoleAccessExample example);

    int deleteByExample(RoleAccessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleAccess record);

    int insertSelective(RoleAccess record);

    List<RoleAccess> selectByExample(RoleAccessExample example);

    RoleAccess selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleAccess record, @Param("example") RoleAccessExample example);

    int updateByExample(@Param("record") RoleAccess record, @Param("example") RoleAccessExample example);

    int updateByPrimaryKeySelective(RoleAccess record);

    int updateByPrimaryKey(RoleAccess record);
}