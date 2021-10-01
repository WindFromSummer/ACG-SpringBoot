package zc.free.acg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zc.free.acg.model.ComicImg;
import zc.free.acg.model.ComicImgExample;

public interface ComicImgMapper {
    long countByExample(ComicImgExample example);

    int deleteByExample(ComicImgExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ComicImg record);

    int insertSelective(ComicImg record);

    List<ComicImg> selectByExample(ComicImgExample example);

    ComicImg selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ComicImg record, @Param("example") ComicImgExample example);

    int updateByExample(@Param("record") ComicImg record, @Param("example") ComicImgExample example);

    int updateByPrimaryKeySelective(ComicImg record);

    int updateByPrimaryKey(ComicImg record);
}