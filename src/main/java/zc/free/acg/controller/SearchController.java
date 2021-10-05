package zc.free.acg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zc.free.acg.model.User;

/**
 * @auther ZhengCong
 * @date 2020/6/16 21:47
 * 查询接口
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    @PostMapping("/default")
    public String search(String key, Integer page, String topic, RedirectAttributes attr) {
        attr.addAttribute("key", key);
        User user = new User();
        return "redirect:/search/a";
    }

    @GetMapping("/a")
    public String a(String key, RedirectAttributes attr) {
        System.out.println(attr.getAttribute("key").toString());
        return "index";
    }
}
