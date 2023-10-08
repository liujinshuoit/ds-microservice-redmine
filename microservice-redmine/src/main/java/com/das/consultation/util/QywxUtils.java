package com.das.consultation.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: LJS
 * @Date: 2022/5/10 19:27
 */
public class QywxUtils {

    /**
     * Token获取
     * @param tokenUrl
     * @param corpId
     * @param appSecret
     * @return
     */
    public static String getAccToken(String tokenUrl, String corpId, String appSecret) {
        String sbf = OkHttpUtils.getJson(tokenUrl+"?corpid="+corpId+"&corpsecret="+appSecret, "");
        String ss =JSONObject.parseObject(sbf.toString()).getString("access_token"); //获取data对象
        if("".equals(ss)||ss==null){
            System.out.println("获取token失败>>>>>ss的值是："+ss);
        }
        /* 返回token字符串 */
        return ss;
    }

    /**
     * 登陆用户ID获取
     * @param appUserInfoUrl
     * @param appCode
     * @param tokenUrl
     * @param corpId
     * @param appSecret
     * @return
     */
    public static String getLoginUserId(String appUserInfoUrl,  String appCode, String tokenUrl, String corpId, String appSecret) {
        /* Token令牌获取 */
        String token = getAccToken(tokenUrl, corpId, appSecret);
        /* 根据应用code获取用户ID */
        String userJsonStr = OkHttpUtils.getJson(appUserInfoUrl+"?code=" + appCode +"&access_token=" + token, "");
        return userJsonStr;
    }

