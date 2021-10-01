package zc.free.acg.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @auther ZhengCong
 * @date 2020/5/15 17:01
 * 图片实体类
 */
@Table(name = "comic_img")
public class ComicImg {
    //主键
    @Id
    //自增
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String img;
    private String come;
    private Date uploadTime;
    private Integer uploadUser;

    public ComicImg() {
    }

    public ComicImg(String img, String come, Date uploadTime, Integer uploadUser) {
        this.img = img;
        this.come = come;
        this.uploadTime = uploadTime;
        this.uploadUser = uploadUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCome() {
        return come;
    }

    public void setCome(String come) {
        this.come = come;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(Integer uploadUser) {
        this.uploadUser = uploadUser;
    }

    @Override
    public String toString() {
        return "ComicImg{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", come='" + come + '\'' +
                ", uploadTime=" + uploadTime +
                ", uploadUser=" + uploadUser +
                '}';
    }
}
