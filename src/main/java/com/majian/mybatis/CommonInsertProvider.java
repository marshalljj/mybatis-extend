package com.majian.mybatis;


import java.util.List;
import java.util.stream.Collectors;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by majian on 2017/12/9.
 */
public class CommonInsertProvider<T> {

    public String insert(T entity) {
        Class<?> type = entity.getClass();

        String tableName = TableParser.getTableName(type);
        List<FieldWrapper> items = CandidateFilter.getCandidateFields(type);

        List<FieldWrapper> valueParts = items.stream()
            .filter(item -> !item.isId())
            .filter(item -> !item.isVersion())
            .collect(Collectors.toList());

        return new SQL() {{
            INSERT_INTO(tableName);
            valueParts.forEach(item -> {
                VALUES(item.getColumnName(), item.getFieldToken());
            });
        }}.toString();
    }




}
