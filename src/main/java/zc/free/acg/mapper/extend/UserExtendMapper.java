package zc.free.acg.mapper.extend;


import java.util.Map;

public interface UserExtendMapper {

    /**
     * 查询用户关注的展会
     * @param param
     * @return
     */
    Integer selectUserIsForkExhibitionCount(Map param);

}