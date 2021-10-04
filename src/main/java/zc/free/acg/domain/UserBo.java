package zc.free.acg.domain;

import zc.free.acg.model.User;

/**
 * @auther ZhengCong
 * @date 2020/3/30 21:05
 * 用于实现查询用户是否关注自己的扩展实体类
 */
public class UserBo extends User {
    private Integer IsFollowered;

    public Integer getIsFollowered() {
        return IsFollowered;
    }

    public void setIsFollowered(Integer isFollowered) {
        IsFollowered = isFollowered;
    }

    @Override
    public String toString() {
        return "UserBo{" +
                "IsFollowered=" + IsFollowered +
                '}';
    }
}
