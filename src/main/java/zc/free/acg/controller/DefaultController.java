package zc.free.acg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import zc.free.acg.service.IdentityService;

/**
 * @auther ZhengCong
 * @date 2020/3/30 14:32
 * 抓哟控制跳转页面和一些测试跳转页面的controller
 */
@Controller
public class DefaultController {
    @Autowired
    private IdentityService identityService;


    /**
     * 跳转主页
     * @return
     */
    @GetMapping("/")
    public String toIndex() {
        return "index";
    }

    /**
     * 跳转注册页面
     * @return
     */
    @GetMapping("/register")
    public String toRegister() {
        return "register";
    }

    /**
     * 跳转登录页面
     * 跳转自定义登录页面security设置此方法为跳转方法
     * @return
     */
    @GetMapping("/login")
    public String toLogin() {
        return "login";
    }

    /**
     * 跳转个人信息页面
     * @return
     */
    @GetMapping("/myInfo")
    public String toMyInfo() {
        return "myInfo";
    }

    /**
     * 跳转我的粉丝页面
     * @return
     */
    @GetMapping("/myFollowed")
    public String toMyFollowed() {
        return "myfollowed";
    }

    /**
     * 跳转管理展会页面
     * @return
     */
    @GetMapping("/myExhibition")
    public String toMyExhibition() {
        return "myExhibition";
    }

    /**
     * 跳转发布展会页面
     * @return
     */
    @GetMapping("/publish_ex")
    public String toPublish_ex() {
        return "publish_ex";
    }
    /**
     * 跳转发布公告页面
     * @return
     */
    @GetMapping("/publish_ex_detail")
    @ResponseBody
    public String toPublish_ex_detail() {
        return "未完成";
    }
    /**
     * 跳转发布公告页面
     * @return
     */
    @GetMapping("/update_ex")
    @ResponseBody
    public String toUpdate_ex() {
        return "未完成";
    }

    /**
     * 跳转我的关注列表页面
     * @return
     */
    @GetMapping("/myFollower")
    public String toMyFollower() {
        return "myfollower";
    }

    /**
     * 修改密码页面
     * @return
     */
    @GetMapping("/updatePwd")
    public String toUpdatePwd() {
        return "updatePwd";
    }
    /**
     * 更换头像页面
     * @return
     */
    @GetMapping("/changeImg")
    public String toChangeImg() {
        return "updateImg";
    }

    /**
     * 跳转到搜索页面
     * @return
     */
    @PostMapping("/search")
    public String toSearch(Model model, String key) {
        model.addAttribute("key", key);
        return "search_result_exhibition";
    }



    /**
     * 跳转话题页面
     * @return
     */
    @GetMapping("/topic")
    public String toTopic() {
        return "topic";
    }

    /**
     * 跳转身份认证页面
     * @return
     */
    @GetMapping("/identity")
    public String toIdentity() {
        return "identity";
    }




}
