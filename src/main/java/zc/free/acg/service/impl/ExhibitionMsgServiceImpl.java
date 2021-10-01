//package zc.free.acg.service.impl;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import tk.mybatis.mapper.entity.Example;
//import zc.free.acg.domain.*;
//import zc.free.acg.service.ExhibitionMsgService;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @auther ZhengCong
// * @date 2020/4/7 22:07
// */
//@Service
//public class ExhibitionMsgServiceImpl implements ExhibitionMsgService {
//    @Autowired
//    private ExhibitionMsgMapper exhibitionMsgMapper;
//    @Override
//    public void addExhibitionMsg(ExhibitionMsg exhibitionMsg) {
//        exhibitionMsg.setCreateAt(new Date());
//        this.exhibitionMsgMapper.insertSelective(exhibitionMsg);
//    }
//
//    @Override
//    public List<ExhibitionMsg> queryExhibitionMsgs(Integer id) {
//        //初始化example对象
//        Example example = new Example(ExhibitionMsg.class);
//        Example.Criteria criteria = example.createCriteria();
//        if (id != null && id >0) {
//            criteria.andEqualTo("exId", id);
//        }
//        List<ExhibitionMsg> msgs = this.exhibitionMsgMapper.selectByExample(example);
//        return msgs;
//    }
//
//    @Override
//    public List<ExhibitionMsgBo> queryExhibitionMsgsAndMsgOwnerByExhibitionId(Integer id) {
//        List<ExhibitionMsgBo> msgs = this.exhibitionMsgMapper.selectExhibitionMsgsAndMsgOwnerByExhibitionId(id);
//        return msgs;
//    }
//
//    @Override
//    public PageResult<ExhibitionMsgBo> queryExhibitionMsgsAndMsgOwnerByExhibitionIdWithPage(Integer id, Integer page, Integer cows) {
//        PageHelper.startPage(page, cows);
//        List<ExhibitionMsgBo> exhibitionMsgBos = this.exhibitionMsgMapper.selectExhibitionMsgsAndMsgOwnerByExhibitionId(id);
//
//        //包装成pageinfo
//        PageInfo<ExhibitionMsgBo> pageInfo = new PageInfo<>(exhibitionMsgBos);
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
//}
