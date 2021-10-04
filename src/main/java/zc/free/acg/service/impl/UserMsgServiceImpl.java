package zc.free.acg.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import zc.free.acg.model.UserMsg;
import zc.free.acg.mapper.UserMsgMapper;
import zc.free.acg.model.UserMsgExample;
import zc.free.acg.service.UserMsgService;

import java.util.Date;
import java.util.List;

/**
 * @auther ZhengCong
 * @date 2020/4/7 21:51
 */
@Service
public class UserMsgServiceImpl implements UserMsgService {
    @Autowired
    private UserMsgMapper userMsgMapper;

    @Override
    public void addMsg(UserMsg userMsg) {
        userMsg.setCreateAt(new Date());
        this.userMsgMapper.insertSelective(userMsg);
    }

    @Override
    public List<UserMsg> queryMsgs(Integer id) {
        //初始化example对象
        UserMsgExample example = new UserMsgExample();
        UserMsgExample.Criteria criteria = example.createCriteria();
        if (id != null && id >0) {
            criteria.andUserIdEqualTo(id);
        }
        List<UserMsg> msgs = this.userMsgMapper.selectByExample(example);
        return msgs;
    }

}
