package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/4/7 17:29
 * 展会实体类
 */
@Table(name = "exhibition")
public class Exhibition {
    //主键
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String img;
    private Date startAt;
    private Date endAt;
    private Integer type; //展会类型    1 漫展 2 科技展 3 游戏展
    private String address;
    private String tips;
    private Double price;
    private Integer state; //展会状态  0未开始  1 已开始 2 已结束
    private Integer owner;

    public Exhibition() {
    }

    public Exhibition(String name, String img, Date startAt, Date endAt, Integer type, String address, String tips, Double price, Integer state, Integer owner) {
        this.name = name;
        this.img = img;
        this.startAt = startAt;
        this.endAt = endAt;
        this.type = type;
        this.address = address;
        this.tips = tips;
        this.price = price;
        this.state = state;
        this.owner = owner;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Exhibition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                ", type=" + type +
                ", address='" + address + '\'' +
                ", tips='" + tips + '\'' +
                ", price=" + price +
                ", state=" + state +
                ", owner=" + owner +
                '}';
    }
}
