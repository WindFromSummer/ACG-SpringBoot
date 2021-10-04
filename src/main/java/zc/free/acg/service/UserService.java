package zc.free.acg.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;
import zc.free.acg.domain.PageResult;
import zc.free.acg.model.User;
import zc.free.acg.model.UserFollower;

import java.util.List;

public interface UserService {
    /**
     * 校验用户数据是否可用
     * @param data  校验的数据
     * @param type  数据类型
     * @return  true 用户可用  false 用户不可用
     */
    Boolean checkUser(String data, Integer type);

    /**
     * 向手机发送注册验证码
     * @param phone 手机号码
     */
    void sendVerifyCode(String phone) throws ClientException;

    /**
     * 向手机号发送修改密码验证码
     * @param phone 手机号
     */
    void sendPwdVerifyCode(String phone) throws ClientException;

    /**
     * 账号注册
     * @param user
     * @param code
     */
    void register(User user, String code);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    User queryUser(String username, String password);

    /**
     * 根据用户ID查询用户信息
     * @param id  用户ID
     * @return
     */
    User queryUserById(Integer id);

    /**
     * 关注用户
     * @param userFollower
     */
    void starUser(UserFollower userFollower);

    /**
     * 查询关注数量
     * @param userId
     * @return
     */
    Integer queryFollowerCount(Integer userId);

    /**
     * 查询关注的人
     * @param userId
     * @return
     */
    List<User> queryFollowers(Integer userId);

    /**
     * 分页查询关注用户
     * @param userId
     * @param page
     * @param cows
     * @return
     */
    PageResult<User> queryFollowersByPage(Integer userId, Integer page, Integer cows);

    /**
     * 查询粉丝数量
     * @param userId
     * @return
     */
    Integer queryFollowedCount(Integer userId);

    /**
     * 查询粉丝
     * @param userId
     * @return
     */
    List<User> queryFolloweds(Integer userId);

    /**
     * 分页查询我的粉丝列表
     * @param userId
     * @param page
     * @param cows
     * @return
     */
    PageResult<User> queryFollowedsByPage(Integer userId, Integer page, Integer cows);

    /**
     * 根据关键字模糊查询用户列表
     * @param key
     * @return
     */
    PageResult<User> queryUserByKey(String key,Integer page, Integer cows);

    /**
     * 更新头像
     * @param id
     * @param file
     * @return
     */
    String uploadImg(Integer id, MultipartFile file);

    /**
     * 修改个人基本信息
     * @param user
     */
    void updateUserInfo(User user);

    /**
     * 取消关注
     * @param id
     * @param targetUser
     */
    void cancelStarUser(Integer id, Integer targetUser);

    /**
     * 用户修改密码
     * @param user
     * @param code
     */
    void updatePwd(User user, String code);

    /**
     * 查询用户是否关注展会
     * @param id
     * @param exId
     * @return
     */
    Boolean queryUserIsForkExhibition(Integer id, Integer exId);

    /**
     * 用户关注展会
     * @param id
     * @param exId
     */
    void ForkExhibition(Integer id, Integer exId);
}
