package zc.free.acg.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: Util
 * @Description:
 * @author: 杜义淙 duyicong@csii.com.cn
 * @date: 2017年6月6日 上午11:14:26
 */
public class Util {
    private static Log log = LogFactory.getLog(Util.class);

    public static final int MAX_RECORD_COUNT = 2000;
    public static final String MAX_RECORD_COUNT_MSG = " 2000 ";
    private final static String ABC_SEED = "_CEBDigest200505231011";

    private static Pattern pattern = Pattern.compile("^\\d+$");

    public static boolean isNullOrEmpty(String inStr) {
        return (inStr == null || inStr.trim().length() == 0);
    }

    public static boolean isNullOrEmpty(List list) {
        return (list == null || list.size() == 0);
    }

    public static boolean isNullOrEmpty(Map map) {
        return (map == null || map.size() == 0);
    }


    //liwx 20210208 判断字符串中是否包含中文
    public static boolean isContainChinese(String str) {
        boolean flag = false;
        for(int i = 0; i<str.length(); i++){
            String temp = str.substring(i,i+1);
            flag = Pattern.matches("[\u4E00-\u9FA5]",temp);
            if(flag){
                return flag;
            }
        }
        return flag;
    }

    /**
     * 常见类型判空
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String) {
            return isNullOrEmpty((String) obj);
        } else if (obj instanceof List) {
            return isNullOrEmpty((List) obj);
        } else if (obj instanceof Map) {
            return isNullOrEmpty((Map) obj);
        } else if (obj instanceof Object[]) {
            Object[] o = (Object[]) obj;
            for (int i = 0; i < o.length; i++) {
                Object object = o[i];
                if (object instanceof Date) {
                    if (object.equals(new Date(0))) {
                        return true;
                    }
                } else if (Util.isNullOrEmpty(object)) {
                    return true;
                }
            }
        } else if (obj instanceof Date) {
            if (obj.equals(new Date(0))) {
                return true;
            }
        }

        return false;
    }

    /**
     * 根据当前日期计算出与当前日期间隔时间单位的日期
     *
     * @param currentDate  当前日期
     * @param dateUnit     时间单位
     * @param dateUnitType 滚动日期单位的类型
     * @return
     */
    public static Date rollDateByDateUnit(Date currentDate, int dateUnit, int dateUnitType) {
        Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
        if (currentDate != null) {
            calendar.setTime(currentDate);
        }
        calendar.add(dateUnitType, dateUnit);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 根据当前日期计算出与当前日期间隔天数的日期
     *
     * @param currentDate
     * @param day
     * @return
     */
    public static Date rollDateByDay(Date currentDate, int day) {
        Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
        if (currentDate != null) {
            calendar.setTime(currentDate);
        }
        calendar.add(Calendar.DATE, day);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 根据当前日期计算出与当前日期间隔周数的日期
     * @param currentDate 当前日期
     * @param week 周数
     * @return
     */
    public static Date rollDateByWeek(Date currentDate, int week) {
        Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
        if (currentDate != null) {
            calendar.setTime(currentDate);
        }
        calendar.add(Calendar.WEDNESDAY, week);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 根据当前日期计算出与当前日期间隔月数的日期
     * @param currentDate 当前日期
     * @param month 月份
     * @return
     */
    public static Date rollDateByMonth(Date currentDate, int month) {
        Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
        if (currentDate != null) {
            calendar.setTime(currentDate);
        }
        calendar.add(Calendar.MONTH, month);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 根据当前日期计算出与当前日期间隔年数的日期
     * @param currentDate 当前日期
     * @param year 年数
     * @return
     */
    public static Date rollDateByYear(Date currentDate, int year) {
        Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
        if (currentDate != null) {
            calendar.setTime(currentDate);
        }
        calendar.add(Calendar.YEAR, year);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 根据年月日生成日期对象
     * @param year 年份
     * @param month 月份
     * @param day 日期
     * @return 日期对象
     */
    public static Date toDate(int year, int month, int day) {
        return toDate(year, month, day, 0, 0, 0);
    }

    /**
     * @param year 年份
     * @param month 月份
     * @param day 日期
     * @param hour 小时
     * @param minute 分钟
     * @param second 秒数
     * @return 日期
     */
    public static Date toDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.set(year, month - 1, day, hour, minute, second);
        return new Date(c.getTimeInMillis());
    }

    private static int toDays(Date date) {
        int dayOffset = -1;
        Calendar c = Calendar.getInstance();
        Calendar timeZero = Calendar.getInstance();
        timeZero.setTimeInMillis(0);
        c.setTime(date);

        while (timeZero.get(Calendar.YEAR) < c.get(Calendar.YEAR)) {
            dayOffset += timeZero.getActualMaximum(Calendar.DAY_OF_YEAR);
            timeZero.add(Calendar.YEAR, 1);
        }

        dayOffset += c.get(Calendar.DAY_OF_YEAR);
        return dayOffset;
    }

    /**
     * 两个时间之前相差天数
     * @param date1 日期
     * @param date2 日期
     * @return 日期间隔天数
     */
    public int differByDay(Date date1, Date date2) {
        return (int) (toDays(date1) - toDays(date2));
    }


    /**
     * @author zhujf 电子银行渠道密码参数规则定义
     * （密码控制策略选项|最低长度｜最大长度｜密码构成规则｜首次登录是否修改｜有效日期｜允许失败次数|是否支持密码复杂度|发送短信|密码频繁错误次数|间隔时间（S））
     * 规则ID=模块渠道+密码类型+Passowrd
     * F|6|20|C|Y|0D|20|0|0|6|3600
     *//*
    public static void resolvePasswordPolicyRule(String ruleDef, PasswordPolicy passwordPolicy) {
        String[] policy = StringUtils.tokenizeToStringArray(ruleDef, "|");
        if (policy.length != 11) {
            throw new IllegalArgumentException("invalid parameter " + ruleDef);
        }
        passwordPolicy.setPasswordOption(Enum.valueOf(PasswordOption.class, policy[0].trim()));
        passwordPolicy.setMinLen(Integer.parseInt(policy[1].trim()));
        passwordPolicy.setMaxLen(Integer.parseInt(policy[2].trim()));
        passwordPolicy.setPattern(policy[3].trim());
        passwordPolicy.setFirstUpd(isBoolean(policy[4].trim()));
        String tmp = policy[5].trim();
        String validDate = tmp.substring(0, tmp.length() - 1);
        String validDateUnit = tmp.substring(tmp.length() - 1);
        passwordPolicy.setValidDate(Integer.parseInt(validDate));
        passwordPolicy.setValidDateUnit(resovleDateUnit(validDateUnit));
        passwordPolicy.setFaildCount(Integer.parseInt(policy[6]));
        passwordPolicy.setComplex(Integer.parseInt(policy[7]));
        passwordPolicy.setSmsCount(Integer.parseInt(policy[8]));
        passwordPolicy.setLockCount(Integer.parseInt(policy[9]));
        passwordPolicy.setLockTime(Integer.parseInt(policy[10]));

    }*/

    public static boolean isBoolean(String s) {
        if (("Y").equals(s) || (("Yes").equals(s))) {
            return true;
        }
        return false;
    }

    /*public static boolean resovleUsbKeyFlag(TokenType certType) {
        if (TokenType.UC == certType) {
            return true;
        } else {
            return false;
        }
    }*/

    public static int resovleDateUnit(String unit) {
        if (unit.equals(DAY)) {
            return Calendar.DATE;
        } else if (unit.endsWith(MONTH)) {
            return Calendar.MONTH;
        } else if (unit.endsWith(YEAR)) {
            return Calendar.YEAR;
        } else if (unit.endsWith(WEEK)) {
            return Calendar.WEEK_OF_YEAR;
        } else {
            throw new IllegalArgumentException("invalid data parameter " + unit);
        }
    }

    private static final String DAY = "D";
    private static final String MONTH = "M";
    private static final String YEAR = "Y";
    private static final String WEEK = "W";

    /**
     * 根据证书的base64位编码获取证书数据
     *
     * @param encoderBase64
     * @return
     */
    /*public static X509Certificate getClientCertificate(String encoderBase64) {
        byte[] buffer = Base64.base64ToByteArray(encoderBase64.replaceAll("[\\n|\\r]", ""));
        InputStream bytearrayinputstream = new ByteArrayInputStream(buffer);
        try {
            CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
            return (X509Certificate) certificatefactory.generateCertificate(bytearrayinputstream);
        } catch (Exception e) {
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509", "INFOSEC");
                bytearrayinputstream = new ByteArrayInputStream(buffer);
                return (X509Certificate) certificateFactory.generateCertificate(bytearrayinputstream);
            } catch (Exception e1) {
                log.error(e1);
                throw new ValidationRuntimeException("uibs.no_client_cert");
            }
        } finally {
            try {
                bytearrayinputstream.close();
            } catch (IOException e) {
                log.error(e.getStackTrace());
            }
        }
    }*/



    /**
     * 将string格式的日期按照指定的日期格式转换成date类型
     */
    public static java.util.Date parseDate(String dateString, String pattern) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat(pattern);
        try {
            return simpledateformat.parse(dateString);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * 将date类型s的日期按照指定的日期格式转换成tring格式
     */
    public static String fmtDate(java.util.Date date, String pattern) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat(pattern);
        return simpledateformat.format(date);
    }

    public static boolean equals(Object obj, Object anObject) {
        boolean bool = false;
        if (obj == null || anObject == null) {
            return bool;
        }

        bool = obj.equals(anObject);
        return bool;
    }





    /**
     * {比较两个日期的大小 .如果date1在date2之后，返回true; date1在date2之前或相等，返回false}
     *
     * @param date1
     * @param date2
     * @return
     * @version 1.0
     * @since 1.0
     */
    public static boolean dateCompare(Date date1, Date date2) {
        return date1.compareTo(date2) > 0 ? true : false;
    }




    /**
     * 产生n位随机数
     *
     * @param n
     * @param radix 10-只有数字 36-包含数字和字母(小写)
     * @return
     */
    public static String randomPassword(int n, int radix) {
        SecureRandom a = new SecureRandom();
        long l = a.nextLong();
        long l1 = l < 0L ? -l : l;
        StringBuffer stringbuffer = new StringBuffer(Long.toString(l1, radix));

        while (stringbuffer.length() < n) {
            stringbuffer.insert(0, '0');
        }

/*        for (stringbuffer = new StringBuffer(Long.toString(l1, radix)); stringbuffer.length() < n; stringbuffer.insert(0, '0')) {

        }*/
        if (stringbuffer.length() > n) {
            return stringbuffer.substring(stringbuffer.length() - n);
        } else {
            return stringbuffer.toString();
        }
    }

    /**
     * 产生N位随机数 其中包含数字和字母的组合含有大小写
     *
     * @param pwd
     * @return
     */
    // public static String randomPassword2(int n) {
    // char[] constant = { '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
    // 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p',
    // 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
    // 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
    // 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2',
    // '3', '4', '5', '6', '7', '8', '9' };
    // StringBuilder newRandom = new StringBuilder(65);
    // Random rd = new Random();
    // for (int i = 0; i < n; i++) {
    // newRandom.append(constant[rd.nextInt(65)]);
    // }
    //
    // return newRandom.toString();
    // }



    /**
     * 判断字符串是否是数字串
     *
     * @param num
     * @return
     */
    public static boolean isNumber(String num) {
        if (num == null) {
            return false;
        }
        for (int i = 0; i < num.length(); ++i) {
            char c = num.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * 日期运算
     * @param date 日期
     * @param minutes 分钟数
     * @return
     */
    public static java.util.Date rollDateByMinute(java.util.Date date, int minutes) {
        Calendar calendar = GregorianCalendar.getInstance(Locale.getDefault());
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.MINUTE, minutes);
        return new java.util.Date(calendar.getTimeInMillis());
    }

    /**
     * 判断是否为简单密码
     *
     * @author wangqi
     */
    public static boolean checkSimplePwd(String pwd) {
        char[] charArray = pwd.toCharArray();
        String eq = "";
        String asc = "";
        String desc = "";
        for (int i = 0; i < charArray.length - 1; i++) {
            Character c1 = charArray[i];
            Character c2 = charArray[i + 1];
            if (c1.equals(c2)) {
                eq += "1";
                continue;
            }
            String s = c1.toString() + c2.toString();
            if (pattern.matcher(s).matches()) {
                int i1 = Integer.valueOf(c1.toString());
                int i2 = Integer.valueOf(c2.toString());
                if (i1 - i2 == 1) {
                    desc += "1";
                } else if (i2 - i1 == 1) {
                    asc += "1";
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        if (eq.length() == pwd.length() - 1 || desc.length() == pwd.length() - 1 || asc.length() == pwd.length() - 1) {
            return false;
        }
        return true;
    }

    /**
     * 判断个人USBKey编号有效性
     *
     * @author wangqi
     */
    public static boolean checkPerUSBNo(String no) {
        if (no.startsWith("CF")) {
            return true;
        }
        if (no.length() != 12) {
            return false;
        }
        if (!pattern.matcher(no).matches()) {
            return false;
        }
        int checkNo = Integer.valueOf(no.substring(6, 7));
        String realNo = no.substring(0, 6) + no.substring(7);
        char[] c = realNo.toCharArray();
        int count = 0;
        for (int i = c.length - 1; i >= 0; i--) {
            int cc = Integer.valueOf(((Character) c[i]).toString());
            int jj = (c.length - i - 1) % 2 == 0 ? cc * 2 : cc;
            int kk = jj >= 10 ? jj / 10 + jj % 10 : jj;
            count += kk;
        }
        return (10 - count % 10) % 10 == checkNo;
    }

    public static boolean checkEntUSBNo(String no) {
        if (!no.startsWith("C")) {
            return true;
        }
        if (!no.startsWith("CO") && !no.startsWith("C0")) {
            return false;
        }
        if (no.length() != 14 && no.length() != 13) {
            return false;
        }
        String realCompareNo = "";
        if (no.length() == 13) {
            realCompareNo = no.substring(1);
        } else if (no.length() == 14) {
            realCompareNo = no.substring(2);
        }

        if (!pattern.matcher(realCompareNo).matches()) {
            return false;
        }
        int checkNo = Integer.valueOf(realCompareNo.substring(6, 7));
        String realNo = realCompareNo.substring(0, 6) + realCompareNo.substring(7);
        char[] c = realNo.toCharArray();
        int count = 0;
        for (int i = c.length - 1; i >= 0; i--) {
            int cc = Integer.valueOf(((Character) c[i]).toString());
            int jj = (c.length - i - 1) % 2 == 0 ? cc * 2 : cc;
            int kk = jj >= 10 ? jj / 10 + jj % 10 : jj;
            count += kk;
        }
        return (10 - count % 10) % 10 == checkNo;

    }

    /**
     * 获取一个对象的string形式并去除空格
     *
     * @param object
     * @return
     * @version 1.0
     * @since 1.0
     */
    public static String toStringAndTrim(Object object) {
        if (object == null) {
            return "";
        } else {
            return object.toString().trim();
        }
    }

    /**
     * 字符串替换,用replaceWith替换str中的replaced
     *
     * @param str
     * @param replaced
     * @param replaceWith
     * @return
     */
    public static String replace(String str, String replaced, String replaceWith) {
        if (str == null) {
            return null;
        }
        if (replaced == null || replaceWith == null) {
            return str;
        }
        StringBuffer buf = new StringBuffer();

        int pos = str.indexOf(replaced);
        if (pos > -1) {
            String left = str.substring(0, pos);
            String right = str.substring(pos + replaced.length());
            if (right.length() < 2) {
                right = right + "0";
            }
            buf.append(left);
            buf.append(replaceWith);
            buf.append(replace(right, replaced, replaceWith));
        } else {
            buf.append(str);
        }

        return buf.toString();
    }


    /**
     * 格式处理 1234567890 变成 1234**7890
     *
     * @param beginIndex 前面保留几位
     * @param endIndex   后面保留几位
     * @param replace    替代符号
     * @param acno
     * @return
     */
    public static String acNoFormat(int beginIndex, int endIndex, String replace, String acno) {
        StringBuffer sb = new StringBuffer();
        char[] acNochars = acno.toCharArray();

        for (int i = 0; i < acNochars.length; i++) {
            if (i >= beginIndex && i <= (acNochars.length - endIndex)) {
                sb.append(replace);
            } else {
                sb.append(acNochars[i]);
            }
        }
        return sb.toString();
    }


    /**
     * 字符串脱敏保留前3位+****+后5位，小于八位自动将前面替换的位置减少
     *
     * @param acno
     * @return
     */
    public static String acNoFormatDefalut(String acno) {
        return acNoFormatExt(3, 5, "*", acno);
    }


    /**
     * @param beginIndex
     * @param endIndex
     * @param replace
     * @param acno
     * @return
     */
    public static String acNoFormatExt(int beginIndex, int endIndex, String replace, String acno) {
        StringBuffer sb = new StringBuffer();
        char[] acNochars = acno.toCharArray();
        int lg = acno.length();

        int mm = beginIndex + endIndex - lg;

        if (mm > 0) {
            beginIndex = beginIndex - mm > 0 ? beginIndex - mm : 0;
        }

        for (int i = 0; i < acNochars.length; i++) {
            if (i >= beginIndex && i <= (acNochars.length - endIndex)) {
                sb.append(replace);
            } else {
                sb.append(acNochars[i]);
            }
        }
        return sb.toString();
    }


    /**
     * 比较两个对象是否相等
     *
     * @param firstStr
     * @param secondStr
     * @return
     */

    public static boolean trimAndEquals(Object firstStr, Object secondStr) {
        if (firstStr == null && secondStr == null) {
            return true;
        } else if (firstStr == null || secondStr == null) {
            return false;
        } else {
            return toStringAndTrim(firstStr).equals(toStringAndTrim(secondStr));
        }

    }

    /**
     * @param signedData 签名数据
     * @return map
     */
    /*public static final HashMap getSignedData(String signedData) {
        try {
            HashMap result = new HashMap();
            MsXMLElement xml = new MsXMLElement();
            xml.parse(signedData);
            for (Enumeration e = xml.elements(); e.hasMoreElements(); ) {
                MsXMLElement x0 = (MsXMLElement) e.nextElement();
                if ("E".equals(x0.getName())) {
                    for (Enumeration x0_e = x0.elements(); x0_e.hasMoreElements(); ) {
                        MsXMLElement x0_0 = (MsXMLElement) x0_e.nextElement();
                        if ("M".equals(x0_0.getName())) {
                            String name = "";
                            String value = "";
                            for (Enumeration e2 = x0_0.elements(); e2.hasMoreElements(); ) {
                                MsXMLElement x2 = (MsXMLElement) e2.nextElement();
                                if ("k".equals(x2.getName())) {
                                    name = x2.getText();
                                }
                                if ("v".equals(x2.getName())) {
                                    value = x2.getText();
                                }
                            }
                            if (!isNullOrEmpty(name)) {
                                result.put(name, value);
                            }
                        }
                    }
                }
            }
            return result;
        } catch (Exception e) {
            throw new IllegalArgumentException("invalid sign format");
        }
    }*/

    /**
     * 把object 转换成SqlDate
     */
    public static Date convertSqlDate(Object s) {
        if (s == null) {
            return null;
        } else {
            if (s instanceof Long) {
                return new Date((Long) s);
            } else if (s instanceof Number) {
                return new Date(((Number) s).longValue());
            } else if (s instanceof String) {
                return Date.valueOf((String) s);
            } else {
                return (Date) s;
            }
        }
    }

    /**
     * 前提条件；List 中存储的对象为Map; sorkKey 为Map中某个Key; descend 排序方式 true:降序 false:升序
     * 把value对象的toString()结果比较；
     */
    private static class MapSingleComparator implements Comparator {
        Object sortKey;
        boolean descend;

        public MapSingleComparator(Object sortKey, boolean descend) {
            this.sortKey = sortKey;
            this.descend = descend;
        }

        @Override
        public int compare(Object o1, Object o2) {
            int compareResult;
            if (o1 == null && o2 == null) {
                compareResult = 0;
            } else if (o1 == null) {
                compareResult = -1;
            } else if (o2 == null) {
                compareResult = 1;
            } else {
                Object obj1 = ((Map) o1).get(sortKey);
                Object obj2 = ((Map) o2).get(sortKey);
                if (obj1 == null && obj2 == null) {
                    compareResult = 0;
                } else if (obj1 == null) {
                    compareResult = -1;
                } else if (obj2 == null) {
                    compareResult = 1;
                } else {
                    compareResult = obj1.toString().compareTo(obj2.toString());
                }
            }

            return descend ? compareResult * -1 : compareResult;
        }

    }

    /**
     * 将集合dataList按指定的字段进行排序
     *
     * @param dataList
     * @param sortKey
     * @param descend
     */
    public static void sortListBySingleMapKey(List dataList, Object sortKey, boolean descend) {
        if (sortKey == null) {
            log.error("sort keys should not be null.");
            return;
        }
        Collections.sort(dataList, new MapSingleComparator(sortKey, descend));
        return;
    }




    /*public static String digest(String value) {
        return CsiiUtils.digest(value + ABC_SEED);
    }*/


    /**
     * 判断外围系统返回是否报错
     *
     * @param rep
     */
    /*public static void isTrue(CommonResponse rep) {
        //判断核心是否有返回错误
        if (!CommonResponseHead.SUCCESS.equals(rep.getCommonResponseHead().getRespCode())) {
            throw new RemoteRejectRuntimeException(rep.getCommonResponseHead().getRespCode(), rep.getCommonResponseHead().getRespMessage(), null);
        }
    }*/


    /**
     * 查询list是否无记录
     *
     * @param list
     */
    /*public static void isEmpty(List list) {
        if (list == null || list.size() == 0) {
            throw new ValidationRuntimeException(CHECKMSG.VALIDATION_LIST_EMPTY);//记录不存在
        }
    }*/

    //直接将base64编码生成证书对象
    /*public static X509Certificate generateCertFromBase64(String certBase64) {
        InputStream in = new ByteArrayInputStream(com.csii.pe.common.util.Base64.base64ToByteArray(certBase64));
        CertificateFactory cf;
        try {
            cf = CertificateFactory.getInstance("X.509");
            X509Certificate clientCert = (X509Certificate) cf.generateCertificate(in);

            return clientCert;
        } catch (CertificateException e) {
            log.error(e.getStackTrace());
            //System.out.println("ClientCert Generated Failed");
        }
        return null;
    }*/


    static final Pattern variableFillingPattern = Pattern.compile("(\\$\\{)([\\w]+)(\\})");

    /**
     * @param content 字符串内容
     * @param map map
     * @return 替换后的字符串
     */
    /*public static String variableFilling(String content, Map<String, Object> map) {
        Matcher m = variableFillingPattern.matcher(content);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            //规则中${值}中的值
            String key = m.group(2);

            if (log.isDebugEnabled()) {
                log.debug("符合规则中第二个的值" + key);
            }

            if (!map.containsKey(key)) {
                throw new PeRuntimeException("map need key :" + key);
            }
            //下一步是替换并且把替换好的值放到sb中
            m.appendReplacement(sb, String.valueOf(map.get(key)));
        }
        //把符合的数据追加到sb尾
        m.appendTail(sb);

        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }

        return sb.toString();
    }*/

    /**
     * @param content 字符串内容
     * @param param 参数
     * @return 替换后的字符串
     */
    public static String variableFilling(String content, String[] param) {
        Matcher m = variableFillingPattern.matcher(content);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; m.find(); i++) {
            m.appendReplacement(sb, param[i]);
        }
        //把符合的数据追加到sb尾
        m.appendTail(sb);
        if (log.isDebugEnabled()) {
            log.debug(sb.toString());
        }
        return sb.toString();
    }

    /*public static Map dealIdentity(String identity) throws ValidationException, ParseException {
        //数字
        String nuPat = "^[0-9]{8}$";
        //中文
        String chnPat = "^[\\u4E00-\\u9FBB\\u3400-\\u4DBF\\uF900-\\uFAD9\\u3000-\\u303F\\u2000-\\u206F\\uFF00-\\uFFEF]{1,4}$";
        Map idnoValidity = new HashMap();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String[] beginAndEnd = identity.split("-");
        if (beginAndEnd.length != 2) {
            throw new ValidationException("validation.identityformat.error");
        }
        //起始日期
        if (Util.isNullOrEmpty(beginAndEnd[0])) {
            throw new ValidationException("validation.identitybegindate.null");
        }
        //结束日期
        if (Util.isNullOrEmpty(beginAndEnd[1])) {
            throw new ValidationException("validation.identityenddate.null");
        }
        String begin = beginAndEnd[0].replaceAll("\\.", "");
        String end = beginAndEnd[1].replaceAll("\\.", "");
        //判断开始时间,开始时间默认为数字，如果不是数字报错
        java.util.Date bdate;
        java.util.Date edate;
        Pattern pb = PatternPool.getPattern(nuPat);
        Matcher matcher = pb.matcher(begin);
        if (!matcher.matches()) {
            throw new ValidationException("validation.identitybegindate.error");
        } else {
            bdate = simpleDateFormat.parse(begin);
            if (bdate.compareTo(DateUtil.getCurrentDateTime()) > 0) {
                throw new ValidationException("validation.identitybegindate.error");
            }
        }
        idnoValidity.put("DocumentBegin", begin);
        //判断结束时间，可能出现中文，出现中文不管，数字判断日期
        Matcher matcherendno = pb.matcher(end);
        if (matcherendno.matches()) {
            //匹配数字
            edate = simpleDateFormat.parse(end);
            if (edate.compareTo(DateUtil.getCurrentDateTime()) < 0) {
                //证件过期
                throw new ValidationException("validation.identityoverdate.error");
            }
            idnoValidity.put("DocumentEnd", end);
        } else {
            Pattern nb = PatternPool.getPattern(chnPat);
            Matcher matcherchn = nb.matcher(end);
            if (!matcherchn.matches()) {
                throw new ValidationException("validation.identityenddate.error");
            }
            idnoValidity.put("DocumentEnd", "99991231");
        }
        return idnoValidity;

    }*/

    /*public static Object getMapData(Map data, String fieldName){
        if(org.apache.commons.lang3.StringUtils.isBlank(fieldName)){
            return null;
        }
        Object result = null;
        String fn[] = fieldName.split("\\.");
        if(fn.length > 1){
            Object tempObj = data;
            for(String name : fn){
                if(org.apache.commons.lang3.StringUtils.isNotBlank(name)){
                    tempObj = ClassUtils.getObjectValue(tempObj, name);
                }
            }
            result = tempObj;
        } else {
            result = ClassUtils.getObjectValue(data, fieldName);
        }
        return result;
    }*/

    //短信验证码转换
    public static  byte[] getBytesWithGivenEncoding(String content, String encoding) {
        try {
            byte[] bytes = content.getBytes(encoding);
            return bytes;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * obj转bigdecimal
     * @param obj
     * @return
     */
    public static BigDecimal obj2BigDecimal(Object obj){
        BigDecimal bigDec = null;
        if(obj != null){
            if(obj instanceof BigDecimal){
                bigDec = (BigDecimal) obj;
            }else if(obj instanceof String){
                bigDec = new BigDecimal((String)obj);
            }else if(obj instanceof BigInteger){
                bigDec = new BigDecimal((BigInteger)obj);
            }else if(obj instanceof Number){
                bigDec = new BigDecimal(((Number)obj).doubleValue());
            }else {
                throw new ClassCastException("Can not make ["+obj+"] into a BigDecimal .");
            }
        }
        return bigDec;
    }

    /**
     * obj转bigdecimal 无空
     * @param obj
     * @return
     */
    public static BigDecimal obj2BigDecimalNotNull(Object obj){
        BigDecimal bigDec = new BigDecimal("0");
        if(obj != null){
            if(obj instanceof BigDecimal){
                bigDec = (BigDecimal) obj;
            }else if(obj instanceof String){
                bigDec = new BigDecimal((String)obj);
            }else if(obj instanceof BigInteger){
                bigDec = new BigDecimal((BigInteger)obj);
            }else if(obj instanceof Number){
                bigDec = new BigDecimal(((Number)obj).doubleValue());
            }else {
                throw new ClassCastException("Can not make ["+obj+"] into a BigDecimal .");
            }
        }
        return bigDec;
    }

    public static <E> List<E> sort(List<E> list, final String keyStr, final SortOrder sort){
        Collections.sort(list, new Comparator<E>() {
            @Override
            public int compare(E a, E b) {
                int ret = 0;
                String method = "get" + (keyStr.substring(0,1).toUpperCase().concat(keyStr.substring(1)));
                try{
                    Method m1 = ((E)a).getClass().getMethod(method);
                    Method m2 = ((E)b).getClass().getMethod(method);
                    if(sort !=null && SortOrder.DESCENDING.equals(sort)){
                        //反序
                        ret = m2.invoke(b).toString().compareTo(m1.invoke(a).toString());
                    }else {
                        //正序
                        ret = m1.invoke(a).toString().compareTo(m2.invoke(b).toString());
                    }
                }catch (Exception ne){
                    ne.printStackTrace();
                }
                return ret;
            }
        });
        return list;
    }

    /**
     * 获取详细堆栈异常
     * @param e
     * @return
     */
    public static String excetionDetail(Throwable e){
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)){
            e.printStackTrace(pw);
            return sw.toString();
        }catch (Exception e1){
            e1.printStackTrace();
        }
        return null;
    }
}