    /**
     * 用户列表获取
     * @param userListUrl
     * @param tokenUrl
     * @param corpId
     * @param userListAppSecret
     * @return
     */
    public static String getUserList(String userListUrl, String tokenUrl, String corpId, String userListAppSecret) {
        /* Token令牌获取 */
        String token = getAccToken(tokenUrl, corpId, userListAppSecret);
        /* 根据应用code获取用户ID */
        String userListJsonStr = OkHttpUtils.getJson(userListUrl+"?department_id=1&fetch_child=1&access_token=" + token, "");
        return userListJsonStr;
    }

//    /**
//     * 获取组织架构id和人员Userid
//     * @return
//     */
//    private static void getAllQiYeUser(String starttime,String endtime,String token) throws Exception {
//        List<Object> users = new ArrayList<>();
//        //--拉取企业微信所有部门信息
//        String generalUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token="+token+"&department_id=1&fetch_child=1"; //Token地址
//        URL url = new URL(generalUrl);
//        String strRead = null;
//        StringBuffer sbf = new StringBuffer();
//        // 连接接口路径
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        // 设置请求属性
//        connection.setRequestProperty("Content-Type", "application/json");
//        connection.setRequestProperty("Connection", "Keep-Alive");
//        connection.setUseCaches(false);
//        connection.setDoOutput(true);
//        connection.setDoInput(true);
//        // 建立连接
//        connection.connect();
//        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//        // 插入Body里面的json数据
//        JSONObject parm = new JSONObject();
//        writer.write(parm.toString());
//        writer.flush();
//        InputStream is = connection.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//        while ((strRead = reader.readLine()) != null) {
//            sbf.append(strRead);
//            sbf.append("\r\n");
//        }
//        reader.close(); //关流
//        connection.disconnect();
//        JSONObject object = JSONObject.parseObject(sbf.toString());
//        //System.out.print(object);
//        JSONArray jsonArray = new JSONArray(object.get("userlist").toString());
//        int iSize = jsonArray.length();
//        List<String> userlist = new ArrayList<String>();//将员工Userid放到List里面
////        for (int i = 0; i < iSize; i++) {
////            org.json.JSONObject jsonObject = jsonArray.getJSONObject(i);
////            String userid = CUtil.convert2String(jsonObject.get("userid"));//转换String的方法 CUtil.convert2String();
////            userlist.add(userid);
////        }
////
////        List<List<String>> list = fixedGrouping2(userlist, 100); //因为企业微信API一次性只能获取固定人员和30天的数据因此将人员进行拆分
////
////        for (List<String> list2 : list) {
////            getDKData(starttime, endtime, list2,token);
////        }
//    }



//    /**
//     * 将一组数据固定分组，每组n个元素
//     *
//     * @param source 要分组的数据源
//     * @param n      每组n个元素
//     * @param <T>
//     * @return
//     */
//    public static <T> List<List<T>> fixedGrouping2(List<T> source, int n) {
//
//        if (null == source || source.size() == 0 || n <= 0)
//            return new ArrayList<List<T>>();
//        List<List<T>> result = new ArrayList<List<T>>();
//        int remainder = source.size() % n;
//        int size = (source.size() / n);
//        for (int i = 0; i < size; i++) {
//            List<T> subset = null;
//            subset = source.subList(i * n, (i + 1) * n);
//            result.add(subset);
//        }
//        if (remainder > 0) {
//            List<T> subset = null;
//            subset = source.subList(size * n, size * n + remainder);
//            result.add(subset);
//        }
//        return result;
//    }

//    public  static  void getDKData(String starttime,String endtime,List userlist,String token) throws Exception{
//        System.out.print(userlist);
//        long kssj = CUtil.convert2Date(starttime).getTime()/1000; //开始时间
//        long jssj = CUtil.convert2Date(endtime).getTime()/1000; //结束时间
//        String generalUrl = "https://qyapi.weixin.qq.com/cgi-bin/checkin/getcheckindata?access_token="+getAccToken1()+""; //Token地址
//        URL url = new URL(generalUrl);
//        String strRead = null;
//        StringBuffer sbf = new StringBuffer();
//        // 连接接口路径
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        // 设置请求属性
//        connection.setRequestProperty("Content-Type", "application/json");
//        connection.setRequestProperty("Connection", "Keep-Alive");
//        connection.setUseCaches(false);
//        connection.setDoOutput(true);
//        connection.setDoInput(true);
//        // 建立连接
//        connection.connect();
//        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//        // 插入Body里面的json数据
//        JSONObject parm = new JSONObject();
//        parm.put("opencheckindatatype","3");    //打卡类型。1：上下班打卡；2：外出打卡；3：全部打卡
//        parm.put("starttime", kssj);
//        parm.put("endtime", jssj);
//        parm.put("useridlist", userlist);
//        writer.write(parm.toString());
//        writer.flush();
//        InputStream is = connection.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//        while ((strRead = reader.readLine()) != null) {
//            sbf.append(strRead);
//            sbf.append("\r\n");
//        }
//        reader.close(); //关流
//        connection.disconnect();
//
//        JSONObject object = JSONObject.parseObject(sbf.toString());
//        System.out.print(object);
//
//        JSONArray jsonArray = new JSONArray(object.get("checkindata").toString());//获取打卡数据
//        int iSize = jsonArray.length();
//        for (int i = 0; i < iSize; i++) {//循环验证状态
//            org.json.JSONObject jsonObject = jsonArray.getJSONObject(i);
//            String deviceid = CUtil.convert2String(jsonObject.get("deviceid"));//获取打卡设备id。如果有值则证明打卡了。如果无则证明没有
//            String exception_type = CUtil.convert2String(jsonObject.get("exception_type"));//打卡类型 未打卡状态为：未打卡，此处为空的情况为走了补考勤申请因此需要进行两个条件来验证
//            if (deviceid.length() > 0 || exception_type.length() == 0) {
//                insterIorecordSync(jsonObject);//进行数据同步操作
//            }
//        }
//
//    }

    //    private static String QywxTokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";
//
//    private static String AppKey = "wwad3174e7a51d5933";
//
//    private static String AppSecret = "vhaYbCop64x4TjJbWbTebWdmpPvt1MOrB2LTwtdUE_I";

//    public static void main(String[] args) throws Exception {
//        String token = getAccToken("https://qyapi.weixin.qq.com/cgi-bin/gettoken", "wwad3174e7a51d5933", "vhaYbCop64x4TjJbWbTebWdmpPvt1MOrB2LTwtdUE_I");
//        System.out.println("Token令牌获取：" + token);
//        getAllQiYeUser(null, null, token);
//    }

}
