package com.das.consultation.config.Listener;

import javax.xml.bind.Marshaller;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * created by jun on 2020/12/4
 * describe:
 * version 1.0
 */
public class MarshallerListener extends Marshaller.Listener {
    private static final String BLANK_CHAR = "";

    @Override
    public void beforeMarshal(Object source) {
        super.beforeMarshal(source);
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            try {
                if (f.getType() == String.class && f.get(source) == null) {
                    f.set(source, BLANK_CHAR);
                } else if (f.getType() == Date.class && f.get(source) == null) {
                    f.set(source, new Date(0));
                }
                if (f.getType() == LocalDateTime.class && f.get(source) == null) {
                    f.set(source, BLANK_CHAR);
                }
                if (f.getType() == LocalDate.class && f.get(source) == null) {
                    f.set(source, BLANK_CHAR);
                }
                if (f.getType() == BigDecimal.class && f.get(source) == null) {
                    f.set(source, 0);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
