package com.das.consultation.config.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * created by jun on 2021/3/9
 * describe:
 * version 1.0
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private static final String formater="yyyy-MM-dd";
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        Optional<String> optional=Optional.ofNullable(v);
        if(optional.isPresent()){
            return null;
        }
        return LocalDate.parse(v, DateTimeFormatter.ofPattern(formater));
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.format(DateTimeFormatter.ofPattern(formater));
    }

}
