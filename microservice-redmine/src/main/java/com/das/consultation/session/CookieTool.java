package com.das.consultation.session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by 49188 on 2017/8/1.
 */
public class CookieTool {

    public static String getCookie(HttpServletRequest request, String key){
        Cookie cookies[] = request.getCookies();
        for(int i=0;cookies!=null && i<cookies.length;i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals(key)){
                return URLDecoder.decode(cookie.getValue());
            }
        }
        return "";
    }

    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String key, String value){
        Cookie cookie = new Cookie(key, URLEncoder.encode(value));
        //设置时间为1个月
        cookie.setMaxAge(30*24*3600);
        cookie.setPath(request.getContextPath());
        //把cookie给浏览器
        response.addCookie(cookie);
    }

    public static void clearCookie(HttpServletRequest request, HttpServletResponse response, String key){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if(key.equals(cookie.getName())){
                cookie.setMaxAge(0);
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);
            }
        }
    }

}
