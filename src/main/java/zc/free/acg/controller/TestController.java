package zc.free.acg.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zc.free.acg.domain.PageResult;
import zc.free.acg.domain.editor.PublicMsg;
import zc.free.acg.domain.editor.Ueditor;
import zc.free.acg.mapper.ExhibitionDetailsMapper;
import zc.free.acg.model.*;
import zc.free.acg.service.ExhibitionService;
import zc.free.acg.service.UploadService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @auther ZhengCong
 * @date 2020/4/22 13:40
 * 连接测试使用
 */
@Controller
public class TestController {
    private static String prefix = "test/";
    //-------------------------------------------

    @Autowired
    private ExhibitionService exhibitionService;
    /**
     * 跳转测试页面
     * @return
     */
    @GetMapping("/test")
    public String toTest() {
        return prefix + "test";
    }
    /**
     * 测试图片上传
     * @return
     */
    @GetMapping("/testUpLoad")
    public String toTestUpLoad() {
        return prefix + "upload";
    }
    /**
     * 审核身份列表
     * @return
     */
    @GetMapping("/t1")
    public String t1() {
        return prefix + "t1";
    }
    @GetMapping("/t2")
    public String t2() {
        return prefix + "t2";
    }
    @GetMapping("/t4")
    public String t4() {
        return prefix + "t4";
    }
    @GetMapping("/t5")
    public String t5() {
        return prefix + "t5";
    }
    @GetMapping("/t6")
    public String t6() {
        return prefix + "t6";
    }
    @GetMapping("/t7")
    public String t7() {
        return prefix + "t7";
    }

    @GetMapping("/t8")
    public String t8() {
        return prefix + "testSendAjaxWhenDocLoad";
    }
    @GetMapping("/t9")
    public String t9() {
        return prefix + "t9";
    }
    @GetMapping("/t10")
    public String t10() {
        return prefix + "t10";
    }

    @GetMapping("/t12")
    public String t12() {
        return "popper";
    }

    @GetMapping("/t13")
    public String t13() {
        return "search_head";
    }

    @PostMapping("/t14")
    @ResponseBody
    public void t14() {
        System.out.println("进入t14方法");
    }

    @PostMapping("/t15")
    @ResponseBody
    public void t15() {
        System.out.println("进入t15方法");
    }

    /**
     * 测试分页
     * @return
     */
    @GetMapping("/testFenye")
    public String testFenye() {
        return prefix + "testFenye";
    }

    /**
     * 测试根据查询条件进行分页查询
     * @param key 名称 模糊匹配
     * @param city 城市
     * @param page 页码
     * @param cows 行数
     * @param sortBy 排序条件
     * @param desc 升降序
     * @return 展会分页结果集
     */
    @ApiOperation(value = "根据条件查询展会", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "展会名称",paramType = "query",required = false, dataType = "String"),
            @ApiImplicitParam(name = "city", value = "所在城市", paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", paramType = "query", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "cows", value = "当前页数", paramType = "query", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy", value = "当前页数", paramType = "query", required = false, dataType = "String"),
            @ApiImplicitParam(name = "desc", value = "当前页数", paramType = "query", required = false, dataType = "Boolean")
    })
    @PostMapping("/page")
    public String queryExhibitions(
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "city",required = false) String city,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "cows",defaultValue = "5") Integer cows,
            @RequestParam(value = "sortBy",required = false) String sortBy,
            @RequestParam(value = "desc",required = false) Boolean desc,
            Model model
    ) {
        PageResult<Exhibition> data =this.exhibitionService.queryExhibitionsByPage(key,city,page,cows,sortBy,desc);
        model.addAttribute("curPage", page);
        model.addAttribute("key", key);
        model.addAttribute("data", data);
        return prefix + "testFenye";
    }
    @Autowired
    private ExhibitionDetailsMapper exhibitionDetailsMapper;
    /**
     * 测试跳转登录页面
     * @return
     */
    @GetMapping("/tail")
    public String getM(Model model) {
        ExhibitionDetailsExample exhibitionDetailsExample = new ExhibitionDetailsExample();
        exhibitionDetailsExample.createCriteria().andIdEqualTo(1);
        List<ExhibitionDetails> exhibitionDetails = this.exhibitionDetailsMapper.selectByExample(exhibitionDetailsExample);
        ExhibitionDetails details = exhibitionDetails.get(0);
        model.addAttribute("data", details);
        return prefix +"t11";
    }
    /**
     * 测试跳转登录页面
     * @return
     */
    @GetMapping("/userlogin")
    public String toUserLogin() {
        return "login";
    }

    /**
     * 测试
     * 跳转富文本编辑器页面
     * @return
     */
    @GetMapping("/toEditorPage")
    public String toText() {
        return "text";
    }
    /**
     * 测试
     * 跳转展示图片页面
     * @return
     */
    @GetMapping("/toshowimg")
    public String toshowimg() {
        return prefix + "showimg";
    }
    /**
     * 测试
     * thymeleaf接受异步请求页面
     * @return
     */
    @PostMapping("/testAjax")
    public ResponseEntity<User> testAjax(@RequestParam("name")String name) {
        User user = new User();
        user.setUsername(name);
        return ResponseEntity.ok(user);
    }
    /**
     * 测试
     * thymeleaf接受异步请求页面
     * @return
     */
    @GetMapping("/toTest")
    public String toTestPost() {
        return "test/testPost";
    }


    /**
     * 测试 spring security 的自定义登录页面处理登录请求
     * @return
     */
    @GetMapping("/a")
    public String a() {
        return "login";
    }
    @PostMapping("/a")
    public String ab() {
        return "login";
    }

    /**
     * 认证通过获取用户身份
     * @return
     */
    @GetMapping("username")
    public String getUser() {
        String username = null;
        //当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal == null) {
            username = "匿名";
        }
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails)principal;
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
    /**
     * 跳转到富文本编辑器页面
     * @return
     */
    @GetMapping("/editor")
    public String toEditor() {
        return prefix + "editor";
    }
    @RequestMapping(value="/ueditor")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {

        return PublicMsg.UEDITOR_CONFIG;
    }
    @Autowired
    private UploadService uploadService;

    /**
     * Ueditor的图片上传功能 只能使用多图片上传按钮
     * @param upfile
     * @return
     */
    @RequestMapping(value="/imgUpload")
    @ResponseBody
    public Ueditor imgUpload(MultipartFile upfile) {
        Ueditor ueditor = new Ueditor();
        String fileName = upfile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.') + 1);
        //上传图片返回地址
        String url = this.uploadService.uploadImage(upfile);
        //这几个值很关键
        ueditor.setState("SUCCESS");
        //这里是图片完整路径，不管是本地路径，还是文件服务器路径都行
        ueditor.setUrl(url);
        ueditor.setOriginal(fileName);
        ueditor.setTitle(fileName);
        return ueditor;
    }


    /**
     * 测试上传富文本编辑器的内容
     * @param content
     */
    @PostMapping("/testSave")
    public String testSave(@RequestParam("editorValue") String content) {
        return "redirect:/editor";
    }


    @GetMapping("/p/{id}")
    public String testPariable(@PathVariable("id") Integer id) {
        return "index2";
    }


}
