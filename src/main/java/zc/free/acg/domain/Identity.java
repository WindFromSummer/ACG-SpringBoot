package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/3/29 22:33
 * 身份信息表实体类
 */
@Table(name = "identity")
public class Identity {
    //主键
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date createAt;
    private Date updateAt;
    private String description;

    public Identity() {
    }

    public Identity(String name, Date createAt, Date updateAt, String description) {
        this.name = name;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Identity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", description='" + description + '\'' +
                '}';
    }
}
