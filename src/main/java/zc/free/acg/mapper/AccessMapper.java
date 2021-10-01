package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.Access;
import zc.free.acg.model.AccessExample;

public interface AccessMapper {
    long countByExample(AccessExample example);

    int deleteByExample(AccessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Access record);

    int insertSelective(Access record);

    List<Access> selectByExample(AccessExample example);

    Access selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Access record, @Param("example") AccessExample example);

    int updateByExample(@Param("record") Access record, @Param("example") AccessExample example);

    int updateByPrimaryKeySelective(Access record);

    int updateByPrimaryKey(Access record);
}