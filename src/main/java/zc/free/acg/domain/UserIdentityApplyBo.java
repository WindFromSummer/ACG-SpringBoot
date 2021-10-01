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
 * 用户申请身份表扩展类 用户申请身份认证以及管理员进行申请审核的表实体类 并具有身份名称
 */
public class UserIdentityApplyBo extends UserIdentityApply implements Serializable {
    private String identityName;


    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    @Override
    public String toString() {
        return "UserIdentityApplyBo{" +
                "identityName='" + identityName + '\'' +
                '}';
    }
}
