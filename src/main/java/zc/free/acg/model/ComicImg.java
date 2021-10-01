package zc.free.acg.model;

import java.util.Date;

public class ComicImg {
    private Integer id;

    private String img;

    private String come;

    private Date uploadTime;

    private Integer uploadUser;

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
        this.img = img == null ? null : img.trim();
    }

    public String getCome() {
        return come;
    }

    public void setCome(String come) {
        this.come = come == null ? null : come.trim();
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
}