package zc.free.acg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.free.acg.mapper.TopicMsgMapper;
import zc.free.acg.service.TopicService;

/**
 * @auther ZhengCong
 * @date 2020/5/9 21:09
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicMsgMapper topicMsgMapper;
}
