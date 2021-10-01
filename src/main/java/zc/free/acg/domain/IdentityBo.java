package zc.free.acg.domain;

import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/5/7 11:27
 * 用户查询自己所拥有的身份信息  包括自己拥有的身份名称和获取的时间
 */
public class IdentityBo {
    //身份名称
    private String name;
    //获取时间
    private Date createAt;

    public IdentityBo() {
    }

    public IdentityBo(String name, Date createAt) {
        this.name = name;
        this.createAt = createAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "IdentityBo{" +
                "name='" + name + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
