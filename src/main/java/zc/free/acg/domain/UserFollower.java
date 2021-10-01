package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/4/6 22:00
 * 关注表
 */
public class UserFollower {
    //主键
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer followerUser;
    private Integer status;
    private Date createdAt;
    private Date updatedAt;

    public UserFollower() {
    }

    public UserFollower(Integer userId, Integer followerUser, Integer status, Date createdAt, Date updatedAt) {
        this.userId = userId;
        this.followerUser = followerUser;
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

    public Integer getFollowerUser() {
        return followerUser;
    }

    public void setFollowerUser(Integer followerUser) {
        this.followerUser = followerUser;
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
        return "UserFollower{" +
                "id=" + id +
                ", userId=" + userId +
                ", followerUser=" + followerUser +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
