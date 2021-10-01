package zc.free.acg.service;

import zc.free.acg.domain.ExhibitionMsg;
import zc.free.acg.domain.ExhibitionMsgBo;
import zc.free.acg.domain.PageResult;

import java.util.List;

public interface ExhibitionMsgService {
    /**
     * 用户评论展会
     * @param exhibitionMsg
     */
    void addExhibitionMsg(ExhibitionMsg exhibitionMsg);

    /**
     * 根据展会id查查询展会评论
     * @param id
     * @return
     */
    List<ExhibitionMsg> queryExhibitionMsgs(Integer id);
    /**
     * 根据展会id查询展会评论h和评论人信息（名称）
     * @param id
     * @return
     */
    List<ExhibitionMsgBo> queryExhibitionMsgsAndMsgOwnerByExhibitionId(Integer id);

    /**
     * 根据展会id分页查询展会评论h和评论人信息（名称）
     * @param id
     * @return
     */
    PageResult<ExhibitionMsgBo> queryExhibitionMsgsAndMsgOwnerByExhibitionIdWithPage(Integer id, Integer page, Integer cows);
}
