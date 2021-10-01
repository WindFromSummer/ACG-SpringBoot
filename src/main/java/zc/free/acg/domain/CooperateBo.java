package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/4/7 17:29
 * 合作扩展类 包含发布人姓名
 */
public class CooperateBo extends Cooperate{
    private String ownerName;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public String toString() {
        return "CooperateBo{" +
                "ownerName='" + ownerName + '\'' +
                '}';
    }
}