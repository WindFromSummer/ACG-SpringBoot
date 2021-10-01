package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/4/9 11:12
 * 展会公告实体类
 */
@Table(name = "exhibition_details")
public class ExhibitionDetails implements Serializable {
    //主键
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer exhibitionId;
    private String title;
    private String content;
    private Date createAt;

    public ExhibitionDetails() {
    }

    public ExhibitionDetails(Integer exhibitionId, String title, String content, Date createAt) {
        this.exhibitionId = exhibitionId;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(Integer exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "ExhibitionDetails{" +
                "id=" + id +
                ", exhibitionId=" + exhibitionId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
