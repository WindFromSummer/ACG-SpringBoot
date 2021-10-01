package zc.free.acg.util;

import org.springframework.beans.BeanUtils;

/**
 * @author 杜义淙
 * @Title: BeanMapUtils
 * @Description: Bean
 * date 2019-10-16 13:14
 */
public final class BeanMapUtils {

    private BeanMapUtils() {
    }

    /**
     * bean类型转换
     * @param srcBeanObject 源bean
     * @param clazz 目标类
     * @param <T> 泛化类
     * @return 泛化类
     */
    public static <T> T bean2Bean(Object srcBeanObject, Class<T> clazz) {
        try {
            T target = clazz.newInstance();
            BeanUtils.copyProperties(srcBeanObject, target);
            return target;
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception.getMessage(), exception);
        }
    }
}
