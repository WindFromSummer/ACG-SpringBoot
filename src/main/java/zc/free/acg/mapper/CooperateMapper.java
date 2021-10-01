package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.Cooperate;
import zc.free.acg.model.CooperateExample;

public interface CooperateMapper {
    long countByExample(CooperateExample example);

    int deleteByExample(CooperateExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cooperate record);

    int insertSelective(Cooperate record);

    List<Cooperate> selectByExample(CooperateExample example);

    Cooperate selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cooperate record, @Param("example") CooperateExample example);

    int updateByExample(@Param("record") Cooperate record, @Param("example") CooperateExample example);

    int updateByPrimaryKeySelective(Cooperate record);

    int updateByPrimaryKey(Cooperate record);
}