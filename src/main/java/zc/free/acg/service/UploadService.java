package zc.free.acg.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    /**
     * 上传图片文件 返回图片路径
     * @param file
     * @return
     */
    String uploadImage(MultipartFile file);

    /**
     * 上传任意文件 返回路径
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);
}
