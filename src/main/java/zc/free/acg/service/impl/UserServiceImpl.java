package zc.free.acg.service.impl;



import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import zc.free.acg.config.PwdSmsProperties;
import zc.free.acg.config.SmsProperties;
import zc.free.acg.domain.PageResult;
import zc.free.acg.domain.User;
import zc.free.acg.domain.UserFollowed;
import zc.free.acg.domain.UserFollower;
import zc.free.acg.mapper.UserFollowedMapper;
import zc.free.acg.mapper.UserFollowerMapper;
import zc.free.acg.mapper.UserMapper;
import zc.free.acg.model.UserExample;
import zc.free.acg.service.UploadService;
import zc.free.acg.service.UserService;
import zc.free.acg.util.CodecUtils;
import zc.free.acg.util.NumberUtils;
import zc.free.acg.util.SmsUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @auther ZhengCong
 * @date 2020/3/29 16:39
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    //手机注册验证码
    @Autowired
    private SmsProperties properties;
    //修改密码手机验证码
    @Autowired
    private PwdSmsProperties pwdSmsProperties;
    @Autowired
    private SmsUtils smsUtils;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserFollowerMapper userFollowerMapper;
    @Autowired
    private UserFollowedMapper userFollowedMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UploadService uploadService;
    //用于手机注册验证码存储到redis的前置标识
    private static final String REGISTER_KEY_PREFIX = "user:verify:";
    //用于修改密码的验证码存储到redis的前置标识
    private static final String UPDATEPWD_KEY_PREFIX = "user:updatePwd:";

    @Override
    public Boolean checkUser(String data, Integer type) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if(type == 1) {
            criteria.andUsernameEqualTo(data);
        } else if(type == 2) {
            criteria.andPhoneEqualTo(data);
        } else {
            // 暂不支持的登录类型
            return null;
        }
        long userCount = userMapper.countByExample(userExample);

        if ( 0 == userCount){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void sendVerifyCode(String phone) throws ClientException {
        if (StringUtils.isBlank(phone))  {
            return ;
        }
        //生成验证码
        String code = NumberUtils.generateCode(6);


        //发送验证码
        smsUtils.sendSms(phone, code, properties.getSignName(), properties.getVerifyCodeTemplate());
        //将验证码储存到redis中
        this.stringRedisTemplate.opsForValue().set(REGISTER_KEY_PREFIX + phone, code, 5, TimeUnit.MINUTES);
    }

    @Override
    public void sendPwdVerifyCode(String phone) throws ClientException {
        if (StringUtils.isBlank(phone))  {
            return ;
        }
        //生成验证码
        String code = NumberUtils.generateCode(6);

        //发送验证码
        smsUtils.sendSms(phone, code, pwdSmsProperties.getSignName(), pwdSmsProperties.getVerifyCodeTemplate());
        //将验证码储存到redis中
        this.stringRedisTemplate.opsForValue().set(UPDATEPWD_KEY_PREFIX + phone, code, 5, TimeUnit.MINUTES);
    }

    @Transactional
    @Override
    public void register(User user, String code) {
        zc.free.acg.model.User userDao = new zc.free.acg.model.User();
        log.info("注册功能请求参数：User-" + user);
        log.info("注册功能请求参数：用户输入验证码-" + code);

        // 查询redis中的验证码
        String verifyCode = this.stringRedisTemplate.opsForValue().get(REGISTER_KEY_PREFIX + user.getPhone());
        // 校验验证码
        if (!StringUtils.equals(verifyCode, code)) {
            throw new RuntimeException("验证码错误");
        }
        // 生成salt
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);

        // 加salt加密
        user.setPassword(CodecUtils.BCryptPasswordEncoder(user.getPassword()));


        // 新增用户
        user.setId(null);
        user.setCreated(new Date());


        this.userMapper.insertSelective(userDao);
    }

    @Override
    public void updatePwd(User user, String code) {
        // 查询redis中的验证码
        String verifyCode = this.stringRedisTemplate.opsForValue().get(UPDATEPWD_KEY_PREFIX + user.getPhone());
        // 校验验证码
        if (!StringUtils.equals(verifyCode, code)) {
            throw new RuntimeException("验证码错误");
        }
        System.out.println("相同");

        //对密码加密
        user.setPassword(CodecUtils.BCryptPasswordEncoder(user.getPassword()));
        //初始化example对象
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("phone", user.getPhone());
        // 修改密码
        this.userMapper.updateByExampleSelective(user, example);

    }

    @Override
    public Boolean queryUserIsForkExhibition(Integer id, Integer exId) {
        int count = this.userMapper.selectUserIsForkExhibition(id,exId);
        if (count == 0) {
            return false;
        } else if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void ForkExhibition(Integer id, Integer exId) {
        this.userMapper.ForkExhibition(id,exId);
    }

    @Override
    public User queryUser(String username, String password) {
        User record = new User();
        record.setUsername(username);
        User user = this.userMapper.selectOne(record);
        //判断用户是否为空
        if (user == null) {
            return null;
        }
        //读取salt 对用户输入的密码加盐加密
        password = CodecUtils.md5Hex(password, user.getSalt());
        //对密码进行判断

        if (StringUtils.equals(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User queryUserById(Integer id) {
        if (id <= 0) {
            return null;
        }
        User user = this.userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Transactional
    @Override
    public void starUser(UserFollower userFollower) {
        List<UserFollower> select = this.userFollowerMapper.select(userFollower);
        if (select == null || select.size() == 0) {
            this.userFollowerMapper.insertSelective(userFollower);
            UserFollowed record = new UserFollowed();
            record.setUserId(userFollower.getFollowerUser());
            record.setFollowedUser(userFollower.getUserId());
            this.userFollowedMapper.insertSelective(record);
        } else {
            //更新关注表
            Example example = new Example(UserFollower.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId", userFollower.getUserId());
            criteria.andEqualTo("followerUser", userFollower.getFollowerUser());
            UserFollower record = new UserFollower();
            userFollower.setStatus(1);
            this.userFollowerMapper.updateByExampleSelective(userFollower,example);
            //更新粉丝表
            example = new Example(UserFollowed.class);
            criteria = example.createCriteria();
            criteria.andEqualTo("userId", userFollower.getFollowerUser());
            criteria.andEqualTo("followedUser", userFollower.getUserId());
            UserFollowed userFollowed = new UserFollowed();
            userFollowed.setStatus(1);
            this.userFollowedMapper.updateByExampleSelective(userFollowed,example);
        }

    }

    @Transactional
    @Override
    public void cancelStarUser(Integer id, Integer targetUser) {
        //更新关注表
        Example example = new Example(UserFollower.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", id);
        criteria.andEqualTo("followerUser", targetUser);
        UserFollower userFollower = new UserFollower();
        userFollower.setStatus(0);
        this.userFollowerMapper.updateByExampleSelective(userFollower,example);
        //更新粉丝表
        example = new Example(UserFollowed.class);
        criteria = example.createCriteria();
        criteria.andEqualTo("userId", targetUser);
        criteria.andEqualTo("followedUser", id);
        UserFollowed userFollowed = new UserFollowed();
        userFollowed.setStatus(0);
        this.userFollowedMapper.updateByExampleSelective(userFollowed,example);
    }


    @Override
    public Integer queryFollowerCount(Integer userId) {
        //初始化example对象
        Example example = new Example(UserFollower.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        //根据userId查询数量 或者根据首字母查询
        return this.userFollowerMapper.selectCountByExample(example);
    }

    @Override
    public List<User> queryFollowers(Integer userId) {
        return this.userMapper.findFollowerUsersById(userId);
    }

    @Override
    public Integer queryFollowedCount(Integer userId) {
        //初始化example对象
        Example example = new Example(UserFollowed.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        //根据userId查询数量 或者根据首字母查询
        return this.userFollowedMapper.selectCountByExample(example);
    }

    @Override
    public List<User> queryFolloweds(Integer userId) {
        return this.userMapper.findFollowedUsersById(userId);
    }

    @Override
    public PageResult<User> queryFollowedsByPage(Integer userId, Integer page, Integer cows) {
        //添加分页条件
        PageHelper.startPage(page,cows);
        // 查询结果
        List<User> userFollowers = this.userMapper.findFollowedUsersById(userId);
        //包装成pageinfo
        PageInfo<User> pageInfo = new PageInfo<>(userFollowers);
        //包装成分页结果
        long total = pageInfo.getTotal();
        //总记录页数
        int totalPage = 0;
        if ((total % cows) == 0) {
            totalPage = (int) (total / cows);
        } else {
            totalPage = (int) (total / cows + 1);
        }

        return new PageResult<>(pageInfo.getTotal(),totalPage ,pageInfo.getList());
    }

    @Override
    public PageResult<User> queryUserByKey(String key, Integer page, Integer cows) {
        //初始化example对象
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        //根据name模糊查询
        if(StringUtils.isNotBlank(key)) {
            criteria.andLike("username", "%"+key +"%");
        }

        //添加分页条件
        PageHelper.startPage(page,cows);

        List<User> users = this.userMapper.selectByExample(example);
        //包装成pageinfo
        PageInfo<User> pageInfo = new PageInfo<>(users);
        //包装成分页结果
        long total = pageInfo.getTotal();
        //总记录数
        //总记录页数
        int totalPage = 0;
        if ((total % cows) == 0) {
            totalPage = (int) (total / cows);
        } else {
            totalPage = (int) (total / cows + 1);
        }

        return new PageResult<>(pageInfo.getTotal(),totalPage ,pageInfo.getList());
    }

    @Override
    public String uploadImg(Integer id, MultipartFile file) {
        String url = this.uploadService.uploadImage(file);
        User record = new User();
        record.setId(id);
        record.setImage(url);
        this.userMapper.updateByPrimaryKeySelective(record);
        return url;
    }

    @Override
    public void updateUserInfo(User user) {
        this.userMapper.updateByPrimaryKeySelective(user);
    }



    @Override
    public PageResult<User> queryFollowersByPage(Integer userId, Integer page, Integer cows) {
        //添加分页条件
        PageHelper.startPage(page,cows);
        // 查询结果
        List<User> userFollowers = this.userMapper.findFollowerUsersById(userId);
        //包装成pageinfo
        PageInfo<User> pageInfo = new PageInfo<>(userFollowers);
        //包装成分页结果
        long total = pageInfo.getTotal();
        //总记录页数
        int totalPage = 0;
        if ((total % cows) == 0) {
            totalPage = (int) (total / cows);
        } else {
            totalPage = (int) (total / cows + 1);
        }

        return new PageResult<>(pageInfo.getTotal(),totalPage ,pageInfo.getList());
    }

    public static void main(String[] args) {
        long i = 1;
        System.out.println(i == 1);
    }

}
