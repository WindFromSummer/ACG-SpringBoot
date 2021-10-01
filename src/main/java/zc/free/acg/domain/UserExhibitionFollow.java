package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @auther ZhengCong
 * @date 2020/6/23 12:21
 */
@Table(name = "user_ex_follow")
public class UserExhibitionFollow {
    //主键
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer exId;
    private Integer userId;

    public UserExhibitionFollow() {
    }

    public UserExhibitionFollow(Integer id, Integer exId, Integer userId) {
        this.id = id;
        this.exId = exId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExId() {
        return exId;
    }

    public void setExId(Integer exId) {
        this.exId = exId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserExhibitionFollow{" +
                "id=" + id +
                ", exId=" + exId +
                ", userId=" + userId +
                '}';
    }
}
