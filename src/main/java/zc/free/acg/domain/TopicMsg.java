package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/5/9 18:18
 */
@Table(name = "topic_msg")
public class TopicMsg {
    //主键
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String content;
    private Date createAt;
    private Integer parentId;
    private Integer topicId;

    public TopicMsg() {
    }

    public TopicMsg(Integer userId, String content, Date createAt, Integer parentId, Integer topicId) {
        this.userId = userId;
        this.content = content;
        this.createAt = createAt;
        this.parentId = parentId;
        this.topicId = topicId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "TopicMsg{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                ", parentId=" + parentId +
                ", topicId=" + topicId +
                '}';
    }
}
