package zc.free.acg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import zc.free.acg.domain.IdentityBo;
import zc.free.acg.domain.PageResult;
import zc.free.acg.domain.UserIdentityApplyBo;
import zc.free.acg.mapper.IdentityMapper;
import zc.free.acg.mapper.UserIdentityApplyMapper;
import zc.free.acg.mapper.UserIdentityMapper;
import zc.free.acg.model.*;
import zc.free.acg.service.IdentityService;
import zc.free.acg.service.UploadService;
import zc.free.acg.util.BeanUtils;
import zc.free.acg.util.Util;

import java.util.Date;
import java.util.List;

/**
 * @auther ZhengCong
 * @date 2020/3/29 22:39
 */
@Service
public class IdentityServiceImpl implements IdentityService {
    @Autowired
    private IdentityMapper identityMapper;
    @Autowired
    private UserIdentityApplyMapper userIdentityApplyMapper;
    @Autowired
    private UserIdentityMapper userIdentityMapper;
    @Autowired
    private UploadService uploadService;

    @Override
    public int addIdentity(String name) {
        //新增身份
        // 查询身份是否存在
        Identity record = queryIdentityByName(name);
        if (record != null) {
            return -1;
        } else {
            record = new Identity();
            record.setName(name);
            record.setCreateAt(new Date());
            record.setUpdateAt(new Date());
            // 增加身份
            int count = this.identityMapper.insertSelective(record);
            return count;
        }

    }

    @Override
    public List<Identity> queryIdentitys() {
        IdentityExample identityExample = new IdentityExample();
        IdentityExample.Criteria criteria = identityExample.createCriteria();
        List<Identity> identities = this.identityMapper.selectByExample(identityExample);
        return identities;
    }

    @Override
    public Identity queryIdentityByName(String name) {
        IdentityExample identityExample = new IdentityExample();
        IdentityExample.Criteria criteria = identityExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<Identity> identities = this.identityMapper.selectByExample(identityExample);
        if (Util.isNullOrEmpty(identities)) {
            return null;
        } else {
            return identities.get(0);
        }
    }

    @Override
    public void applyIdentity(UserIdentityApply applyInfo) {
        applyInfo.setApplyAt(new Date());
        byte status = 0;
        applyInfo.setApplyState(status);
        this.userIdentityApplyMapper.insertSelective(applyInfo);
    }
    @Transactional
    @Override
    public void applyIdentity(UserIdentityApply applyInfo, MultipartFile file) {
        applyInfo.setApplyAt(new Date());
        byte status = 0;
        applyInfo.setApplyState(status);
        String url = this.uploadService.uploadFile(file);
        applyInfo.setContent(url);
        this.userIdentityApplyMapper.insertSelective(applyInfo);
    }

    @Override
    public List<UserIdentityApply> queryApply() {
        //初始化example对象
        UserIdentityApplyExample example = new UserIdentityApplyExample();
        UserIdentityApplyExample.Criteria criteria = example.createCriteria();
        byte applyState = 0;
        criteria.andApplyStateEqualTo(applyState);
        //查询未审核请求
        List<UserIdentityApply> userIdentityApplies = this.userIdentityApplyMapper.selectByExample(example);
        return userIdentityApplies;
    }

    @Override
    public List<UserIdentityApplyBo> queryApplyAndIdentityName() {
        //初始化example对象
        UserIdentityApplyExample example = new UserIdentityApplyExample();
        UserIdentityApplyExample.Criteria criteria = example.createCriteria();
        byte status = 0;
        criteria.andApplyStateEqualTo(status);
        //查询未审核请求
        List<UserIdentityApply> userIdentityApplies = this.userIdentityApplyMapper.selectByExample(example);
        List<UserIdentityApplyBo> userIdentityApplyBos = BeanUtils.listbean2ListBean(userIdentityApplies, UserIdentityApplyBo.class);
        return userIdentityApplyBos;
    }

