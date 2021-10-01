package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/4/7 17:29
 * 展会实体类附加展会想去人数
 */
public class ExhibitionBo1 extends Exhibition{
    private Integer forkNum;
    private String ownerName;

    public Integer getForkNum() {
        return forkNum;
    }

    public void setForkNum(Integer forkNum) {
        this.forkNum = forkNum;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
