//package zc.free.acg.controller;
//
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import zc.free.acg.domain.Cooperate;
//import zc.free.acg.domain.CooperateBo;
//import zc.free.acg.domain.PageResult;
//import zc.free.acg.service.CooperateService;
//
//import java.util.List;
//
///**
// * @auther ZhengCong
// * @date 2020/5/8 15:05
// * 合作模块controller
// */
//@Controller
//@RequestMapping("cooperate")
//public class CooperateController {
//
//    @Autowired
//    private CooperateService cooperateService;
//
//    /**
//     * 跳转合作页面
//     * @return
//     */
//    @GetMapping("/")
//    public String toCooperate() {
//        return "cooperate";
//    }
//
//    /**
//     * 跳转发布合作页面
//     * @return
//     */
//    @GetMapping("/pub")
//    public String toCooperatePub() {
//        return "cooperate_pub";
//    }
//
//    /**
//     * 发布展会
//     * @param cooperate
//     * @return
//     */
//    @PostMapping("/pub")
//    public String publishCooperate(Cooperate cooperate) {
//        this.cooperateService.publishCooperate(cooperate);
//        return "cooperate_pub";
//    }
//    @PostMapping("/search")
//    public String searchByPage(
//            @RequestParam("key") String key,
//            @RequestParam(value = "page", defaultValue = "1") Integer page,
//            @RequestParam(value = "cows", defaultValue = "5") Integer cows,
//            Model model
//    ) {
//        PageResult<CooperateBo> result = this.cooperateService.queryCooperateByPage(key, page, cows);
//        model.addAttribute("data", result);
//        model.addAttribute("key", key);
//        model.addAttribute("curPage", page);
//        return "cooperate";
//    }
//
//}