    @Transactional
    @Override
    public void updateApply(UserIdentityApply userIdentityApply) {
        //如果审核通过
        if (userIdentityApply.getApplyState() == 2) {
            //添加信息到用户身份表
            UserIdentity record = new UserIdentity();
            record.setUserId(userIdentityApply.getUserId());
            record.setIdentityId(userIdentityApply.getIdentityId());
            record.setCreateAt(new Date());
            record.setUpdateAt(new Date());
            this.userIdentityMapper.insertSelective(record);
        }
        //修改用户身份审核表信息
        this.userIdentityApplyMapper.updateByPrimaryKeySelective(userIdentityApply);
    }

    @Override
    public List<IdentityBo> queryIdentityById(Integer id) {
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setUserId(id);
        UserIdentityExample userIdentityExample = new UserIdentityExample();
        UserIdentityExample.Criteria criteria = userIdentityExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        List<UserIdentity> userIdentities = this.userIdentityMapper.selectByExample(userIdentityExample);
        List<IdentityBo> identityBos = BeanUtils.listbean2ListBean(userIdentities, IdentityBo.class);

        return identityBos;

    }

    @Override
    public PageResult<IdentityBo> queryIdentityByIdWithPage(Integer id, Integer page, Integer cows) {
        //添加分页条件
        PageHelper.startPage(page,cows);
        // 查询结果
        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setUserId(id);
        UserIdentityExample userIdentityExample = new UserIdentityExample();
        UserIdentityExample.Criteria criteria = userIdentityExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        List<UserIdentity> userIdentities = this.userIdentityMapper.selectByExample(userIdentityExample);
        List<IdentityBo> identityBos = BeanUtils.listbean2ListBean(userIdentities, IdentityBo.class);

        //包装成pageinfo
        PageInfo<IdentityBo> pageInfo = new PageInfo<>(identityBos);
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
    public List<UserIdentityApply> queryApplyByUserId(Integer id) {
        UserIdentityApplyExample userIdentityApplyExample = new UserIdentityApplyExample();
        UserIdentityApplyExample.Criteria criteria = userIdentityApplyExample.createCriteria();
        criteria.andUserIdEqualTo(id);

        return this.userIdentityApplyMapper.selectByExample(userIdentityApplyExample);
    }

    @Override
    public List<UserIdentityApplyBo> queryApplyWithIdentityNameByUserId(Integer id) {
        UserIdentityApplyExample userIdentityApplyExample = new UserIdentityApplyExample();
        UserIdentityApplyExample.Criteria criteria = userIdentityApplyExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        List<UserIdentityApply> userIdentityApplies = this.userIdentityApplyMapper.selectByExample(userIdentityApplyExample);
        List<UserIdentityApplyBo> userIdentityApplyBos = BeanUtils.listbean2ListBean(userIdentityApplies, UserIdentityApplyBo.class);
        return userIdentityApplyBos;
    }

    @Override
    public UserIdentityApply queryApplyById(Integer id) {
        UserIdentityApply userIdentityApply = this.userIdentityApplyMapper.selectByPrimaryKey(id);
        return userIdentityApply;
    }

    @Override
    public UserIdentityApplyBo queryApplyAndIdentityNameById(Integer id) {
        UserIdentityApplyExample userIdentityApplyExample = new UserIdentityApplyExample();
        UserIdentityApplyExample.Criteria criteria = userIdentityApplyExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<UserIdentityApply> userIdentityApplies = this.userIdentityApplyMapper.selectByExample(userIdentityApplyExample);
        if (Util.isNullOrEmpty(userIdentityApplies) || userIdentityApplies.size() > 1) {
            return null;
        } else if (userIdentityApplies.size() == 1) {
            List<UserIdentityApplyBo> userIdentityApplyBos = BeanUtils.listbean2ListBean(userIdentityApplies, UserIdentityApplyBo.class);
            return userIdentityApplyBos.get(0);
        } else {
            return null;
        }

    }
}
