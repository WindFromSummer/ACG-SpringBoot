package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.ExhibitionDetails;
import zc.free.acg.model.ExhibitionDetailsExample;

public interface ExhibitionDetailsMapper {
    long countByExample(ExhibitionDetailsExample example);

    int deleteByExample(ExhibitionDetailsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExhibitionDetails record);

    int insertSelective(ExhibitionDetails record);

    List<ExhibitionDetails> selectByExampleWithBLOBs(ExhibitionDetailsExample example);

    List<ExhibitionDetails> selectByExample(ExhibitionDetailsExample example);

    ExhibitionDetails selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExhibitionDetails record, @Param("example") ExhibitionDetailsExample example);

    int updateByExampleWithBLOBs(@Param("record") ExhibitionDetails record, @Param("example") ExhibitionDetailsExample example);

    int updateByExample(@Param("record") ExhibitionDetails record, @Param("example") ExhibitionDetailsExample example);

    int updateByPrimaryKeySelective(ExhibitionDetails record);

    int updateByPrimaryKeyWithBLOBs(ExhibitionDetails record);

    int updateByPrimaryKey(ExhibitionDetails record);
}