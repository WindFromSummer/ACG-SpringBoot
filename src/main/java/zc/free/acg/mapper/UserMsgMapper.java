package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.UserMsg;
import zc.free.acg.model.UserMsgExample;

public interface UserMsgMapper {
    long countByExample(UserMsgExample example);

    int deleteByExample(UserMsgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserMsg record);

    int insertSelective(UserMsg record);

    List<UserMsg> selectByExampleWithBLOBs(UserMsgExample example);

    List<UserMsg> selectByExample(UserMsgExample example);

    UserMsg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserMsg record, @Param("example") UserMsgExample example);

    int updateByExampleWithBLOBs(@Param("record") UserMsg record, @Param("example") UserMsgExample example);

    int updateByExample(@Param("record") UserMsg record, @Param("example") UserMsgExample example);

    int updateByPrimaryKeySelective(UserMsg record);

    int updateByPrimaryKeyWithBLOBs(UserMsg record);

    int updateByPrimaryKey(UserMsg record);
}