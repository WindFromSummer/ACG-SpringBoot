package zc.free.acg.service;

import org.springframework.web.multipart.MultipartFile;
import zc.free.acg.domain.*;

import java.io.File;
import java.util.List;

public interface IdentityService {
    /**
     * 新增身份
     * @param name
     * @return 返回新增结果 如果-1 则参数不合法 0 插入失败 1 插入陈宫
     */
    int addIdentity(String name);

    /**
     * 查询身份列表
     * @return
     */
    List<Identity> queryIdentitys();

    /**
     * 根据名称查询身份
     * @param name
     * @return
     */
    Identity queryIdentityByName(String name);

    /**
     * 申请身份认证
     * @param applyInfo
     */
    void applyIdentity(UserIdentityApply applyInfo);
    /**
     * 申请身份认证
     * @param applyInfo
     */
    void applyIdentity(UserIdentityApply applyInfo, MultipartFile file);

    /**
     * 查询状态为待审核的身份认证申请
     * @return
     */
    List<UserIdentityApply> queryApply();

    /**
     * 查询状态为待审核的身份认证申请并扩展携带身份名称信息
     * @return
     */
    List<UserIdentityApplyBo> queryApplyAndIdentityName();

    /**
     * 对身份认证申请进行回复
     * @param userIdentityApply
     */
    void updateApply(UserIdentityApply userIdentityApply);


    /**
     * 根据用户id查询用户用户的身份
     * @param id
     * @return
     */
    List<IdentityBo> queryIdentityById(Integer id);

    /**
     * 根据用户id分页查询身份信息
     * @param id
     * @param page
     * @param cow
     * @return
     */
    PageResult<IdentityBo> queryIdentityByIdWithPage(Integer id, Integer page, Integer cow);

    /**
     * 根据用户id查看该用户所有的身份审核记录
     * @param id
     * @return
     */
    List<UserIdentityApply> queryApplyByUserId(Integer id);

    /**
     * 根据用户id查看该用户所有的身份审核记录
     * @param id
     * @return
     */
    List<UserIdentityApplyBo> queryApplyWithIdentityNameByUserId(Integer id);

    /**
     * 根据id查询请求
     * @param id
     * @return
     */
    UserIdentityApply queryApplyById(Integer id);

    /**
     * 根据id查询请求并附加身份名称信息
     * @param id
     * @return
     */
    UserIdentityApplyBo queryApplyAndIdentityNameById(Integer id);
}
