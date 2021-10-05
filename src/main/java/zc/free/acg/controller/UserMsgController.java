package zc.free.acg.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import zc.free.acg.model.UserMsg;
import zc.free.acg.service.UserMsgService;

import java.util.List;

/**
 * @auther ZhengCong
 * @date 2020/4/7 21:40
 * 用户动态
 */
@Api(description = "用户动态接口")
@Controller
@RequestMapping("userMsg")
public class UserMsgController {
    @Autowired
    private UserMsgService userMsgService;
    /**
     * 用户发布动态
     * @return
     */
    @ApiOperation(value = "用户发送动态", notes="查数")
    @PostMapping("add")
    public ResponseEntity<Void> addMsg(UserMsg userMsg) {
        this.userMsgService.addMsg(userMsg);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 查询用户状态
     * @return
     */
    @ApiOperation(value = "用户发送动态", notes="查数")
    @GetMapping("{id}")
    public ResponseEntity<List<UserMsg>> queryMsgs(@PathVariable("id")Integer id) {
        List<UserMsg> msgs = this.userMsgService.queryMsgs(id);
        if(CollectionUtils.isEmpty(msgs)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(msgs);
    }


}
