package zc.free.acg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import zc.free.acg.domain.ExhibitionBo;
import zc.free.acg.domain.ExhibitionBo1;
import zc.free.acg.domain.PageResult;
import zc.free.acg.mapper.ExhibitionDetailsMapper;
import zc.free.acg.mapper.ExhibitionMapper;
import zc.free.acg.model.Exhibition;
import zc.free.acg.model.ExhibitionDetails;
import zc.free.acg.model.ExhibitionDetailsExample;
import zc.free.acg.model.ExhibitionExample;
import zc.free.acg.service.ExhibitionService;
import zc.free.acg.service.UploadService;
import zc.free.acg.util.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * @auther ZhengCong
 * @date 2020/4/7 17:37
 * 展会模块
 */
@Service
public class ExhibitionServiceImpl implements ExhibitionService {
    @Autowired
    private ExhibitionMapper exhibitionMapper;
    @Autowired
    private ExhibitionDetailsMapper exhibitionDetailsMapper;
    @Autowired
    private UploadService uploadService;

    @Override
    public void saveExhibition(Exhibition exhibition) {
        this.exhibitionMapper.insertSelective(exhibition);
    }

    @Override
    public PageResult<Exhibition> queryExhibitionsByPage(String key, String city, Integer page, Integer cows, String sortBy, Boolean desc) {
        //初始化example对象
        ExhibitionExample example = new ExhibitionExample();
        ExhibitionExample.Criteria criteria = example.createCriteria();

        //根据name模糊查询
        if(StringUtils.isNotBlank(key)) {
            criteria.andNameLike("%"+key +"%");
        }
        //根据城市条件查询
        if(StringUtils.isNotBlank(city)) {
            criteria.andNameLike( "%"+city +"%");
        }
        //添加分页条件
        PageHelper.startPage(page,cows);
        //添加排序条件
        if(StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy +" " + (desc ? "desc": "asc"));
        }
        List<Exhibition> exhibitions = this.exhibitionMapper.selectByExample(example);
        //包装成pageinfo
        PageInfo<Exhibition> pageInfo = new PageInfo<>(exhibitions);
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
    public PageResult<ExhibitionBo1> queryExhibitionsAndForkNumByPage(String key, String city, Integer page, Integer cows, String sortBy, Boolean desc) {
        key = "%"+ key +"%";
        //添加分页条件
        PageHelper.startPage(page,cows);
        //查询
        ExhibitionExample exhibitionExample = new ExhibitionExample();
        ExhibitionExample.Criteria criteria = exhibitionExample.createCriteria();
        criteria.andNameEqualTo(key);
        List<Exhibition> exhibitionss = this.exhibitionMapper.selectByExample(exhibitionExample);
        List<ExhibitionBo1> exhibitionBo1s = BeanUtils.listbean2ListBean(exhibitionss, ExhibitionBo1.class);
        //包装成pageinfo
        PageInfo<ExhibitionBo1> pageInfo = new PageInfo<>(exhibitionBo1s);
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
    public ExhibitionDetails queryExhibitionDetailsById(Integer id) {
        ExhibitionDetails details = this.exhibitionDetailsMapper.selectByPrimaryKey(id);
        return details;
    }

    @Override
    public Exhibition queryExhibitionById(Integer id) {
        Exhibition exhibition = this.exhibitionMapper.selectByPrimaryKey(id);
        return exhibition;
    }

    @Override
    public ExhibitionBo queryExhibitionAndOwnerNameById(Integer id) {
        Exhibition exhibition = this.exhibitionMapper.selectByPrimaryKey(id);
        ExhibitionBo exhibitionBo = BeanUtils.bean2Bean(exhibition, ExhibitionBo.class);
        return exhibitionBo;
    }

    @Override
    public ExhibitionBo1 queryExhibitionAndOwnerNameAndForkNumById(Integer id) {
        Exhibition exhibition = this.exhibitionMapper.selectByPrimaryKey(id);
        ExhibitionBo1 exhibitionBo1 = BeanUtils.bean2Bean(exhibition, ExhibitionBo1.class);
        return exhibitionBo1;
    }

    @Override
    public List<ExhibitionDetails> queryExhibitionDetailsByExId(Integer id) {
        ExhibitionDetailsExample exhibitionDetailsExample = new ExhibitionDetailsExample();
        ExhibitionDetailsExample.Criteria criteria = exhibitionDetailsExample.createCriteria();
        criteria.andExhibitionIdEqualTo(id);
        List<ExhibitionDetails> details = this.exhibitionDetailsMapper.selectByExample(exhibitionDetailsExample);
        return details;
    }

    @Transactional
    @Override
    public void saveExhibition(Exhibition exhibition, MultipartFile file) {
        // 1 上传展会海报
        String url = this.uploadService.uploadImage(file);
        exhibition.setImg(url);
        //保存展会信息
        this.exhibitionMapper.insertSelective(exhibition);
    }

    @Transactional
    @Override
    public void updateExhibition(Exhibition exhibition, MultipartFile file) {
        if (file != null) {
            // 1 上传展会海报
            String url = this.uploadService.uploadImage(file);
            exhibition.setImg(url);
        }
        //修改展会信息
        ExhibitionExample example = new ExhibitionExample();
        ExhibitionExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(exhibition.getId());
        this.exhibitionMapper.updateByExampleSelective(exhibition, example);
    }

    @Override
    public List<Exhibition> queryExhibitionByOwner(Integer ownerId) {
        ExhibitionExample exhibitionExample = new ExhibitionExample();
        ExhibitionExample.Criteria criteria = exhibitionExample.createCriteria();
        criteria.andOwnerEqualTo(ownerId);
        List<Exhibition> exhibitions = this.exhibitionMapper.selectByExample(exhibitionExample);
        return exhibitions;
    }

    @Override
    public PageResult<Exhibition> queryExhibitionsByUserIdWithPage(Integer id, Integer page, Integer cows, String sortBy, Boolean desc) {

        //初始化example对象
        ExhibitionExample example = new ExhibitionExample();
        ExhibitionExample.Criteria criteria = example.createCriteria();

        //查询条件
        criteria.andOwnerEqualTo(id);

        //添加分页条件
        PageHelper.startPage(page,cows);

        List<Exhibition> exhibitions = this.exhibitionMapper.selectByExample(example);
        //包装成pageinfo
        PageInfo<Exhibition> pageInfo = new PageInfo<>(exhibitions);
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
    public void saveExhibitionDetail(Integer exId, String content, String title) {
        ExhibitionDetails details = new ExhibitionDetails();
        details.setExhibitionId(exId);
        details.setContent(content);
        details.setTitle(title);
        details.setCreateAt(new Date());
        this.exhibitionDetailsMapper.insertSelective(details);
    }



}
