package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/4/6 22:01
 * 粉丝表
 */
@Table(name = "user_followed")
public class UserFollowed {
    //主键
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer followedUser;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;

    public UserFollowed() {
    }

    public UserFollowed(Integer userId, Integer followedUser, Integer status, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.followedUser = followedUser;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Integer getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(Integer followedUser) {
        this.followedUser = followedUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UserFollowed{" +
                "id=" + id +
                ", userId=" + userId +
                ", followedUser=" + followedUser +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
