package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/4/7 22:06
 * 展会评论实体类扩展类
 */
public class ExhibitionMsgBo extends ExhibitionMsg {
    private String msgOwnerName; //评论人名称

    public String getMsgOwnerName() {
        return msgOwnerName;
    }

    public void setMsgOwnerName(String msgOwnerName) {
        this.msgOwnerName = msgOwnerName;
    }
}
