package zc.free.acg.service;

import zc.free.acg.domain.UserMsg;

import java.util.List;

public interface UserMsgService {
    /**
     * 发布动态
     * @param userMsg
     */
    void addMsg(UserMsg userMsg);

    /**
     * 查询用户动态
     * @param id
     * @return
     */
    List<UserMsg> queryMsgs(Integer id);
}
