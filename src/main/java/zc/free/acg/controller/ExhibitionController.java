//package zc.free.acg.controller;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import zc.free.acg.Annotation.LogPrint;
//import zc.free.acg.domain.*;
//import zc.free.acg.service.ExhibitionService;
//import zc.free.acg.service.IdentityService;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
///**
// * @auther ZhengCong
// * @date 2020/4/7 17:16
// * 漫展模块
// */
//@Api(description = "漫展相关接口")
//@Controller
//@RequestMapping("exhibition")
//public class ExhibitionController {
//    @Autowired
//    private ExhibitionService exhibitionService;
//
//    /**
//     * 添加展会信息
//     * @param exhibition
//     * @return
//     */
//    @ApiOperation(value = "添加展会信息", notes="private Integer id;\n" +
//            "    private String name;\n" +
//            "    private String img;\n" +
//            "    private Date startAt;\n" +
//            "    private Date endAt;\n" +
//            "    private int type;\n" +
//            "    private String address;\n" +
//            "    private String tips;\n" +
//            "    private Double price;")
//    @PostMapping("add")
//    public String saveExhibition(
//            Exhibition exhibition,
//            @RequestParam("start_at") String start_at,
//            @RequestParam("end_at")String end_at,
//            @RequestParam("file") MultipartFile file,
//            Model model
//    ) throws ParseException {
//        start_at = start_at.replace('T', ' ');
//        end_at = end_at.replace('T', ' ');
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm");
//        Date startAt = sdf.parse(start_at);
//        Date endAt = sdf.parse(end_at);
//        exhibition.setStartAt(startAt);
//        exhibition.setEndAt(endAt);
//        this.exhibitionService.saveExhibition(exhibition,file);
//        model.addAttribute("result", "发布成功");
//        return "publish_ex";
//    }
//
//    /**
//     * 根据查询条件进行分页查询
//     * @param key 名称 模糊匹配
//     * @param city 城市
//     * @param page 页码
//     * @param cows 行数
//     * @param sortBy 排序条件
//     * @param desc 升降序
//     * @return 展会分页结果集
//     */
//    @ApiOperation(value = "根据条件查询展会", notes="")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "key", value = "展会名称",paramType = "query",required = false, dataType = "String"),
//            @ApiImplicitParam(name = "city", value = "所在城市", paramType = "query", required = false, dataType = "String"),
//            @ApiImplicitParam(name = "page", value = "当前页数", paramType = "query", required = false, dataType = "Integer"),
//            @ApiImplicitParam(name = "cows", value = "当前页数", paramType = "query", required = false, dataType = "Integer"),
//            @ApiImplicitParam(name = "sortBy", value = "当前页数", paramType = "query", required = false, dataType = "String"),
//            @ApiImplicitParam(name = "desc", value = "当前页数", paramType = "query", required = false, dataType = "Boolean")
//    })
//    @PostMapping("/page")
//    @LogPrint
//    public String queryExhibitions(
//            @RequestParam(value = "key",required = false) String key,
//            @RequestParam(value = "city",required = false) String city,
//            @RequestParam(value = "page",defaultValue = "1") Integer page,
//            @RequestParam(value = "cows",defaultValue = "5") Integer cows,
//            @RequestParam(value = "sortBy",required = false) String sortBy,
//            @RequestParam(value = "desc",required = false) Boolean desc,
//            Model model
//    ) {
//        PageResult<ExhibitionBo1> result =this.exhibitionService.queryExhibitionsAndForkNumByPage(key,city,page,cows,sortBy,desc);
//
//        model.addAttribute("curPage", page);
//        model.addAttribute("key", key);
//        model.addAttribute("data", result);
//        return "search_result_exhibition";
//    }
//
////    @ApiOperation(value = "根据ID查询展会详细信息主要是漫展公告 参展人员", notes="")
////    @GetMapping("/details/{id}")
////    public ResponseEntity<ExhibitionDetails> queryExhibitions(@PathVariable("id") Integer id) {
////        ExhibitionDetails details = this.exhibitionService.queryExhibitionDetailsById(id);
////        if (details == null) {
////            return ResponseEntity.notFound().build();
////        }
////        return ResponseEntity.ok(details);
////    }
//
//    @ApiOperation(value = "根据展会ID查询展会公告简介", notes="")
//    @GetMapping("/details/{id}")
//    public String queryExhibitions(@PathVariable("id") Integer id, Model model) {
//        // 查询展会公告
//        List<ExhibitionDetails> details = this.exhibitionService.queryExhibitionDetailsByExId(id);
//        model.addAttribute("details", details);
//        //查询展会信息
//        ExhibitionBo1 exhibitionBo1 = this.exhibitionService.queryExhibitionAndOwnerNameAndForkNumById(id);
//        model.addAttribute("exhibition", exhibitionBo1);
//        return "ex_detail";
//    }
//
//
//    @ApiOperation(value = "根据展会ID查询展会信息并返回到更改信息页面", notes="")
//    @GetMapping("/update/{exId}")
//    public String queryExhibitionById(@PathVariable("exId") Integer id, Model model) {
//        Exhibition exhibition =this.exhibitionService.queryExhibitionById(id);
//        model.addAttribute("exhibition", exhibition);
//        return "myExhibition_updateExhibition";
//    }
//
//    @ApiOperation(value = "根据展会ID修改展会信息", notes="")
//    @PostMapping("/updateExhibition")
//    public String queryNotes(
//            Exhibition exhibition,
//            @RequestParam("start_at") String start_at,
//            @RequestParam("end_at")String end_at,
//            @RequestParam("file") MultipartFile file,
//            Model model
//    ) throws ParseException {
//        Date startAt = null;
//        Date endAt = null;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm");
//        if (null != start_at && !("".equals(start_at)) ) {
//            start_at = start_at.replace('T', ' ');
//            startAt = sdf.parse(start_at);
//        }
//        if (null != end_at && !("".equals(end_at)) ) {
//            end_at = end_at.replace('T', ' ');
//            endAt = sdf.parse(end_at);
//        }
//        exhibition.setStartAt(startAt);
//        exhibition.setEndAt(endAt);
//        //将空字符串的属性置为null
//        if (exhibition.getName().equals("")) {
//            exhibition.setName(null);
//        }
//        if (exhibition.getAddress().equals("")) {
//            exhibition.setAddress(null);
//        }
//        if (exhibition.getTips().equals("")) {
//            exhibition.setTips(null);
//        }
//        this.exhibitionService.updateExhibition(exhibition,file);
//        model.addAttribute("result", "修改成功");
//        return "myExhibition_updateExhibition";
//    }
//
//    @ApiOperation(value = "根据展会公告ID查询展会公告信息", notes="")
//    @GetMapping("/notes/{id}")
//    public String queryNotes(@PathVariable("id") Integer id, Model model) {
//        ExhibitionDetails detail = this.exhibitionService.queryExhibitionDetailsById(id);
//        model.addAttribute("detail", detail);
//        return "ex_detail_note";
//    }
//
//
//    @ApiOperation(value = "根据用户id查询该用户发布的展会", notes="")
//    @GetMapping("{ownId}")
//    public String queryExhibitionByOwner(@PathVariable("ownId") Integer ownerId, Model model) {
//        List<Exhibition> exhibitions = this.exhibitionService.queryExhibitionByOwner(ownerId);
//        model.addAttribute("exhibitions",exhibitions);
//        return "myExhibition";
//    }
//
//    /**
//     * 根据用户id分页查询该用户发布的展会
//     * @param page 页码
//     * @param cows 行数
//     * @param sortBy 排序条件
//     * @param desc 升降序
//     * @return 展会分页结果集
//     */
//    @ApiOperation(value = "分页查询指定id用户发布的展会", notes="")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "用户id", paramType = "query", required = true, dataType = "Integer"),
//            @ApiImplicitParam(name = "page", value = "当前页数", paramType = "query", required = false, dataType = "Integer"),
//            @ApiImplicitParam(name = "cows", value = "每页条数", paramType = "query", required = false, dataType = "Integer"),
//            @ApiImplicitParam(name = "sortBy", value = "排序条件", paramType = "query", required = false, dataType = "String"),
//            @ApiImplicitParam(name = "desc", value = "升降顺序", paramType = "query", required = false, dataType = "Boolean")
//    })
//    @GetMapping("/my/{id}")
//    public String queryExhibitionsByUserIdWithPage(
//            @PathVariable(value = "id") Integer id,
//            @RequestParam(value = "page",defaultValue = "1") Integer page,
//            @RequestParam(value = "cows",defaultValue = "5") Integer cows,
//            @RequestParam(value = "sortBy",required = false) String sortBy,
//            @RequestParam(value = "desc",required = false) Boolean desc,
//            Model model
//    ) {
//        PageResult<Exhibition> result = this.exhibitionService.queryExhibitionsByUserIdWithPage(id, page, cows, sortBy,desc);
//        model.addAttribute("curPage", page);
//        model.addAttribute("data",result);
//        return "myExhibition";
//    }
//
//    @ApiOperation(value = "跳转公告管理页面并根据展会id查询展会公告", notes="")
//    @GetMapping("/detail/{id}")
//    public String toAdminDetail(
//            @PathVariable("id") Integer exId,
//            Model model
//    ) {
//        List<ExhibitionDetails> details = this.exhibitionService.queryExhibitionDetailsByExId(exId);
//        Exhibition exhibition = this.exhibitionService.queryExhibitionById(exId);
//        model.addAttribute("notes", details);
//        model.addAttribute("exhibition", exhibition);
//        return "myExhibition_notes";
//    }
//
//    @ApiOperation(value = "跳转到添加公告页面", notes="")
//    @GetMapping("/toAddDetail/{exId}")
//    public String toAddDetail(
//            @PathVariable("exId") Integer exId,
//            Model model
//    ) {
//        Exhibition exhibition = this.exhibitionService.queryExhibitionById(exId);
//        model.addAttribute("exhibition", exhibition);
//        return "myExhibition_notes_add";
//    }
//
//
//    @PostMapping("/addDetail/{exId}")
//    public String addDetail(
//            @PathVariable("exId")Integer exId,
//            @RequestParam("editorValue") String content,
//            @RequestParam("title")String title
//    ) {
//        this.exhibitionService.saveExhibitionDetail(exId, content, title);
//        return "redirect:/exhibition/toAddDetail/" + exId;
//    }
//
//
//}
