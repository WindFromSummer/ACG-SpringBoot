package zc.free.acg.util;

import com.alibaba.fastjson.JSON;
//import com.csii.pe.core.PeRuntimeException;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.dozer.DozerBeanMapper;
//import org.dozer.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public final class BeanUtils {
    /**
     * 日志记录
     */
//    private static Log log = LogFactory.getLog(CSIIJSON.class);
    public static final Logger log = LogManager.getLogger(BeanUtils.class);

    private BeanUtils() {
    }

    private static final String TRUE_FAG = "1";
    private static final String FALSE_FAG = "0";

//    static Mapper dozerMapper = new DozerBeanMapper();


    /**
     * 静态常量
     */
    private static ThreadLocal<Set> recurseBeanSet = new ThreadLocal() {
        @Override
        protected synchronized Set initialValue() {
            return new HashSet();
        }
    };

    /**
     * map转换成bean对象
     * @param map map
     * @param obj bean
     * @param <T> 泛化类
     * @return bean对象
     */
    public static <T> T map2Bean(Map map, T obj) {
        BeanWrapper bw = new BeanWrapperImpl(obj);
        PropertyDescriptor[] props = bw.getPropertyDescriptors();
        for (PropertyDescriptor pd : props) {
            String name = pd.getName();

            if ((!bw.isWritableProperty(name)) || (!bw.isReadableProperty(name))) {
                continue;
            }
            Class class0 = pd.getPropertyType();
            if (Enum.class.isAssignableFrom(class0)) {
                // 枚举首字母小写
                String convertedName = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                Object value = map.get(convertedName);

                if (value != null) {
                    if (value.getClass() == class0) {
                        bw.setPropertyValue(name, value);
                    } else {
                        String enumValue = String.valueOf(value);
                        if (enumValue.length() > 0) {
                            Enum v = Enum.valueOf(class0, enumValue);
                            bw.setPropertyValue(name, v);
                        }
                    }
                }
            } else {
                String convertedName = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                Object value = map.get(convertedName);

                if (value == null) {
                    continue;
                }

                if (value instanceof Map){
                    value = map2Bean((Map) value,class0);
                }
                bw.setPropertyValue(name, value);
            }

        }

        return (T) bw.getWrappedInstance();
    }

    /**
     * map转换成bean对象
     * @param map map
     * @param clazz 类对象
     * @param <T> 泛化对象
     * @return 泛化对象
     */
    public static <T> T map2Bean(Map map, Class<T> clazz) {
        if(clazz.isInstance(map)){
            return (T)map;
        }
        BeanWrapper bw = new BeanWrapperImpl(clazz);
        PropertyDescriptor[] props = bw.getPropertyDescriptors();
        for (PropertyDescriptor pd : props) {
            String name = pd.getName();

            if ((!bw.isWritableProperty(name)) || (!bw.isReadableProperty(name))) {
                continue;
            }
            Class class0 = pd.getPropertyType();
            if (Enum.class.isAssignableFrom(class0)) {
                // 命名规范：严格遵循驼峰命名
                String convertedName = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                Object value = map.get(convertedName);

                if (value != null) {
                    if (value.getClass() == class0) {
                        bw.setPropertyValue(name, value);
                    } else {
                        String enumValue = String.valueOf(value);
                        if (enumValue.length() > 0) {
                            Enum v = Enum.valueOf(class0, String.valueOf(value));
                            bw.setPropertyValue(name, v);
                        }
                    }
                }
            } else {
                String convertedName = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                Object value = map.get(convertedName);

                if (value == null) {
                    continue;
                }
                if (value instanceof Map) {
                    bw.setPropertyValue(name, map2Bean((Map) value, class0));
                } else if (class0.isAssignableFrom(List.class) && value instanceof List) {
                    List srcList = (List)value;
                    if (!srcList.isEmpty()) {
                        try {
                            Type type = bw.getWrappedClass().getDeclaredField(name).getGenericType();
                            if (!(type instanceof ParameterizedType)) {
                                bw.setPropertyValue(name, value);
                            } else {
                                ParameterizedType pt = (ParameterizedType)type;
                                Class ptClass = (Class)pt.getActualTypeArguments()[0];
                                List<T> target = new ArrayList();
                                if (srcList.get(0) instanceof Map) {
                                    target = listMap2ListBean(srcList, ptClass);
                                } else {
                                    Iterator srcIterator = srcList.iterator();

                                    while(srcIterator.hasNext()) {
                                        Object srcObj = srcIterator.next();
                                        ((List)target).add(BeanMapUtils.bean2Bean(srcObj, clazz));
                                    }
                                }

                                bw.setPropertyValue(name, target);
                            }
                        } catch (NoSuchFieldException ee) {
                            throw new RuntimeException("bean2map_fail", ee);
                        }
                    }
                }else if (value instanceof Boolean) {
                    Boolean b = (Boolean) value;
                    if (b) {
                        bw.setPropertyValue(name, TRUE_FAG);
                    } else {
                        bw.setPropertyValue(name, FALSE_FAG);
                    }
                } else if (value instanceof Enum) {
                    bw.setPropertyValue(name, ((Enum) value).name());
                } else {
                    bw.setPropertyValue(name, value);
                }
            }

        }

        return (T) bw.getWrappedInstance();
    }

    /**
     * map转换成bean对象
     * @param map map
     * @param clazz 类对象
     * @param <T> 泛化对象
     * @return 泛化对象
     */
    public static <T> T upperMap2Bean(Map map, Class<T> clazz) {
        BeanWrapper bw = new BeanWrapperImpl(clazz);
        PropertyDescriptor[] props = bw.getPropertyDescriptors();
        for (PropertyDescriptor pd : props) {
            String name = pd.getName();

            if ((!bw.isWritableProperty(name)) || (!bw.isReadableProperty(name))) {
                continue;
            }
            Class class0 = pd.getPropertyType();
            if (Enum.class.isAssignableFrom(class0)) {
                // 命名规范：严格遵循驼峰命名
                String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                Object value = map.get(convertedName);

                if (value != null) {
                    if (value.getClass() == class0) {
                        bw.setPropertyValue(name, value);
                    } else {
                        String enumValue = String.valueOf(value);
                        if (enumValue.length() > 0) {
                            Enum v = Enum.valueOf(class0, String.valueOf(value));
                            bw.setPropertyValue(name, v);
                        }
                    }
            }
            } else {
                String convertedName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
                Object value = map.get(convertedName);

                if (value == null) {
                    continue;
                }
                if (value instanceof Map) {
                    bw.setPropertyValue(name, map2Bean((Map) value, class0));
                } else if (class0.isAssignableFrom(List.class) && value instanceof List) {
                    List srcList = (List)value;
                    if (!srcList.isEmpty()) {
                        try {
                            Type type = bw.getWrappedClass().getDeclaredField(name).getGenericType();
                            if (!(type instanceof ParameterizedType)) {
                                bw.setPropertyValue(name, value);
                            } else {
                                ParameterizedType pt = (ParameterizedType)type;
                                Class ptClass = (Class)pt.getActualTypeArguments()[0];
                                List<T> target = new ArrayList();
                                if (srcList.get(0) instanceof Map) {
                                    target = listMap2ListBean(srcList, ptClass);
                                } else {
                                    Iterator srcIterator = srcList.iterator();

                                    while(srcIterator.hasNext()) {
                                        Object srcObj = srcIterator.next();
                                        ((List)target).add(bean2Bean(srcObj, clazz));
                                    }
                                }

                                bw.setPropertyValue(name, target);
                            }
                        } catch (NoSuchFieldException ee) {
                            throw new RuntimeException("bean2map_fail", ee);
                        }
                    }
                }else if (value instanceof Boolean) {
                    Boolean b = (Boolean) value;
                    if (b) {
                        bw.setPropertyValue(name, TRUE_FAG);
                    } else {
                        bw.setPropertyValue(name, FALSE_FAG);
                    }
                } else if (value instanceof Enum) {
                    bw.setPropertyValue(name, ((Enum) value).name());
                } else {
                    bw.setPropertyValue(name, value);
                }
            }

        }

        return (T) bw.getWrappedInstance();
    }

    /**
     * @param map
     * @param clazz
     * @return
     */
    public static Object map2WeakBean(Map map, Class clazz) {
        BeanWrapper bw = new BeanWrapperImpl(clazz);
        PropertyDescriptor[] props = bw.getPropertyDescriptors();
        for (PropertyDescriptor pd : props) {
            String name = pd.getName();

            if ((!bw.isWritableProperty(name)) || (!bw.isReadableProperty(name))) {
                continue;
            }
            Class class0 = pd.getPropertyType();
            if (Enum.class.isAssignableFrom(class0)) {
                String convertedName = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                Object value = map.get(convertedName);

                if (value != null) {
                    if (value.getClass() == class0) {
                        try {
                            bw.setPropertyValue(name, value);
                        } catch (Exception e) {
                            log.warn(e);
                        }
                    } else {
                        String enumValue = String.valueOf(value);
                        if (enumValue.length() > 0) {
                            Enum v = Enum.valueOf(class0, String.valueOf(value));
                            try {
                                bw.setPropertyValue(name, v);
                            } catch (Exception e) {
                                log.warn(e);
                            }

                        }
                    }
                }
            } else {
                String convertedName = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                Object value = map.get(convertedName);

                if (value == null) {
                    continue;
                }
                try {
                    bw.setPropertyValue(name, value);
                } catch (Exception e) {
                    log.warn(e);
                }

            }

        }

        return bw.getWrappedInstance();
    }

    /**
     * 将bean转化成map，map key，要求驼峰命名
     * @param beanObject
     * @return
     */
    public static Map bean2Map(Object beanObject) {
        BeanWrapperImpl bean = new BeanWrapperImpl(beanObject);
        PropertyDescriptor[] desc = bean.getPropertyDescriptors();
        Map dataMap = new HashMap(desc.length);
        try {
            for (int i = 0; i < desc.length; i++) {
                String name = desc[i].getName();
                if ((!bean.isWritableProperty(name)) || (!bean.isReadableProperty(name))) {
                    continue;
                }
                Object object = bean.getPropertyValue(name);
                if (object == null) {
                    continue;
                }
                // 命名规范：严格遵循驼峰命名
                String convertedName = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                dataMap.put(convertedName, object);
            }

            return dataMap;
        } catch (Exception e1) {
            throw new RuntimeException("pe.core.util.bean2map_fail", e1);
        }
    }

    /**
     * 将bean转化成map，map key，要求驼峰命名
     * @param beanObject
     * @return
     */
    public static Map bean2TreeMap(Object beanObject) {
        BeanWrapperImpl bean = new BeanWrapperImpl(beanObject);
        PropertyDescriptor[] desc = bean.getPropertyDescriptors();
        Map dataMap = new TreeMap();
        try {
            for (int i = 0; i < desc.length; i++) {
                String name = desc[i].getName();
                if ((!bean.isWritableProperty(name)) || (!bean.isReadableProperty(name))) {
                    continue;
                }
                Object object = bean.getPropertyValue(name);
                if (object == null) {
                    continue;
                }
                // 命名规范：严格遵循驼峰命名
                String convertedName = Character.toLowerCase(name.charAt(0)) + name.substring(1);
                dataMap.put(convertedName, object);
            }

            return dataMap;
        } catch (Exception e1) {
            throw new RuntimeException("pe.core.util.bean2map_fail", e1);
        }
    }

    /**
     * 将bean转化成map
     * @param beanObject
     * @return
     */
    public static Map bean2MapInternet(Object beanObject) {
        BeanWrapperImpl bean = new BeanWrapperImpl(beanObject);
        PropertyDescriptor[] desc = bean.getPropertyDescriptors();
        Map dataMap = new HashMap(desc.length);
        try {
            for (int i = 0; i < desc.length; i++) {
                String name = desc[i].getName();

                if ((!bean.isWritableProperty(name)) || (!bean.isReadableProperty(name))) {
                    continue;
                }
                Object object = bean.getPropertyValue(name);
                if (object == null) {
                    continue;
                }
                String convertedName = name.charAt(0) + name.substring(1);
                dataMap.put(convertedName, object);
            }

            return dataMap;
        } catch (Exception e1) {
            throw new RuntimeException("pe.core.util.bean2map_fail", e1);
        }
    }

    /**
     * liwx 20200120
     * 将bean list转化成list map<String, Object>
     * @param list list
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> listBean2ListMapStrOjb(List list) {
        List result = new ArrayList();

        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map tmp = bean2Map(it.next());
            result.add(tmp);
        }
        return result;
    }


    /**
     * 将bean list转化成list map
     * @param list list
     * @return list<Map>
     */
    public static List<Map> listBean2ListMap(List list) {
        List result = new ArrayList();

        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Map tmp = bean2Map(it.next());
            result.add(tmp);
        }
        return result;
    }

    /**
     * 将list map 转成 list bean
     * @param list
     * @param class0
     * @param <T>
     * @return
     */
    public static <T> List<T> listMap2ListBean(List list, Class<T> class0) {
        List result = new ArrayList();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Object t = map2Bean((Map) it.next(), class0);
            result.add(t);
        }
        return result;
    }


    /**
     * 将list bean 转成成另一个类型的 list bean
     * @param list
     * @param class0
     * @param <T>
     * @return
     */
    public static <T> List<T> listbean2ListBean(List<?> list, Class<T> class0) {
        List result = new ArrayList();
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            Object t = bean2Bean(it.next(), class0);
            result.add(t);
        }
        return result;
    }


    /**
     * @param beanObject
     * @return
     */
    public static Map bean2MapRecurse(Object beanObject) {
        Set set = recurseBeanSet.get();

        if (set.contains(beanObject)) {
            return null;
        }
        set.add(beanObject);

        BeanWrapperImpl bean = new BeanWrapperImpl(beanObject);
        PropertyDescriptor[] desc = bean.getPropertyDescriptors();
        Map dataMap = new HashMap(desc.length);
        try {
            String convertedName;
            Iterator it;
            for (int i = 0; i < desc.length; i++) {
                String name = desc[i].getName();

                if ((!bean.isWritableProperty(name)) || (!bean.isReadableProperty(name))) {
                    continue;
                }
                Object object = bean.getPropertyValue(name);
                if (object == null) {
                    continue;
                }
                convertedName = Character.toLowerCase(name.charAt(0)) + name.substring(1);

                Class class0 = object.getClass();
                if ((class0.getName().startsWith("java")) || (Enum.class.isAssignableFrom(class0))) {
                    dataMap.put(convertedName, object);
                } else {
                    Map subMap = bean2MapRecurse(object);
                    if (subMap == null) {
                        continue;
                    }
                    for (it = subMap.entrySet().iterator(); it.hasNext(); ) {
                        Map.Entry entry = (Map.Entry) it.next();

                        dataMap.put(convertedName + "_" + entry.getKey(), entry.getValue());
                    }

                }

            }
            Map map = dataMap;
            set.remove(beanObject);
            return map;
        } catch (Exception e1) {
            throw new RuntimeException("pe.core.util.bean2map_fail", e1);
        }

    }

    /**
     * 将源list bean 设置为目标 bean中的listPropName的属性
     * @param srcBeanObject 源list bean
     * @param destBeanObject 目标bean
     * @param listPropName 属性名称
     */
    public static void list2Bean(List<?> srcBeanObject, Object destBeanObject, String listPropName) {
        BeanWrapperImpl destBean = new BeanWrapperImpl(destBeanObject);
        destBean.setPropertyValue(listPropName, srcBeanObject);
    }

    /**
     * bean对象转换
     * @param srcBeanObject 源bean对象
     * @param class0 类类型
     * @param <T> 泛化对象
     * @return 泛化对象
     */
    public static <T> T bean2Bean(Object srcBeanObject, Class<T> class0) {
        try {
            Object t = class0.newInstance();
            if (srcBeanObject instanceof List) {
                list2Bean((List) srcBeanObject, t, "list");
            } else if(srcBeanObject instanceof Map){
                t = map2Bean(((Map) srcBeanObject), class0);
            }else {
                bean2Bean(srcBeanObject, t);
            }
            return (T) t;
        } catch (Exception e) {
            throw new RuntimeException("pe.core.util.bean2bean_fail", e);
        }
    }

    /**
     * bean对象转换
     * @param srcBeanObject 源对象
     * @param destBeanObject 目标对象
     */
    public static void bean2Bean(Object srcBeanObject, Object destBeanObject) {
        BeanWrapperImpl srcBean = new BeanWrapperImpl(srcBeanObject);
        BeanWrapperImpl destBean = new BeanWrapperImpl(destBeanObject);

        PropertyDescriptor[] destDesc = destBean.getPropertyDescriptors();
        try {
            for (int i = 0; i < destDesc.length; i++) {
                String name = destDesc[i].getName();

                if (!destBean.isWritableProperty(name)) {
                    continue;
                }
                if (!srcBean.isReadableProperty(name)) {
                    continue;
                }
                Object srcValue = srcBean.getPropertyValue(name);
                if (srcValue != null) {
                    if (srcValue instanceof Boolean) {
                        Boolean b = (Boolean) srcValue;
                        if (b) {
                            destBean.setPropertyValue(name, TRUE_FAG);
                        } else {
                            destBean.setPropertyValue(name, FALSE_FAG);
                        }
                    } else if (srcValue instanceof Enum) {
                        destBean.setPropertyValue(name, ((Enum) srcValue).name());
                    } else {
                        destBean.setPropertyValue(name, srcValue);
                    }

                }

            }

        } catch (Exception e1) {
            throw new RuntimeException("pe.core.util.bean2bean_fail", e1);
        }
    }

    /**
     * 使用dozerMapper转换嵌套的复杂对象
     * @param srcBeanObject
     * @param class0
     * @param <T>
     * @return
     */
