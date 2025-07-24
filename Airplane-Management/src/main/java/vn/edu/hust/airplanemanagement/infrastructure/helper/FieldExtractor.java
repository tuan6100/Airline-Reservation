package vn.edu.hust.airplanemanagement.infrastructure.helper;

import vn.edu.hust.airplanemanagement.domain.utility.IFieldExtractor;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class FieldExtractor implements IFieldExtractor {
    @Override
    public Set<String> extract(Class<?> className) {
        Field[] fields = className.getDeclaredFields();
        Set<String> fieldNames = new HashSet<>();
        for(var field: fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }
}
