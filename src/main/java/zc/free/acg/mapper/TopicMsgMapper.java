package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.TopicMsg;
import zc.free.acg.model.TopicMsgExample;

public interface TopicMsgMapper {
    long countByExample(TopicMsgExample example);

    int deleteByExample(TopicMsgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TopicMsg record);

    int insertSelective(TopicMsg record);

    List<TopicMsg> selectByExample(TopicMsgExample example);

    TopicMsg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TopicMsg record, @Param("example") TopicMsgExample example);

    int updateByExample(@Param("record") TopicMsg record, @Param("example") TopicMsgExample example);

    int updateByPrimaryKeySelective(TopicMsg record);

    int updateByPrimaryKey(TopicMsg record);
}