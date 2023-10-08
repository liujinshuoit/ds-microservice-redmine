//package com.das.consultation.interceptor;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
///**
// * 系统异常处理器
// * @Author: LJS
// * @Date: 2021/5/26 9:48
// */
//@ControllerAdvice
//public class SystemExceptionHandler {
//
//    private final static Logger LOGGER = LoggerFactory.getLogger(SystemExceptionHandler.class);
//
//    /**
//     * 系统服务异常处理
//     * @param e
//     * @return
//     */
//    @ResponseBody
//    @ExceptionHandler(ServiceException.class)
//    public Object serviceExceptionHandler(ServiceException e) {
//        /* 局部变量 */
//        // 异常码
//        String code = e.getCode();
//        // 异常信息
//		String message = e.getMessage();
//		// 异常返回对象
//        RestResponse restResponse = new RestResponse();
//
//        /* 异常日志记录 */
//        LOGGER.error(message, e);
//        restResponse.getResponseHeader().setErrorCode(code);
//        restResponse.getResponseHeader().setMessage(message);
//        restResponse.setResponseBody(e.getResponseBody());
//
//        /* 异常返回 */
//        return restResponse;
//    }
//
//    /**
//     * 系统其它异常处理
//     * @param e
//     * @return
//     */
//    @ResponseBody
//    @ExceptionHandler(Exception.class)
//    public Object exceptionHandler(Exception e) {
//        /* 异常日志记录 */
//        LOGGER.error("系统异常", e);
//
//        /* 局部变量 */
//        // 异常返回对象
//        RestResponse restResponse = new RestResponse();
//
//        /* 异常返回对象组装 */
//        restResponse.getResponseHeader().setErrorCode(ErrorCode.EXCEPTION);
//        restResponse.getResponseHeader().setMessage("系统异常");
//
//        /* 异常返回 */
//        return restResponse;
//    }
//
//}