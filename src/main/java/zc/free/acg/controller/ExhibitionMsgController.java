package zc.free.acg.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zc.free.acg.domain.ExhibitionBo1;
import zc.free.acg.domain.ExhibitionMsg;
import zc.free.acg.domain.ExhibitionMsgBo;
import zc.free.acg.domain.PageResult;
import zc.free.acg.service.ExhibitionMsgService;
import zc.free.acg.service.ExhibitionService;

/**
 * @auther ZhengCong
 * @date 2020/4/7 21:40
 * 用户动态
 */
@Api(description = "漫展评论相关接口")
@Controller
@RequestMapping("exhibitionMsg")
public class ExhibitionMsgController {
    @Autowired
    private ExhibitionService exhibitionService;
    @Autowired
    private ExhibitionMsgService exhibitionMsgService;
    /**
     * 用户发布动态
     * @return
     */
    /*@ApiOperation(value = "用户对漫展进行评论", notes="")
    @PostMapping("add")
    public ResponseEntity<Void> addMsg(ExhibitionMsg exhibitionMsg) {
        this.exhibitionMsgService.addExhibitionMsg(exhibitionMsg);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }*/

    /**
     * 用户发布动态
     * @return
     */
    @ApiOperation(value = "用户对漫展进行评论", notes="")
    @PostMapping("add")
    public String addMsg(ExhibitionMsg exhibitionMsg) {
        this.exhibitionMsgService.addExhibitionMsg(exhibitionMsg);
        return "redirect:/exhibitionMsg/" + exhibitionMsg.getExId();
    }

    /**
     * 查询展会评论
     * @return
     */
//    @ApiOperation(value = "查询展会评论", notes="")
//    @GetMapping("{id}")
//    public ResponseEntity<List<ExhibitionMsg>> queryExhibitionMsgs(@PathVariable("id")Integer id) {
//        List<ExhibitionMsg> msgs = this.exhibitionMsgService.queryExhibitionMsgs(id);
//        if(CollectionUtils.isEmpty(msgs)) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(msgs);
//    }
    /**
     * 查询展会评论
     * @return
     */
    @ApiOperation(value = "根据展会id分页查询展会评论", notes="")
    @GetMapping("{id}")
    public String queryExhibitionMsgs(
            @PathVariable("id")Integer id,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "cows",defaultValue = "5") Integer cows,
            Model model) {
        //查询展会信息
        ExhibitionBo1 exhibitionBo1 = this.exhibitionService.queryExhibitionAndOwnerNameAndForkNumById(id);
        model.addAttribute("exhibition", exhibitionBo1);
        //查询评论信息
        PageResult<ExhibitionMsgBo> msgs = this.exhibitionMsgService.queryExhibitionMsgsAndMsgOwnerByExhibitionIdWithPage(id,page,cows);
        model.addAttribute("msg", msgs);
        model.addAttribute("curPage", page);
        return "ex_detail";
    }



}
