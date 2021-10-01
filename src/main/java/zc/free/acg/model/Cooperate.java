package zc.free.acg.model;

import java.util.Date;

public class Cooperate {
    private Integer id;

    private String cooperateName;

    private String content;

    private Integer ownerId;

    private String suitFor;

    private String location;

    private Date createAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCooperateName() {
        return cooperateName;
    }

    public void setCooperateName(String cooperateName) {
        this.cooperateName = cooperateName == null ? null : cooperateName.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getSuitFor() {
        return suitFor;
    }

    public void setSuitFor(String suitFor) {
        this.suitFor = suitFor == null ? null : suitFor.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}