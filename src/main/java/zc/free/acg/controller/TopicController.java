package zc.free.acg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zc.free.acg.service.TopicService;

/**
 * @auther ZhengCong
 * @date 2020/5/8 15:05
 * 话题模块controller
 */
@Controller
@RequestMapping("topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @GetMapping("pub")
    public String gotoPublishTopic() {
        return "topic_publish";
    }
    @PostMapping("pub")
    public String publishTopic() {
        return "topic_publish";
    }


    @GetMapping("search")
    public String gotoTopic() {
        return "topic";
    }



}
