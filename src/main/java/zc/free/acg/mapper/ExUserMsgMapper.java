package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.ExUserMsg;
import zc.free.acg.model.ExUserMsgExample;

public interface ExUserMsgMapper {
    long countByExample(ExUserMsgExample example);

    int deleteByExample(ExUserMsgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExUserMsg record);

    int insertSelective(ExUserMsg record);

    List<ExUserMsg> selectByExample(ExUserMsgExample example);

    ExUserMsg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExUserMsg record, @Param("example") ExUserMsgExample example);

    int updateByExample(@Param("record") ExUserMsg record, @Param("example") ExUserMsgExample example);

    int updateByPrimaryKeySelective(ExUserMsg record);

    int updateByPrimaryKey(ExUserMsg record);
}