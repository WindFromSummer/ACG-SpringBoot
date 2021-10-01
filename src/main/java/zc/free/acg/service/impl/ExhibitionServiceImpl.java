//package zc.free.acg.service.impl;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.multipart.MultipartFile;
//import tk.mybatis.mapper.entity.Example;
//import zc.free.acg.domain.*;
//import zc.free.acg.service.ExhibitionService;
//import zc.free.acg.service.UploadService;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * @auther ZhengCong
// * @date 2020/4/7 17:37
// * 展会模块
// */
//@Service
//public class ExhibitionServiceImpl implements ExhibitionService {
//    @Autowired
//    private ExhibitionMapper exhibitionMapper;
//    @Autowired
//    private ExhibitionDetailsMapper exhibitionDetailsMapper;
//    @Autowired
//    private UploadService uploadService;
//
//    @Override
//    public void saveExhibition(Exhibition exhibition) {
//        this.exhibitionMapper.insertSelective(exhibition);
//    }
//
//    @Override
//    public PageResult<Exhibition> queryExhibitionsByPage(String key, String city, Integer page, Integer cows, String sortBy, Boolean desc) {
//        //初始化example对象
//        Example example = new Example(Exhibition.class);
//        Example.Criteria criteria = example.createCriteria();
//
//        //根据name模糊查询
//        if(StringUtils.isNotBlank(key)) {
//            criteria.andLike("name", "%"+key +"%");
//        }
//        //根据城市条件查询
//        if(StringUtils.isNotBlank(city)) {
//            criteria.andLike("name", "%"+city +"%");
//        }
//        //添加分页条件
//        PageHelper.startPage(page,cows);
//        //添加排序条件
//        if(StringUtils.isNotBlank(sortBy)) {
//            example.setOrderByClause(sortBy +" " + (desc ? "desc": "asc"));
//        }
//        List<Exhibition> exhibitions = this.exhibitionMapper.selectByExample(example);
//        //包装成pageinfo
//        PageInfo<Exhibition> pageInfo = new PageInfo<>(exhibitions);
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
//    public PageResult<ExhibitionBo1> queryExhibitionsAndForkNumByPage(String key, String city, Integer page, Integer cows, String sortBy, Boolean desc) {
//        key = "%"+ key +"%";
//        //添加分页条件
//        PageHelper.startPage(page,cows);
//        //查询
//        List<ExhibitionBo1> exhibitions = this.exhibitionMapper.selectExhibitionsAndForkNumByPage(key);
//        //包装成pageinfo
//        PageInfo<ExhibitionBo1> pageInfo = new PageInfo<>(exhibitions);
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
//    public ExhibitionDetails queryExhibitionDetailsById(Integer id) {
//        ExhibitionDetails details = this.exhibitionDetailsMapper.selectByPrimaryKey(id);
//        return details;
//    }
//
//    @Override
//    public Exhibition queryExhibitionById(Integer id) {
//        Exhibition exhibition = this.exhibitionMapper.selectByPrimaryKey(id);
//        return exhibition;
//    }
//
//    @Override
//    public ExhibitionBo queryExhibitionAndOwnerNameById(Integer id) {
//        ExhibitionBo exhibitionBo = this.exhibitionMapper.selectExhibitionAndOwnerNameById(id);
//        return exhibitionBo;
//    }
//
//    @Override
//    public ExhibitionBo1 queryExhibitionAndOwnerNameAndForkNumById(Integer id) {
//        return this.exhibitionMapper.selectExhibitionAndOwnerNameAndForkNumById(id);
//    }
//
//    @Override
//    public List<ExhibitionDetails> queryExhibitionDetailsByExId(Integer id) {
//        ExhibitionDetails record = new ExhibitionDetails();
//        record.setExhibitionId(id);
//        List<ExhibitionDetails> details = this.exhibitionDetailsMapper.select(record);
//        return details;
//    }
//
//    @Transactional
//    @Override
//    public void saveExhibition(Exhibition exhibition, MultipartFile file) {
//        // 1 上传展会海报
//        String url = this.uploadService.uploadImage(file);
//        exhibition.setImg(url);
//        //保存展会信息
//        this.exhibitionMapper.insertSelective(exhibition);
//    }
//
//    @Transactional
//    @Override
//    public void updateExhibition(Exhibition exhibition, MultipartFile file) {
//        if (file != null) {
//            // 1 上传展会海报
//            String url = this.uploadService.uploadImage(file);
//            exhibition.setImg(url);
//        }
//        //修改展会信息
//        Example example = new Example(Exhibition.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("id",exhibition.getId());
//        this.exhibitionMapper.updateByExampleSelective(exhibition, example);
//    }
//
//    @Override
//    public List<Exhibition> queryExhibitionByOwner(Integer ownerId) {
//        Exhibition record = new Exhibition();
//        record.setOwner(ownerId);
//        List<Exhibition> exhibitions = this.exhibitionMapper.select(record);
//        return exhibitions;
//    }
//
//    @Override
//    public PageResult<Exhibition> queryExhibitionsByUserIdWithPage(Integer id, Integer page, Integer cows, String sortBy, Boolean desc) {
//
//        //初始化example对象
//        Example example = new Example(Exhibition.class);
//        Example.Criteria criteria = example.createCriteria();
//
//        //查询条件
//        criteria.andEqualTo("owner",id);
//
//        //添加分页条件
//        PageHelper.startPage(page,cows);
//
//        List<Exhibition> exhibitions = this.exhibitionMapper.selectByExample(example);
//        //包装成pageinfo
//        PageInfo<Exhibition> pageInfo = new PageInfo<>(exhibitions);
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
//    public void saveExhibitionDetail(Integer exId, String content, String title) {
//        ExhibitionDetails details = new ExhibitionDetails();
//        details.setExhibitionId(exId);
//        details.setContent(content);
//        details.setTitle(title);
//        details.setCreateAt(new Date());
//        this.exhibitionDetailsMapper.insertSelective(details);
//    }
//
//
//
//}
