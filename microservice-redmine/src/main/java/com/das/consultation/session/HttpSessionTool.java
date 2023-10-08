package com.das.consultation.session;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 49188 on 2017/8/1.
 */
public class HttpSessionTool {

    public static void saveSession(HttpServletRequest request, String key, Object value){
        request.getSession().setAttribute(key, value);
    }

    public static <T> T getValue(HttpServletRequest request, String key){
        return (T)request.getSession().getAttribute(key);
    }

    public static void clearValue(HttpServletRequest request, String key){
        request.getSession().removeAttribute(key);
    }

}
