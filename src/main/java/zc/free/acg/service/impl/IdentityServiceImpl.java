//package zc.free.acg.service.impl;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//import tk.mybatis.mapper.entity.Example;
//import zc.free.acg.domain.*;
//import zc.free.acg.service.IdentityService;
//import zc.free.acg.service.UploadService;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @auther ZhengCong
// * @date 2020/3/29 22:39
// */
//@Service
//public class IdentityServiceImpl implements IdentityService {
//    @Autowired
//    private IdentityMapper identityMapper;
//    @Autowired
//    private UserIdentityApplyMapper userIdentityApplyMapper;
//    @Autowired
//    private UserIdentityMapper userIdentityMapper;
//    @Autowired
//    private UploadService uploadService;
//
//    @Override
//    public int addIdentity(String name) {
//        //新增身份
//        // 查询身份是否存在
//        Identity record = queryIdentityByName(name);
//        if (record != null) {
//           return -1;
//        } else {
//            record = new Identity();
//            record.setName(name);
//            record.setCreateAt(new Date());
//            record.setUpdateAt(new Date());
//            // 增加身份
//            int count = this.identityMapper.insertSelective(record);
//            return count;
//        }
//
//    }
//
//    @Override
//    public List<Identity> queryIdentitys() {
//        List<Identity> identities = this.identityMapper.selectAll();
//        return identities;
//    }
//
//    @Override
//    public Identity queryIdentityByName(String name) {
//        Identity record = new Identity();
//        record.setName(name);
//        Identity identity = this.identityMapper.selectOne(record);
//        return identity;
//    }
//
//    @Override
//    public void applyIdentity(UserIdentityApply applyInfo) {
//        applyInfo.setApplyAt(new Date());
//        applyInfo.setApplyState(0);
//        this.userIdentityApplyMapper.insertSelective(applyInfo);
//    }
//    @Transactional
//    @Override
//    public void applyIdentity(UserIdentityApply applyInfo, MultipartFile file) {
//        applyInfo.setApplyAt(new Date());
//        applyInfo.setApplyState(0);
//        String url = this.uploadService.uploadFile(file);
//        applyInfo.setContent(url);
//        this.userIdentityApplyMapper.insertSelective(applyInfo);
//    }
//
//    @Override
//    public List<UserIdentityApply> queryApply() {
//        //初始化example对象
//        Example example = new Example(UserIdentityApply.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("applyState", 0);
//        //查询未审核请求
//        List<UserIdentityApply> userIdentityApplies = this.userIdentityApplyMapper.selectByExample(example);
//        return userIdentityApplies;
//    }
//
//    @Override
//    public List<UserIdentityApplyBo> queryApplyAndIdentityName() {
//        //初始化example对象
//        Example example = new Example(UserIdentityApply.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("applyState", 0);
//        //查询未审核请求
//        List<UserIdentityApplyBo> userIdentityApplies = this.userIdentityApplyMapper.selectIdentityApplyAndIdentityName();
//        return userIdentityApplies;
//    }
//
//    @Transactional
//    @Override
//    public void updateApply(UserIdentityApply userIdentityApply) {
//        //如果审核通过
//        if (userIdentityApply.getApplyState() == 2) {
//            //添加信息到用户身份表
//            UserIdentity record = new UserIdentity();
//            record.setUserId(userIdentityApply.getUserId());
//            record.setIdentityId(userIdentityApply.getIdentityId());
//            record.setCreateAt(new Date());
//            record.setUpdateAt(new Date());
//            this.userIdentityMapper.insertSelective(record);
//        }
//        //修改用户身份审核表信息
//        this.userIdentityApplyMapper.updateByPrimaryKeySelective(userIdentityApply);
//    }
//
//    @Override
//    public List<IdentityBo> queryIdentityById(Integer id) {
//        UserIdentity userIdentity = new UserIdentity();
//        userIdentity.setUserId(id);
//        List<IdentityBo> userIdentities = this.userIdentityMapper.findIdentitiesByUserId(id);
//        System.out.println(userIdentities);
//        return userIdentities;
//
//    }
//
//    @Override
//    public PageResult<IdentityBo> queryIdentityByIdWithPage(Integer id, Integer page, Integer cows) {
//        //添加分页条件
//        PageHelper.startPage(page,cows);
//        // 查询结果
//        UserIdentity userIdentity = new UserIdentity();
//        userIdentity.setUserId(id);
//        List<IdentityBo> userIdentities = this.userIdentityMapper.findIdentitiesByUserId(id);
//
//        //包装成pageinfo
//        PageInfo<IdentityBo> pageInfo = new PageInfo<>(userIdentities);
//        //包装成分页结果
//        long total = pageInfo.getTotal();
//        //总记录页数
//        int totalPage = 0;
//        if ((total % cows) == 0) {
//            totalPage = (int) (total / cows);
//        } else {
//            totalPage = (int) (total / cows + 1);
//        }
//
//        return new PageResult<>(pageInfo.getTotal(),totalPage ,pageInfo.getList());
//    }
//
//    @Override
//    public List<UserIdentityApply> queryApplyByUserId(Integer id) {
//        UserIdentityApply record = new UserIdentityApply();
//        record.setUserId(id);
//
//        return this.userIdentityApplyMapper.select(record);
//    }
//
//    @Override
//    public List<UserIdentityApplyBo> queryApplyWithIdentityNameByUserId(Integer id) {
//        List<UserIdentityApplyBo> result = this.userIdentityApplyMapper.selectApplyWithIdentityNameByUserId(id);
//        return result;
//    }
//
//    @Override
//    public UserIdentityApply queryApplyById(Integer id) {
//        UserIdentityApply userIdentityApply = this.userIdentityApplyMapper.selectByPrimaryKey(id);
//        return userIdentityApply;
//    }
//
//    @Override
//    public UserIdentityApplyBo queryApplyAndIdentityNameById(Integer id) {
//        UserIdentityApplyBo result = this.userIdentityApplyMapper.selectApplyWithIdentityNameById(id);
//        return result;
//    }
//}