//    public static <T> T dozerConvertBean(Object srcBeanObject, Class<T> class0){
//        return dozerMapper.map(srcBeanObject, class0);
//    }


    /**
     * @author wangpengfei
     * @param object 要强转的对象 , entityClass 强转后的类型
     * @return T
     * @descprition   对象类型强转
     * @version 1.0
     */
    public static <T> T convertBean(Object object, Class<T> entityClass) {
        if(null == object) {
            return null;
        }
        return JSON.parseObject(JSON.toJSONString(object), entityClass);
    }

    /**
     * @author yjz
     * @param target toSet
     * @param source fromGet
     * @descprition 取source值存到target一样属性名中(暂时不支持复杂类型)
     * tip: bean属性会直接覆盖,不会在深入解析
     */
    public static void addBean(Object target, Object source) {
        Map map = BeanUtils.bean2Map(source);
        try {
            PropertyDescriptor[] propertys = Introspector.getBeanInfo(target.getClass()).getPropertyDescriptors();
            Arrays.stream(propertys).forEach(property -> {
                try {
                    Object value = map.get(property.getName());
                    if(null!=value){
                        property.getWriteMethod().invoke(target, value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            log.error("转换失败,target:"+target+"\tsource:"+source);
            throw new RuntimeException("pe.core.util.addBean_fail", e);
        }
    }

    /**
     * @author yjz
     * @param source 字段类型String
     * @param clazz  targetClass
     * @param df
     * @param <T>    target
     * @return target
     * @descprition 取source值存到target一样属性名中(暂时不支持复杂类型)
     * tip: date类型需要特殊处理, 目前支持 有参构造(参数为String)
     */
    public static <T> T StringBeanToBean(Object source, Class<T> clazz, String df) {
        try {
            String dateFormatStr = Optional.ofNullable(df).orElse("yyyyMMdd");
            SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
            T target = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            Map<String, String> sourceMap = bean2Map(source);
            Arrays.stream(fields).filter(field -> {
                String value = sourceMap.get(field.getName());
                return !Util.isNullOrEmpty(value);
            }).forEach(field -> {
                try {
                    String key = field.getName();
                    Class<?> type = field.getType();
                    String value = sourceMap.get(key);
                    Object obj = Date.class == type ? dateFormat.parse(value) : type.getConstructor(String.class).newInstance(value);
                    String methodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                    Method setMethod = clazz.getMethod(methodName, type);
                    setMethod.invoke(target, obj);
                } catch (Exception e) {
                    log.error("convert to bean fail", e);
                    throw new RuntimeException("pe.core.util.StringBeanToBean", e);
                }
            });
            return target;
        } catch (Exception e) {
            log.error("convert to bean fail", e);
            throw new RuntimeException("pe.core.util.StringBeanToBean", e);
        }
    }


}
