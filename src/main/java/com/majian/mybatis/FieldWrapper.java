package com.majian.mybatis;

import com.google.common.base.CaseFormat;
import java.lang.reflect.Field;

/**
 * Created by majian on 2017/12/9.
 */
class FieldWrapper {

    Field field;

    public FieldWrapper(Field field) {
        this.field = field;
    }

    boolean isId() {
        return field.isAnnotationPresent(Id.class);
    }

    boolean isVersion() {
        return field.isAnnotationPresent(Version.class);
    }

    String getColumnName() {
        if (field.isAnnotationPresent(Column.class)) {
            return field.getAnnotation(Column.class).value();
        }
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, getFieldName());
    }

    private String getFieldName() {
        return field.getName();
    }

    public String getFieldToken() {
        return String.format("#{%s}", getFieldName());
    }
}
