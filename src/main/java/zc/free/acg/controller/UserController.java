package zc.free.acg.controller;

import com.aliyuncs.exceptions.ClientException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zc.free.acg.domain.PageResult;
import zc.free.acg.model.UserFollower;
import zc.free.acg.model.User;
import zc.free.acg.service.UserService;

/**
 * @auther ZhengCong
 * @date 2020/3/29 16:31
 */
@Api(description = "用户操作接口")
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 检查用户数据是否可用 包括用户名和手机号是否已被主注册
     * @param data  传入的数据
     * @param type  传入的数据类型  1为用户名 2为手机号
     * @return
     */
    @ApiOperation(value = "验证用户是否可用", notes="true为可用 false为不可用 type=1 数据为用户名 type=2 数据为手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "测试数据",paramType = "path",required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "测试数据的类型", paramType = "path", required = true, dataType = "Integer"),
    })
    @GetMapping("/check/{data}/{type}")
    public ResponseEntity<Boolean> checkUser(
            @PathVariable("data") String data,
            @PathVariable("type") Integer type
    ) {
        Boolean bool = this.userService.checkUser(data,type);
        if(bool == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bool);
    }

    /**
     * 向指定手机号发送注册验证码
     * @param phone  手机号码
     * @return
     */
    @ApiOperation(value = "向指定手机发送验证码", notes="phone:手机号")
    @ApiImplicitParam(name = "phone", value = "手机号码",paramType = "query",required = true, dataType = "String")
    @PostMapping("/code")
    public ResponseEntity<Void> sendVerifyCode(@RequestParam("phone") String phone) {
        try {
            this.userService.sendVerifyCode(phone);
        } catch (ClientException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 向指定手机号发送修改密码验证码
     * @param phone  手机号码
     * @return
     */
    @ApiOperation(value = "向指定手机号发送修改密码验证码", notes="phone:手机号")
    @ApiImplicitParam(name = "phone", value = "手机号码",paramType = "query",required = true, dataType = "String")
    @PostMapping("/pwdCode")
    public ResponseEntity<Void> sendPwdVerifyCode(@RequestParam("phone") String phone) {
        try {
            this.userService.sendPwdVerifyCode(phone);
        } catch (ClientException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 用户注册功能
     * @param user
     * @param code
     * @return
     */
    @ApiOperation(value = "用户注册功能", notes="用户注册 需要code name phone password")
    @PostMapping("/register")
    public String register(@RequestParam(value = "code") String code , User user) {
        this.userService.register(user,code);
        return "index";
    }

    /**
     * 用户修改密码
     * @param user
     * @param code
     * @return
     */
    @ApiOperation(value = "修改用户密码", notes="")
    @PostMapping("/updatePwd")
    public String updatePwd(@RequestParam(value = "code") String code , User user) {
        this.userService.updatePwd(user,code);
        return "index";
    }

    /**
     * 判断用户名密码是否正确
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "登录查询功能", notes="参数 username password")
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam("username")String username, @RequestParam("password") String password) {
        System.out.println("flag");
        User user = this.userService.queryUser(username,password);
        if(user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    @ApiOperation(value = "查询个人信息", notes="参数用户ID ")
    @ApiImplicitParam(name = "id", value = "用户ID",paramType = "path",required = true, dataType = "Integer")
    @GetMapping("query/{id}")
    public ResponseEntity<User> queryUser(@PathVariable("id") Integer id) {
        User user = this.userService.queryUserById(id);
        if(user == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }


    /**
     * 更换用户头像
     * @param file
     * @return
     */
    @ApiOperation(value = "更换头像", notes="更新用户头像 ")
    @ApiImplicitParam(name = "file", value = "上传的图片",required = true, dataType = "MultipartFile")
    @PostMapping("uploadImg")
    public String uploadImg(@RequestParam("file") MultipartFile file, @RequestParam("userId")Integer id) {
        String url = this.userService.uploadImg(id,file);
        return "updateImg";
    }

    /**
     * 根据用户名称模糊查询用户列表
     * @param key
     * @return
     */
    /*@ApiOperation(value = "根据用户名称模糊查询用户列表", notes="用户关键字 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "用户关键字",paramType = "query",required = false, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", paramType = "query", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "cows", value = "每页条数", paramType = "query", required = false, dataType = "Integer")
    })
    @GetMapping("query/users")
    public ResponseEntity<PageResult<User>> queryUserByKey(
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "cows",defaultValue = "5") Integer cows) {

        PageResult<User> result =this.userService.queryUserByKey(key,page,cows);
        if(CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }*/
    /**
     * 根据用户名称模糊查询用户列表
     * @param key
     * @return
     */
    @ApiOperation(value = "根据用户名称模糊查询用户列表", notes="用户关键字 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "用户关键字",paramType = "query",required = false, dataType = "String"),
            @ApiImplicitParam(name = "page", value = "当前页数", paramType = "query", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "cows", value = "每页条数", paramType = "query", required = false, dataType = "Integer")
    })
    @PostMapping("query/users")
    public String queryUserByKey(
            @RequestParam(value = "key",required = false) String key,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "cows",defaultValue = "5") Integer cows,
            Model model
    ) {

        PageResult<User> result =this.userService.queryUserByKey(key,page,cows);
        model.addAttribute("curPage", page);
        model.addAttribute("key", key);
        model.addAttribute("data", result);
        return "search_result_user";
    }


    /**
     * 关注用户
     * @param userFollower
     * @return
     */
    @ApiOperation(value = "关注用户", notes="用户id userId 类型Integer \r\n和目标用户id followerUser 类型Integer")
    @PostMapping("star")
    public ResponseEntity<Void> starUser(UserFollower userFollower) {
        this.userService.starUser(userFollower);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 查询指定用户是否关注指定展会
     * @param id
     * @param exId
     * @return
     */
    @ApiOperation(value = "查询指定用户是否关注指定展会", notes="用户id id 类型Integer \r\n和展会id exId 类型Integer")
    @GetMapping("/query/forkExhibition/{id}/{exId}")
    public ResponseEntity<Boolean> queryUserIsForkExhibition(
            @PathVariable("id") Integer id,
            @PathVariable("exId") Integer exId
    ) {
        Boolean b = this.userService.queryUserIsForkExhibition(id,exId);
        return ResponseEntity.ok(b);
    }


    /**
     * 查询指定用户是否关注指定展会
     * @param id
     * @param exId
     * @return
     */
    @ApiOperation(value = "查询指定用户是否关注指定展会", notes="用户id id 类型Integer \r\n和展会id exId 类型Integer")
    @GetMapping("/forkExhibition/{id}/{exId}")
    public ResponseEntity<Void> ForkExhibition(
            @PathVariable("id") Integer id,
            @PathVariable("exId") Integer exId
    ) {
        this.userService.ForkExhibition(id,exId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 取消关注
     * @param id
     * @param targetUser
     * @return
     */
    @PostMapping("cancelStarUser")
    public ResponseEntity<Void> CancelStarUser(Integer id, Integer targetUser) {
        System.out.println("进入");
        this.userService.cancelStarUser(id,targetUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /**
     * 查询我的关注用户数量
     * @param userId
     * @return
     */
    @ApiOperation(value = "查询我的关注用户数量", notes="")
    @ApiImplicitParam(name = "userId", value = "用户ID",paramType = "query",required = true, dataType = "Integer")
    @GetMapping("follower/query")
    public ResponseEntity<Integer> queryFollowerCount(Integer userId) {
        Integer followerCount = this.userService.queryFollowerCount(userId);
        return ResponseEntity.ok(followerCount);
    }

    /**
     * 查询我的关注用户列表
     * @param userId
     * @return
     */
    /*@ApiOperation(value = "查询关注的人", notes="")
    @ApiImplicitParam(name = "userId", value = "用户ID",paramType = "query",required = true, dataType = "Integer")
    @GetMapping("followers")
    public ResponseEntity<List<User>> queryFollowers(Integer userId) {
        List<User> followers = this.userService.queryFollowers(userId);
        if(CollectionUtils.isEmpty(followers)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(followers);
    }*/

    /**
     * 查询我的关注用户列表
     * @param userId
     * @return
     */
    /*@ApiOperation(value = "查询关注的人", notes="")
    @ApiImplicitParam(name = "userId", value = "用户ID",paramType = "query",required = true, dataType = "Integer")
    @GetMapping("followers")
    public String queryFollowers(Integer userId, Model model) {
        List<User> followers = this.userService.queryFollowers(userId);
        model.addAttribute("followers",followers);
        return "myfollower";
    }*/

    /**
     * 分页查询我的关注列表
     * @param page 页码
     * @param cows 行数
     * @return 关注用户结果集
     */
    @ApiOperation(value = "分页查询我的关注列表", notes="")
    @GetMapping("followers")
    public String queryFollowers(
            @RequestParam(value = "userId",required = true) Integer userId,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "cows",defaultValue = "5") Integer cows,
            Model model
    ) {
        PageResult<User> followers = this.userService.queryFollowersByPage(userId,page,cows);
        model.addAttribute("curPage", page);
        model.addAttribute("data", followers);
        return "myfollower";
    }

    /**
     * 查询用户粉丝数量
     * @param userId
     * @return
     */
    @ApiOperation(value = "查询粉丝的人数量", notes="")
    @ApiImplicitParam(name = "userId", value = "用户ID",paramType = "query",required = true, dataType = "Integer")
    @GetMapping("followed/query")
    public ResponseEntity<Integer> queryFollowed(Integer userId) {
        Integer followedCount = this.userService.queryFollowedCount(userId);
        return ResponseEntity.ok(followedCount);
    }

    /**
     * 查询粉丝列表
     * @param userId
     * @return
     */
    /*@ApiOperation(value = "查询粉丝", notes="")
    @ApiImplicitParam(name = "userId", value = "用户ID",paramType = "query",required = true, dataType = "Integer")
    @GetMapping("followeds")
    public ResponseEntity<List<User>> queryFolloweds(Integer userId) {
        List<User> followeds = this.userService.queryFolloweds(userId);
        if(CollectionUtils.isEmpty(followeds)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(followeds);
    }*/

    /**
     * 分页查询我的粉丝列表
     * @param page 页码
     * @param cows 行数
     * @return 关注用户结果集
     */
    @ApiOperation(value = "分页查询我的粉丝列表", notes="")
    @GetMapping("followeds")
    public String queryFollowerds(
            @RequestParam(value = "userId",required = true) Integer userId,
            @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "cows",defaultValue = "5") Integer cows,
            Model model
    ) {
        PageResult<User> followeds = this.userService.queryFollowedsByPage(userId,page,cows);
        model.addAttribute("curPage", page);
        model.addAttribute("data", followeds);
        return "myfollowed";
    }

    /**
     * 修改用户基本信息
     * @param user
     * @return
     */
    @ApiOperation(value = "修改用户基本信息", notes="修改用户基本信息 ")
    @PostMapping("updateUserInfo")
    public String updateUserInfo(User user) {
        this.userService.updateUserInfo(user);
        return "myInfo";
    }

}
