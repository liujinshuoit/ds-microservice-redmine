package com.das.consultation.utils;

import okhttp3.*;

import java.io.File;

/**
 * created by jun on 2020/8/14
 * describe:okHttp3请求
 * version 1.0
 */
public class OkHttpUtils {

    /**
     * xml格式post请求接口调用
     *
     * @param url    接口地址
     * @param xmlStr xml格式请求参数体
     * @return
     */
    public static String postXml(String url, String xmlStr) {
        RequestBody body = RequestBody.create(MediaType.parse("application/xml"), xmlStr);
        Request requestOk = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response;
        try {
            response = new OkHttpClient().newCall(requestOk).execute();
            String jsonString = response.body().string();
            if (response.isSuccessful()) {
                return jsonString;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }

    /**
     * get请求接口，返回json
     *
     * @param url 接口地址
     * @return
     */
    public static String getJson(String url, String jsonStr) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), jsonStr);
        Request requestOk = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response;
        try {
            response = new OkHttpClient().newCall(requestOk).execute();
            String jsonString = response.body().string();
            if (response.isSuccessful()) {
                return jsonString;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "";
    }

    /**
     * 发送文件
     *
     * @param url         请求接口地址
     * @param uploadDir   参数上传目录
     * @param baseFileUrl 文件保存基准路径
     * @param relativeUrl 文件保存的相对路径
     * @return 接口返回值
     * 该方法前端以formData格式传入，包括文件和参数，可一起传入。
     */
    public String uploadFilePost(String url, String uploadDir, String baseFileUrl, String relativeUrl) {

        File temporaryFile = new File(baseFileUrl + relativeUrl);
        if (!temporaryFile.exists()) {
            return "";
        }
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("uploadDir", uploadDir) //参数一，可注释掉
                .addFormDataPart("fileUrl", relativeUrl) //参数二，可注释掉
                .addFormDataPart("file", temporaryFile.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), temporaryFile)) //文件一
                .build();
        Request requestOk = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response;
        try {
            response = new OkHttpClient().newCall(requestOk).execute();
            String jsonString = response.body().string();
// temporaryFile.delete();
            if (response.isSuccessful()) {
                return jsonString;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
