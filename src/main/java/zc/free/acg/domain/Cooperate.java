package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/4/7 17:29
 * 合作实体类
 */
@Table(name = "cooperate")
public class Cooperate {
    //主键
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cooperateName;
    private String content;
    private Integer ownerId;
    private String suitFor;
    private String location;
    private Date createAt;

    public Cooperate() {
    }

    public Cooperate(String cooperateName, String content, Integer ownerId, String suitFor, String location, Date createAt) {
        this.cooperateName = cooperateName;
        this.content = content;
        this.ownerId = ownerId;
        this.suitFor = suitFor;
        this.location = location;
        this.createAt = createAt;
    }

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
        this.cooperateName = cooperateName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        this.suitFor = suitFor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Cooperate{" +
                "id=" + id +
                ", cooperateName='" + cooperateName + '\'' +
                ", content='" + content + '\'' +
                ", ownerId=" + ownerId +
                ", suitFor='" + suitFor + '\'' +
                ", location='" + location + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}