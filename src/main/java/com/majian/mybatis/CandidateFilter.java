package com.majian.mybatis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by majian on 2017/12/9.
 */
public class CandidateFilter {
    public static List<FieldWrapper> getCandidateFields(Class<?> type) {
        return Arrays.stream(type.getDeclaredFields())
            .filter(field -> !field.isAnnotationPresent(Transient.class))
            .map(FieldWrapper::new)
            .collect(Collectors.toList());
    }
}
