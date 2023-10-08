package com.das.consultation.advice;

import com.alibaba.fastjson.JSONException;
import com.das.consultation.entities.XmlMessage;
import com.das.consultation.exception.DateException;
import com.das.consultation.exception.EmptyResultException;
import com.das.consultation.exception.NotFoundException;
import com.das.consultation.exception.ParamErrorException;
import com.das.consultation.utils.JsonXmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.DocumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.JAXBException;
import java.sql.SQLIntegrityConstraintViolationException;


/**
 * created by jun on 2020/9/21
 * describe:统一抛错错误返回
 * version 1.0
 */
@Slf4j
@RestControllerAdvice
public class DasControllerAdvice {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> responseException(Exception e) {
        XmlMessage resErr = new XmlMessage();
        if (e instanceof JAXBException ||e instanceof DocumentException) {
            resErr.setResult("1008");
            resErr.setDesc("xml格式错误");
        } else {
            resErr.setResult("1001");
            resErr.setDesc(e.toString());
        }

        return new ResponseEntity<>(JsonXmlUtils.jsonToXml(null, resErr), HttpStatus.OK);
    }

    @ExceptionHandler(EmptyResultException.class)
    public ResponseEntity<String> responseException(EmptyResultException e) {
        XmlMessage resErr = new XmlMessage();
        resErr.setResult("1002");
        resErr.setDesc(e.getMessage());
        return new ResponseEntity<>(JsonXmlUtils.jsonToXml(null, resErr), HttpStatus.OK);
    }

    @ExceptionHandler(ParamErrorException.class)
    public ResponseEntity<String> responseException(ParamErrorException e) {
        XmlMessage resErr = new XmlMessage();
        resErr.setResult("1003");
        resErr.setDesc(e.getMessage());
        return new ResponseEntity<>(JsonXmlUtils.jsonToXml(null, resErr), HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> responseException(NotFoundException e) {
        XmlMessage resErr = new XmlMessage();
        resErr.setResult("1004");
        resErr.setDesc(e.getMessage());
        return new ResponseEntity<>(JsonXmlUtils.jsonToXml(null, resErr), HttpStatus.OK);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> responseException(SQLIntegrityConstraintViolationException e) {
        XmlMessage resErr = new XmlMessage();
        resErr.setResult("0");
        resErr.setDesc(e.getMessage());
        return new ResponseEntity<>(JsonXmlUtils.jsonToXml(null, resErr), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> responseException(IllegalArgumentException e) {
        XmlMessage resErr = new XmlMessage();
        resErr.setResult("1006");
        resErr.setDesc(e.getMessage());
        return new ResponseEntity<>(JsonXmlUtils.jsonToXml(null, resErr), HttpStatus.OK);
    }

    @ExceptionHandler(JSONException.class)
    public ResponseEntity<String> responseException() {
        XmlMessage resErr = new XmlMessage();
        resErr.setResult("1007");
        resErr.setDesc("json格式错误");
        return new ResponseEntity<>(JsonXmlUtils.jsonToXml(null, resErr), HttpStatus.OK);
    }
    @ExceptionHandler(DateException.class)
    public ResponseEntity<String> dateException(DateException e) {
        XmlMessage resErr = new XmlMessage();
        resErr.setResult("1009");
        resErr.setDesc(e.getMessage());
        return new ResponseEntity<>(JsonXmlUtils.jsonToXml(null, resErr), HttpStatus.OK);
    }



}
