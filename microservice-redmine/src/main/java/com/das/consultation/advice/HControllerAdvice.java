package com.das.consultation.advice;

import com.das.consultation.utils.JsonXmlUtils;
import com.das.consultation.entities.XmlMessage;
import com.ylzinfo.ehc.sdk.exception.SDKException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * created by jun on 2020/9/21
 * describe:统一抛错错误返回
 * version 1.0
 */
@Slf4j
@RestControllerAdvice
public class HControllerAdvice extends DasControllerAdvice {

    @ExceptionHandler(SDKException.class)
    public ResponseEntity<String> dateException(SDKException e) {
        XmlMessage resErr = new XmlMessage();
        resErr.setResult("1010");
        resErr.setDesc(e.getMessage());
        return new ResponseEntity<>(JsonXmlUtils.jsonToXml(null, resErr), HttpStatus.OK);
    }

}
