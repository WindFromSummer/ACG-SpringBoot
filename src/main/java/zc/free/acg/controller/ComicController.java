//package zc.free.acg.controller;
//
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import zc.free.acg.domain.ComicImg;
//import zc.free.acg.service.UploadService;
//
//import java.util.List;
//
///**
// * @auther ZhengCong
// * @date 2020/5/15 16:39
// * 群友图片上传功能
// */
//@Controller
//@RequestMapping("comicImg")
//public class ComicController {
//    private static String prefix = "extra/comicImg/";
//
//    @Autowired
//    private UploadService uploadService;
//
//    @Autowired
////    private ComicImgMapper comicImgMapper;
//
//    @GetMapping("/")
//    public String comicImg() {
//        return prefix + "comicImg";
//    }
//
//    /**
//     * 上传图片
//     * @return
//     */
//    @ApiOperation(value = "更换头像", notes="更新用户头像 ")
//    @ApiImplicitParam(name = "file", value = "上传的图片",required = true, dataType = "MultipartFile")
//    @PostMapping("upload")
//    public String uploadComicImg(@RequestParam("img") MultipartFile img) {
//        String url = this.uploadService.uploadImage(img);
//        ComicImg comicImg = new ComicImg();
//        comicImg.setImg(url);
//        int i = this.comicImgMapper.insertSelective(comicImg);
//        return prefix + "comicImg";
//    }
//
//    /**
//     * 查询所有图片
//     * @return
//     */
//    @ApiOperation(value = "更换头像", notes="更新用户头像 ")
//    @ApiImplicitParam(name = "file", value = "上传的图片",required = true, dataType = "MultipartFile")
//    @GetMapping("query")
//    public String queryComicImg(Model model) {
//        List<ComicImg> comicImgs = this.comicImgMapper.selectAll();
//        model.addAttribute("imgs", comicImgs);
//        return prefix + "comicImg";
//    }
//
//}
