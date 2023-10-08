//package com.das.consultation.service.redmine.impl;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.bind.RelaxedPropertyResolver;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//
///**
// * 读取配置文件中的配置信息到内存中
// */
//@Configuration
//public class RedmineConfig2 implements EnvironmentAware {
//
//    private static final Logger logger = LoggerFactory.getLogger(RedmineConfig2.class);
//
//    private RelaxedPropertyResolver propertyResolver;
//
//    public static String groupId;
//    public static int topNum;
//    public static Double notRegThres;
//    //    public static Double threshold; //阀值
////    public static String registerURL;  //注册
////    public static String updateURL;  //更新
////    public static String deleteURL;  //删除
////    public static String getURL;  //查询
////    public static String verifyURL;  //认证
////    public static String identifyURL;  //1:N识别
//    public static String matchURL;  //两两比对
//    //    public static String detectURL; //人脸检测
//    public static String compareImgFeaURL; //图片特征比对
//    public static String compare2imgURL; //图片比对
//    public static String compare2feaURL; //特征比对
//    public static String extractURL; //特征提取
//    public static String getQuality; // 图片质量检测
//
//    public static boolean reg1vN; // 是否注册1:N
//    public static boolean del1vN; //是否删除1:N
//    public static boolean qualityCheck; // 是否检测图片质量
//    public static Integer maxSize; // 图像最大大小
//
//    public static String apiSecret; //aibee分配apiSecret
//    public static String apiKey; //aibee分配apikey
//
//    public static String registerN; // 1:N注册
//    public static String removeN; // 1:N删除组内用户
//    public static String deleteN; // 1:N库删除用户
//    public static String removeGroup; // 1:N删除组
//    public static String compareN; // 1:N比对
//    public static String infoN;//N库注册用户信息、
//
//
//
//    @Override
//    public void setEnvironment(Environment environment) {
//        this.propertyResolver = new RelaxedPropertyResolver(environment, "aibee.face.restapi.");
//        this.getInitBdFaceURL();
//        this.propertyResolver = new RelaxedPropertyResolver(environment, "aibee.face.");
//        this.getConfigInfo();
//        this.propertyResolver = new RelaxedPropertyResolver(environment, "aibee.face.image.");
//        this.getImageConfig();
//    }
//
//    /**
//     * 初始化爱笔人脸识别的验证URL
//     */
//    private void getInitBdFaceURL(){
//        logger.info("开始读取爱笔人脸URL配置...");
//        groupId = propertyResolver.getProperty("groupId");
//        topNum = Integer.parseInt(propertyResolver.getProperty("topNum"));
//        notRegThres = Double.parseDouble(propertyResolver.getProperty("notRegThres"));
////        threshold = Double.parseDouble(propertyResolver.getProperty("threshold"));
////        registerURL = propertyResolver.getProperty("register");
////        updateURL = propertyResolver.getProperty("update");
////        deleteURL = propertyResolver.getProperty("delete");
////        getURL = propertyResolver.getProperty("get");
////        verifyURL = propertyResolver.getProperty("verify");
////        identifyURL = propertyResolver.getProperty("identify");
//        matchURL = propertyResolver.getProperty("match");
////        detectURL = propertyResolver.getProperty("detect");
//        compareImgFeaURL = propertyResolver.getProperty("compareImgFea");
//        compare2imgURL = propertyResolver.getProperty("compare2img");
//        compare2feaURL = propertyResolver.getProperty("compare2fea");
//        extractURL = propertyResolver.getProperty("extract");
//        getQuality = propertyResolver.getProperty("imgQuality");
//        registerN = propertyResolver.getProperty("registerN");
//        removeN = propertyResolver.getProperty("removeN");
//        deleteN = propertyResolver.getProperty("deleteN");
//        removeGroup = propertyResolver.getProperty("removeGroup");
//        compareN = propertyResolver.getProperty("compareN");
//        infoN = propertyResolver.getProperty("infoN");
//    }
//
//    private void getConfigInfo(){
//        String reg1vNConf = propertyResolver.getProperty("reg1vN");
//        String del1vNConf = propertyResolver.getProperty("del1vN");
//        reg1vN = Boolean.valueOf(reg1vNConf);
//        del1vN = Boolean.valueOf(del1vNConf);
//        apiSecret = propertyResolver.getProperty("apiSecret");
//        apiKey = propertyResolver.getProperty("apiKey");
//    }
//
//    private void getImageConfig(){
//        String qualityCheckConf = propertyResolver.getProperty("qualityCheck");
//        String maxSizeConf = propertyResolver.getProperty("maxSize");
//        qualityCheck = Boolean.valueOf(qualityCheckConf);
//        maxSize = Integer.valueOf(maxSizeConf);
//    }
//
//}
