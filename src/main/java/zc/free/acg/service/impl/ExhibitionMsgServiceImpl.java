package zc.free.acg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zc.free.acg.domain.ExhibitionMsg;
import zc.free.acg.domain.ExhibitionMsgBo;
import zc.free.acg.domain.PageResult;
import zc.free.acg.mapper.ExUserMsgMapper;
import zc.free.acg.model.ExUserMsg;
import zc.free.acg.model.ExUserMsgExample;
import zc.free.acg.service.ExhibitionMsgService;
import zc.free.acg.util.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * @auther ZhengCong
 * @date 2020/4/7 22:07
 */
@Service
public class ExhibitionMsgServiceImpl implements ExhibitionMsgService {
    @Autowired
    private ExUserMsgMapper exUserMsgMapper;
    @Override
    public void addExhibitionMsg(ExhibitionMsg exhibitionMsg) {
        ExUserMsg exUserMsg = BeanUtils.bean2Bean(exhibitionMsg, ExUserMsg.class);
        exUserMsg.setCreateAt(new Date());
        this.exUserMsgMapper.insertSelective(exUserMsg);
    }

    @Override
    public List<ExhibitionMsg> queryExhibitionMsgs(Integer id) {
        //初始化example对象
        ExUserMsgExample exUserMsgExample = new ExUserMsgExample();
        ExUserMsgExample.Criteria criteria = exUserMsgExample.createCriteria();
        if (id != null && id >0) {
            criteria.andExIdEqualTo(id);
        }
        List<ExUserMsg> exUserMsgs = this.exUserMsgMapper.selectByExample(exUserMsgExample);
        List<ExhibitionMsg> msgs = BeanUtils.listbean2ListBean(exUserMsgs, ExhibitionMsg.class);
        return msgs;
    }

    @Override
    public List<ExhibitionMsgBo> queryExhibitionMsgsAndMsgOwnerByExhibitionId(Integer id) {
        ExUserMsgExample exUserMsgExample = new ExUserMsgExample();
        ExUserMsgExample.Criteria criteria = exUserMsgExample.createCriteria();
        criteria.andExIdEqualTo(id);
        List<ExUserMsg> exUserMsgs = this.exUserMsgMapper.selectByExample(exUserMsgExample);
        List<ExhibitionMsgBo> msgs = BeanUtils.listbean2ListBean(exUserMsgs, ExhibitionMsgBo.class);
        return msgs;
    }

    @Override
    public PageResult<ExhibitionMsgBo> queryExhibitionMsgsAndMsgOwnerByExhibitionIdWithPage(Integer id, Integer page, Integer cows) {
        PageHelper.startPage(page, cows);
        ExUserMsgExample exUserMsgExample = new ExUserMsgExample();
        ExUserMsgExample.Criteria criteria = exUserMsgExample.createCriteria();
        criteria.andExIdEqualTo(id);
        List<ExUserMsg> exUserMsgs = this.exUserMsgMapper.selectByExample(exUserMsgExample);
        List<ExhibitionMsgBo> exhibitionMsgBos = BeanUtils.listbean2ListBean(exUserMsgs, ExhibitionMsgBo.class);

        //包装成pageinfo
        PageInfo<ExhibitionMsgBo> pageInfo = new PageInfo<>(exhibitionMsgBos);
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

}
