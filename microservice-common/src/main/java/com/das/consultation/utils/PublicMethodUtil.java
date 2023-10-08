package com.das.consultation.utils;

import com.alibaba.fastjson.JSONObject;
import com.das.consultation.constant.RangeDateConstant;
import com.das.consultation.exception.DateException;
import com.das.consultation.exception.ParamErrorException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * created by jun on 2020/8/6
 * describe:公共方法
 * version 1.0
 */
public class PublicMethodUtil {

    /**
     * str转为Date
     *
     * @param strDate date字符串
     * @return date
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * str转为Date YYYY-MM-DD
     *
     * @param strDate date字符串
     * @return date
     */
    public static Date strToDateDay(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 将String类型转化为Date时间格式
     *
     * @param str str
     * @param id  id
     * @return date
     */
    public static Date ToDateTime(String str, int id) {
        SimpleDateFormat format = null;
        switch (id) {
            case 1:
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case 2:
                format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                break;
            case 3:
                format = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                break;
            case 4:
                format = new SimpleDateFormat("yyyy-MM-dd");
                break;
        }
        Date date = null;
        try {
            date = Objects.requireNonNull(format).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 判断类中每个属性是否都为空
     *
     * @param o 0
     * @return boolean
     */
    public static boolean allFieldIsNULL(Object o) {
        try {
            for (Field field : o.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                Object object = field.get(o);
                if (object instanceof CharSequence) {
                    if (!org.springframework.util.ObjectUtils.isEmpty(object)) {
                        return false;
                    }
                } else {
                    if (null != object) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return true;
    }

    /**
     * 当天时间往后加一天
     *
     * @param str 字符串时间
     * @return 字符串时间
     * @throws ParseException e
     */
    public static String getAddOneDay(String str) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dd = df.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dd);
        calendar.add(Calendar.DAY_OF_MONTH, 1);//加一天
        System.out.println("增加一天之后：" + df.format(calendar.getTime()));
        return df.format(calendar.getTime());
    }

    /**
     * ParamIsError参数是否正确
     *
     * @param parameter  判断参数的数组
     * @param jsonObject 收到的json对象
     * @return false 不为空
     * @throws ParamErrorException 参数错误异常
     */
    public static boolean ParamIsError(String[] parameter, JSONObject jsonObject) throws ParamErrorException {
        String[] newArray = new String[0];
        for (String str : jsonObject.keySet()) {
            if (null != jsonObject.get(str)) {
                String valString = jsonObject.get(str).toString();
                if (Arrays.asList(parameter).contains(str)) {
                    newArray = AddStringArray(newArray, str);
                    if (StringUtils.isEmpty(valString)) {
                        throw new ParamErrorException(str);
                    }
                }
            }

        }
        if (!Arrays.equals(parameter, newArray)) {
            for (String str : parameter) {
                if (!Arrays.asList(newArray).contains(str)) {
                    throw new ParamErrorException(str);
                }
            }
        }
        return false;
    }

    /**
     * 字符串转为时间戳
     *
     * @param str 参数
     * @return 时间戳
     */
    public static long String2Date(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long beginUseTime = 0;
        try {
            beginUseTime = sdf.parse(str).getTime() / 1000;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return beginUseTime;
    }

    /**
     * string [] 元素追加
     *
     * @param arr string数组
     * @param str 添加的元素
     * @return 新数组
     */
    public static String[] AddStringArray(String[] arr, String str) {
        int size = arr.length;  //获取数组长度
        String[] tmp = new String[size + 1];  //新建临时字符串数组，在原来基础上长度加一
        //先遍历将原来的字符串数组数据添加到临时字符串数组
        System.arraycopy(arr, 0, tmp, 0, size);
        tmp[size] = str;  //在最后添加上需要追加的数据
        return tmp;  //返回拼接完成的字符串数组
    }


    /**
     * 校验日期是否正确
     *
     * @param val  验证字段名
     * @param time 字符串时间
     * @param id   类型
     */
    public static void checkTimeFormat(String val, String time, int id) throws DateException {
        Matcher m;
        Pattern p;
        switch (id) {
            case 1:
                p = Pattern.compile(RangeDateConstant.EL_D);
                m = p.matcher(time);
                if (!m.matches()) {
                    throw new DateException(val, "yyyy-MM-dd");
                }
                break;
            case 2:
                p = Pattern.compile(RangeDateConstant.EL_S);
                m = p.matcher(time);
                if (!m.matches()) {
                    throw new DateException(val, "yyyy-MM-dd HH:mm:ss");
                }
                break;
            case 3:
                p = Pattern.compile(RangeDateConstant.EL_H);
                m = p.matcher(time);
                if (!m.matches()) {
                    throw new DateException(val, "HH:mm:ss");
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
//        m.matches();
    }

    /***
     * 遍历比较两个对象将相同属性值从一个对象赋值到另一个对象
     * @param t1 对象1
     * @param t2 对象2
     * @param <T> 泛型
     * @return 赋值后的对象2
     * @throws Exception 异常
     */
    public static <T> void reflect(T t1, T t2) throws Exception {
        Class cls1 = t1.getClass();
        Class cls2 = t2.getClass();
        Field[] fields1 = cls1.getDeclaredFields();
        Field[] fields2 = cls2.getDeclaredFields();
        for (Field f1 : fields1) {
            f1.setAccessible(true);
            for (Field f2 : fields2) {
                f2.setAccessible(true);
                if (f1.getName().equals(f2.getName())) {
                    f2.set(t2, f1.get(t1));
                }
            }
        }
    }


    /**
     * 拼凑加密字段字符串
     */
    public static String getSign(Map<String, Object> params, String app_key) throws UnsupportedEncodingException {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        // 将参数以参数名的字典升序排序
        Map<String, Object> sortParams = new TreeMap<>(params);
        // 遍历排序的字典,并拼接"key=value"格式
        for (Map.Entry<String, Object> entry : sortParams.entrySet()) {
            String key = entry.getKey();
            Object value = ((String) entry.getValue()).trim();
            if (null != value && !value.equals("")) {
                if (i != 0)
                    sb.append("&");
                sb.append(key).append("=").append(value);
            }
            i++;
        }
        sb.append("key=").append(app_key);
        System.out.println("拼接的字符串===" + sb.toString());
        return md5(sb.toString());
    }

    /**
     * Used building output as Hex
     */
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
            'b', 'c', 'd', 'e', 'f'};

    /**
     * 对字符串进行MD5加密
     *
     * @param text 明文
     * @return 密文
     */
    public static String md5(String text) {
        MessageDigest msgDigest;
        try {
            msgDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
        msgDigest.update(text.getBytes(StandardCharsets.UTF_8)); // 注意改接口是按照指定编码形式签名
        byte[] bytes = msgDigest.digest();
        String md5Str = new String(encodeHex(bytes));
        String verifyMD5 = PublicMethodUtil.VerifyMD5(md5Str.toUpperCase(), text);
        System.out.println("验签结果=====" + verifyMD5);
        return md5Str.toUpperCase();
    }

    private static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0x0F & data[i]];
        }
        return out;
    }


    private static String VerifyMD5(String Result, String newInput) {
//        MessageDigest msgDigest;
//        try {
//            msgDigest = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            throw new IllegalStateException("System doesn't support MD5 algorithm.");
//        }
//        msgDigest.update(newInput.getBytes(StandardCharsets.UTF_8)); // 注意改接口是按照指定编码形式签名
//        byte[] bytes = msgDigest.digest();
//        String md5Str = new String(encodeHex(bytes)).toUpperCase();
        final String md5Str = encrypt(newInput).toUpperCase();
        if (md5Str.equals(Result)) {
            return "验证密码通过！";
        } else {
            return "验证密码失败！";
        }

    }


    public static String encrypt(String s) {
        try {
            return encrypt(s.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException var2) {
            System.out.println("密码验证出异常了");
            return "";
        }
    }

    public static String encrypt(byte[] bytes) {
        MessageDigest messagedigest = null;

        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var3) {
            System.out.println("初始化失败，MessageDigest不支持MD5Util，原因是：" + var3.getMessage());
        }

        messagedigest.update(bytes);
        return byte2hex(messagedigest.digest());
    }

    public static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp = "";

        for (int n = 0; n < b.length; ++n) {
            stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }

            if (n < b.length - 1) {
                hs.append("");
            }
        }

        return hs.toString().toLowerCase();
    }
}
