package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/3/30 18:05
 * 用户角色表实体类
 */
@Table(name = "user_role")
public class UserRole {
    //主键
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer roleId;
    private Integer status;
    private Date createAt;
    private Date updateAt;

}
