package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.Identity;
import zc.free.acg.model.IdentityExample;

public interface IdentityMapper {
    long countByExample(IdentityExample example);

    int deleteByExample(IdentityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Identity record);

    int insertSelective(Identity record);

    List<Identity> selectByExample(IdentityExample example);

    Identity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Identity record, @Param("example") IdentityExample example);

    int updateByExample(@Param("record") Identity record, @Param("example") IdentityExample example);

    int updateByPrimaryKeySelective(Identity record);

    int updateByPrimaryKey(Identity record);
}