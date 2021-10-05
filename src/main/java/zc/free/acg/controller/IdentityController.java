package zc.free.acg.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zc.free.acg.domain.IdentityBo;
import zc.free.acg.domain.PageResult;
import zc.free.acg.domain.UserIdentityApplyBo;
import zc.free.acg.model.Identity;
import zc.free.acg.model.UserIdentityApply;
import zc.free.acg.service.IdentityService;


import java.util.List;

/**
 * @auther ZhengCong
 * @date 2020/3/29 22:22
 * 身份认证
 */
@Controller
@RequestMapping("identity")
public class IdentityController {
    @Autowired
    private IdentityService identityService;

    /**
     * 管理员进行添加身份标签
     * @param name
     * @return
     */
    @ApiOperation(value = "管理员新增身份", notes="参数 name:身份名称")
    @ApiImplicitParam(name = "name", value = "身份名称",paramType = "query",required = true, dataType = "String")
    @PostMapping("add")
    public ResponseEntity<Void> addIdentity(String name) {
        int i = this.identityService.addIdentity(name);
        if(i == -1){
            //响应400 参数不合法
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据姓名查询身份
     * @return
     */
    @ApiOperation(value = "根据姓名查询身份", notes="参数 name:用户姓名")
    @GetMapping("queryByName")
    public ResponseEntity<Identity> queryIdentityByName(@RequestParam String name) {
        Identity identity = this.identityService.queryIdentityByName(name);
        if(identity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(identity);
    }

    /**
     * 根据id查询身份
     * @return
     */
    @ApiOperation(value = "根据id查询身份", notes="参数 id:用户id")
    @GetMapping("queryById/{id}")
    public String queryIdentityById(@PathVariable("id") Integer id, Model model) {
        List<IdentityBo> identities = this.identityService.queryIdentityById(id);
        model.addAttribute("identities",identities);
        return "identity";
    }

    /**
     * 根据用户id分页查询身份
     * @return
     */
    @ApiOperation(value = "根据id查询身份", notes="参数 id:用户id")
    @GetMapping("queryByIdWithPage/{id}")
    public String queryIdentityByIdWithPage(
            @PathVariable("id") Integer id,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "cow",defaultValue = "5") Integer cows,
            Model model) {
        PageResult<IdentityBo> result =this.identityService.queryIdentityByIdWithPage(id,page,cows);
        model.addAttribute("curPage", page);
        model.addAttribute("data", result);
        return "identity";
    }

    /**
     * 查询所有身份标签
     * @return
     */
    @ApiOperation(value = "查询返回所有的身份列表", notes="")
    @GetMapping("query")
    public ResponseEntity<List<Identity>> queryIdentitys() {
        List<Identity> identitys = this.identityService.queryIdentitys();
        if(CollectionUtils.isEmpty(identitys)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(identitys);
    }

    /**
     * 跳转申请身份认证页面
     * @return
     */
    @GetMapping("/identityApply")
    public String toIdentityApply(Model model) {
        List<Identity> identities = this.identityService.queryIdentitys();
        model.addAttribute("identities",identities);
        return "identity_apply";
    }

    /**
     * 申请身份认证
     * @return
     */
    @ApiOperation(value = "申请身份认证", notes="userId 用户ID identityId 身份ID content 上传文件的路径")
    @PostMapping("apply")
    public String applyIdentity(UserIdentityApply applyInfo, @RequestParam("file") MultipartFile file) {
        this.identityService.applyIdentity(applyInfo,file);
        return "identity_apply";
    }

    /**
     * 查询用户的身份审核状态
     * @param id
     * @param model
     * @return
     */
    @GetMapping("applyStatus/{id}")
    public String queryApplyStatus(@PathVariable("id") Integer id, Model model) {
        List<UserIdentityApplyBo> applies = this.identityService.queryApplyWithIdentityNameByUserId(id);
      model.addAttribute("applies",applies);
        return "identity_apply_status";
    }

    /**
     * 查询状态为待审核的身份认证申请
     * @return
     */
    @ApiOperation(value = "查询未处理的身份认证申请", notes="查询未处理的身份认证申请 无需参数")
    @GetMapping("apply/query")
    public String queryApply(Model model) {
        List<UserIdentityApplyBo> applies = this.identityService.queryApplyAndIdentityName();
        model.addAttribute("applies",applies);
        return "identity_apply_admin";
    }

    /**
     * 进入审核页面
     * @return
     */
    @ApiOperation(value = "进入审核页面", notes="进入审核页面 无需参数")
    @GetMapping("apply/reply/{id}")
    public String replyApply(@PathVariable("id") Integer id, Model model) {
        UserIdentityApplyBo apply = this.identityService.queryApplyAndIdentityNameById(id);
        model.addAttribute("apply", apply);
        return "identity_apply_reply";
    }
    /**
     * 管理员对身份认证申请进行回复
     * @return
     */
    @ApiOperation(value = "回复未处理的身份认证申请", notes="查询未处理的身份认证申请 无需参数")
    @PostMapping("apply/reply")
    public ResponseEntity<Void> replyApply(UserIdentityApply userIdentityApply) {
        this.identityService.updateApply(userIdentityApply);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
