package zc.free.acg.service;

import org.springframework.stereotype.Service;
import zc.free.acg.domain.Cooperate;
import zc.free.acg.domain.CooperateBo;
import zc.free.acg.domain.PageResult;

import java.util.List;

public interface CooperateService {
    /**
     * 发布合作意向
     * @param cooperate
     */
    void publishCooperate(Cooperate cooperate);

    /**
     * 根据关键词分页查询合作信息
     * @param key
     * @param page
     * @param cows
     * @return
     */
    PageResult<CooperateBo> queryCooperateByPage(String key, Integer page, Integer cows);


}
