package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.Exhibition;
import zc.free.acg.model.ExhibitionExample;

public interface ExhibitionMapper {
    long countByExample(ExhibitionExample example);

    int deleteByExample(ExhibitionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Exhibition record);

    int insertSelective(Exhibition record);

    List<Exhibition> selectByExample(ExhibitionExample example);

    Exhibition selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Exhibition record, @Param("example") ExhibitionExample example);

    int updateByExample(@Param("record") Exhibition record, @Param("example") ExhibitionExample example);

    int updateByPrimaryKeySelective(Exhibition record);

    int updateByPrimaryKey(Exhibition record);
}