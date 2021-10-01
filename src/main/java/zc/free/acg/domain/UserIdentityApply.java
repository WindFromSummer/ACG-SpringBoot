package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/4/6 16:24
 * 用户申请身份表 用户申请身份认证以及管理员进行申请审核的表实体类
 */
@Table(name = "user_identity_apply")
public class UserIdentityApply implements Serializable {
    //主键
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer identityId;
    private String content;
    private Date applyAt;
    private String replyName;
    private String replyInfo;
    private Integer applyState;

    public UserIdentityApply() {
    }

    public UserIdentityApply(Integer userId, Integer identityId, String content, Date applyAt, String replyName, String replyInfo, Integer applyState) {
        this.userId = userId;
        this.identityId = identityId;
        this.content = content;
        this.applyAt = applyAt;
        this.replyName = replyName;
        this.replyInfo = replyInfo;
        this.applyState = applyState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Integer identityId) {
        this.identityId = identityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getApplyAt() {
        return applyAt;
    }

    public void setApplyAt(Date applyAt) {
        this.applyAt = applyAt;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public String getReplyInfo() {
        return replyInfo;
    }

    public void setReplyInfo(String replyInfo) {
        this.replyInfo = replyInfo;
    }

    public Integer getApplyState() {
        return applyState;
    }

    public void setApplyState(Integer applyState) {
        this.applyState = applyState;
    }

    @Override
    public String toString() {
        return "UserIdentityApply{" +
                "id=" + id +
                ", userId=" + userId +
                ", identityId=" + identityId +
                ", content='" + content + '\'' +
                ", applyAt=" + applyAt +
                ", replyName='" + replyName + '\'' +
                ", replyInfo='" + replyInfo + '\'' +
                ", applyState=" + applyState +
                '}';
    }
}
