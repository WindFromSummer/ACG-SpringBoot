package zc.free.acg.model;

import java.util.Date;

public class Topic {
    private Integer id;

    private String title;

    private String content;

    private Integer publishtUser;

    private Date createAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getPublishtUser() {
        return publishtUser;
    }

    public void setPublishtUser(Integer publishtUser) {
        this.publishtUser = publishtUser;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}