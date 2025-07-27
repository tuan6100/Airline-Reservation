package vn.edu.hust.common.domain.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;


public final class FieldExtractor  {

    private FieldExtractor() {}

    public static Set<String> extract(Class<?> className) {
        Field[] fields = className.getDeclaredFields();
        Set<String> fieldNames = new HashSet<>();
        for(var field: fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }
}