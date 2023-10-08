package com.das.consultation.config.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * created by jun on 2021/3/9
 * describe:
 * version 1.0
 */
public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
    private static final String formater="yyyy-MM-dd hh:mm:ss";
    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        Optional<String> optional=Optional.ofNullable(v);
        if(optional.isPresent()){
            return null;
        }
        return LocalDateTime.parse(v, DateTimeFormatter.ofPattern(formater));
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return v.format(DateTimeFormatter.ofPattern(formater));
    }

}
