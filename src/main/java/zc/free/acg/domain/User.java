//package zc.free.acg.domain;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.util.Date;
//
///**
// * @auther ZhengCong
// * @date 2020/3/29 16:41
// * User实体类
// */
//
//@Table(name = "user")
//public class User {
//    //主键
//    @Id
//    //自增
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//    //姓名
//    private String username;
//    //用户头像
//    private String image;
//    //性别
//    private Integer gender;
//    //用户创建时间
//    private Date created;
//    //用户电话
//    private String phone;
//    //用户密码
//    @JsonIgnore //对象序列化为json字符串时 忽略该属性
//    private String password;
//    //用户密码盐值
//    @JsonIgnore //对象序列化为json字符串时 忽略该属性
//    private String salt;
//
//    public User() {
//    }
//
//    public User(String username, String image, Integer gender, Date created, String phone, String password, String salt) {
//        this.username = username;
//        this.image = image;
//        this.gender = gender;
//        this.created = created;
//        this.phone = phone;
//        this.password = password;
//        this.salt = salt;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    public Integer getGender() {
//        return gender;
//    }
//
//    public void setGender(Integer gender) {
//        this.gender = gender;
//    }
//
//    public Date getCreated() {
//        return created;
//    }
//
//    public void setCreated(Date created) {
//        this.created = created;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getSalt() {
//        return salt;
//    }
//
//    public void setSalt(String salt) {
//        this.salt = salt;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", image='" + image + '\'' +
//                ", gender=" + gender +
//                ", created=" + created +
//                ", phone='" + phone + '\'' +
//                ", password='" + password + '\'' +
//                ", salt='" + salt + '\'' +
//                '}';
//    }
//}
