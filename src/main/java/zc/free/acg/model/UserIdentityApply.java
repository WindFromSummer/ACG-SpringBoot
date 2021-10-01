package zc.free.acg.model;

import java.util.Date;

public class UserIdentityApply {
    private Integer id;

    private Integer userId;

    private Integer identityId;

    private Date applyAt;

    private String replyName;

    private String replyInfo;

    private Byte applyState;

    private String content;

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
        this.replyName = replyName == null ? null : replyName.trim();
    }

    public String getReplyInfo() {
        return replyInfo;
    }

    public void setReplyInfo(String replyInfo) {
        this.replyInfo = replyInfo == null ? null : replyInfo.trim();
    }

    public Byte getApplyState() {
        return applyState;
    }

    public void setApplyState(Byte applyState) {
        this.applyState = applyState;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}