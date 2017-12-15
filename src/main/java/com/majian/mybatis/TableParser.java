package com.majian.mybatis;

/**
 * Created by majian on 2017/12/9.
 */
public class TableParser {
    public static String getTableName(Class<?> type) {
        Table table = type.getAnnotation(Table.class);
        if (table == null) {
            throw new IllegalArgumentException("@Table must exist");
        }
        return table.value();
    }
}
