package zc.free.acg.service;

import org.springframework.web.multipart.MultipartFile;
import zc.free.acg.domain.ExhibitionBo;
import zc.free.acg.domain.ExhibitionBo1;
import zc.free.acg.domain.PageResult;
import zc.free.acg.model.Exhibition;
import zc.free.acg.model.ExhibitionDetails;

import java.util.List;


public interface ExhibitionService {
    /**
     * 保存展会信息
     * @param exhibition
     */
    void saveExhibition(Exhibition exhibition);

    /**
     * 根据查询条件进行分页查询
     * @param key 名称 模糊匹配
     * @param city 城市
     * @param page 页码
     * @param cows 行数
     * @param sortBy 排序条件
     * @param desc 升降序
     * @return 展会分页结果集
     */
    PageResult<Exhibition> queryExhibitionsByPage(String key, String city, Integer page, Integer cows, String sortBy, Boolean desc);

    /**
     * 根据查询条件进行分页查询并附加展会想去人数
     * @param key
     * @param city
     * @param page
     * @param cows
     * @param sortBy
     * @param desc
     * @return
     */
    PageResult<ExhibitionBo1> queryExhibitionsAndForkNumByPage(String key, String city, Integer page, Integer cows, String sortBy, Boolean desc);

    /**
     * 根据展会id查询展会详情
     * @param id
     * @return
     */
    ExhibitionDetails queryExhibitionDetailsById(Integer id);

    /**
     * 根据展会id查询展会信息
     * @param id
     * @return
     */
    Exhibition queryExhibitionById(Integer id);

    /**
     * 根据展会id查询展会信息并查询展会开办人姓名
     * @param id
     * @return
     */
    ExhibitionBo queryExhibitionAndOwnerNameById(Integer id);

    /**
     * 根据展会id查询展会信息并查询展会开办人姓名和关注人数
     * @param id
     * @return
     */
    ExhibitionBo1 queryExhibitionAndOwnerNameAndForkNumById(Integer id);


    /**
     * 查询展会id查询展会的公告
     * @param id
     * @return
     */
    List<ExhibitionDetails> queryExhibitionDetailsByExId(Integer id);

    /**
     * 上传展会宣传图并保存展会信息
     * @param exhibition
     * @param file
     */
    void saveExhibition(Exhibition exhibition, MultipartFile file);

    /**
     * 查询用户发布的展会
     * @param ownerId
     * @return
     */
    List<Exhibition> queryExhibitionByOwner(Integer ownerId);

    /**
     * 分页查询用户发布的展会
     * @param id
     * @param page
     * @param cows
     * @param sortBy
     * @param desc
     * @return
     */
    PageResult<Exhibition> queryExhibitionsByUserIdWithPage(Integer id, Integer page, Integer cows, String sortBy, Boolean desc);

    /**
     * 添加展会
     * @param exId
     * @param content
     * @param title
     */
    void saveExhibitionDetail(Integer exId, String content, String title);

    /**
     * 修改展会信息
     * @param exhibition
     * @param file
     */
    void updateExhibition(Exhibition exhibition, MultipartFile file);


}


