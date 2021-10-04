package zc.free.acg.service.impl;

import com.luhuiguo.fastdfs.domain.StorePath;
import com.luhuiguo.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zc.free.acg.service.UploadService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private FastFileStorageClient storageClient;
    //文件类型为图片
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/gif","image/jpeg","image/png");

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Override
    public String uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        //校验文件脸型
        String contentType = file.getContentType();
        if(!CONTENT_TYPES.contains(contentType)) {
            LOGGER.info("文件类型不合法: {}", originalFilename);//{}为占位符
            return null;
        }
        //校验文件内容
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if(bufferedImage == null) {
                LOGGER.info("文件内容不合法: {}",originalFilename);
                return null;
            }
            //保存到服务器
//            file.transferTo(new File("E:\\workSpace\\exampleProject\\images\\" +originalFilename));
            //通过fastdfs保存
            //返回url 进行回显
//            return "/images/" + originalFilename;
            String extension = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            return "http://120.27.212.247:8888/" + storePath.getFullPath();
            //通过nginx反向代理
//        return "http://images.leyou.com/" + originalFilename;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        //校验文件脸型
        String contentType = file.getContentType();
        //校验文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            if (bufferedReader == null) {
                LOGGER.info("文件内容不合法: {}",originalFilename);
                return null;
            }
            String extension = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), extension, null);
            return "http://120.27.212.247:8888/" + storePath.getFullPath();
            //通过nginx反向代理
//        return "http://images.leyou.com/" + originalFilename;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
