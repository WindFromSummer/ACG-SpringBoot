//package zc.free.acg.service.impl;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import zc.free.acg.domain.Cooperate;
//import zc.free.acg.domain.CooperateBo;
//import zc.free.acg.domain.PageResult;
//import zc.free.acg.service.CooperateService;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @auther ZhengCong
// * @date 2020/5/8 17:34
// */
//@Service
//public class CooperateServiceImpl implements CooperateService {
//    @Autowired
//    private CooperateMapper cooperateMapper;
//
//    @Override
//    public void publishCooperate(Cooperate cooperate) {
//        cooperate.setCreateAt(new Date());
//        this.cooperateMapper.insertSelective(cooperate);
//    }
//
//    @Override
//    public PageResult<CooperateBo> queryCooperateByPage(String key, Integer page, Integer cows) {
//        //添加分页条件
//        PageHelper.startPage(page, cows);
//        // 查询结果
//        key = "%"+ key + "%";
//        List<CooperateBo> cooperateBos = this.cooperateMapper.selectByKeyWithPage(key);
//        //包装成pageinfo
//        PageInfo<CooperateBo> pageInfo = new PageInfo<>(cooperateBos);
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
//        PageResult<CooperateBo> cooperateBoPageResult = new PageResult<>(pageInfo.getTotal(), totalPage, pageInfo.getList());
//        return cooperateBoPageResult;
//    }
//
//
//}
