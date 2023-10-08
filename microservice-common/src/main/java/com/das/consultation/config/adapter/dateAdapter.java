package com.das.consultation.config.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * created by jun on 2021/3/10
 * describe:
 * version 1.0
 */
public class dateAdapter extends XmlAdapter<String ,Date> {
    private SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    @Override
    public Date unmarshal(String v) throws Exception {
        Date result = null;
        if (!"无".equals(v)) {
            result = yyyyMMddHHmm.parse(v);
        }
        return result;
    }

    @Override
    public String marshal(Date v) {
        return yyyyMMddHHmm.format(v);
    }

}
