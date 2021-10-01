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
 * 用户身份表的实体类  多对多关系
 */
@Table(name = "user_identity")
public class UserIdentity implements Serializable {
    //主键
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer identityId;
    private Date createAt;
    private Date updateAt;


    public UserIdentity() {
    }

    public UserIdentity(Integer userId, Integer identityId, Date createAt, Date updateAt) {
        this.userId = userId;
        this.identityId = identityId;
        this.createAt = createAt;
        this.updateAt = updateAt;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "UserIdentity{" +
                "id=" + id +
                ", userId=" + userId +
                ", identityId=" + identityId +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
