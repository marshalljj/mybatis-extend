package com.majian.mybatis;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by majian on 2017/12/9.
 */
public class CommonUpdateProvider<T> {

    public String update(T entity) {
        Class<?> type = entity.getClass();
        String tableName = TableParser.getTableName(type);

        List<FieldWrapper> items = CandidateFilter.getCandidateFields(type);
        DivideResult divideResult = divide(items);
        return new SQL() {{
            UPDATE(tableName);
            divideResult.getValueParts().forEach(item -> {
                SET(parse(item));
            });
            SET(parseVersionIncrement(divideResult.getVersionPart()));
            WHERE(parse(divideResult.getIdPart()));
            WHERE(parse(divideResult.getVersionPart()));
        }}.toString();
    }

    private String parseVersionIncrement(FieldWrapper item) {
        String columnName = item.getColumnName();
        return String.format("%s=%s+1", columnName, columnName);
    }

    private String parse(FieldWrapper item) {
        return String.format("%s=%s", item.getColumnName(), item.getFieldToken());
    }

    private DivideResult divide(List<FieldWrapper> items) {
        DivideResult divideResult = new DivideResult();
        items.forEach(item -> {
            boolean valuesOnly = true;
            if (item.isId()) {
                divideResult.addIdItem(item);
                valuesOnly = false;
            }
            if (item.isVersion()) {
                divideResult.addVersionItem(item);
                valuesOnly = false;
            }

            if (valuesOnly) {
                divideResult.addValueItem(item);
            }

        });
        return divideResult;
    }

    static class DivideResult {

        List<FieldWrapper> idParts = Lists.newArrayList();
        List<FieldWrapper> versionParts = Lists.newArrayList();
        List<FieldWrapper> valueParts = Lists.newArrayList();

        public void addIdItem(FieldWrapper item) {
            if (idParts.size() != 0) {
                throw new IllegalArgumentException("");
            }
            idParts.add(item);
        }

        public void addVersionItem(FieldWrapper item) {
            if (versionParts.size() != 0) {
                throw new IllegalArgumentException("");
            }
            versionParts.add(item);
        }

        public void addValueItem(FieldWrapper item) {
            valueParts.add(item);
        }

        public FieldWrapper getIdPart() {
            return idParts.get(0);
        }

        public FieldWrapper getVersionPart() {
            return versionParts.get(0);
        }

        public List<FieldWrapper> getValueParts() {
            if (valueParts.size() == 0) {
                throw new IllegalArgumentException("");
            }
            return valueParts;
        }
    }
}
