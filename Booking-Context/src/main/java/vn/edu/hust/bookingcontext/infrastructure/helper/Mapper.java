package vn.edu.hust.bookingmanagement.infrastructure.helper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.RecordComponent;
import java.util.Arrays;

@Slf4j
public class Mapper {

    public static <F, T> T map(F fromObject, Class<T> toType, @Nullable String... excludedFields) {
        try {
            T toObject = toType.getDeclaredConstructor().newInstance();
            Arrays.stream(fromObject.getClass().getDeclaredFields())
                    .forEach(field -> {
                        field.setAccessible(true);
                        try {
                            Object value = field.get(fromObject);
                            try {
                                String fieldName = field.getName();
                                if (excludedFields != null && Arrays.asList(excludedFields).contains(fieldName)) {
                                    return;
                                }
                                java.lang.reflect.Field toField = toType.getDeclaredField(fieldName);
                                toField.setAccessible(true);
                                toField.set(toObject, value);
                            } catch (NoSuchFieldException ignored) {
                            }
                        } catch (IllegalAccessException e) {
                            log.error(e.getMessage());
                            throw new RuntimeException(e);
                        }
                    });
            return toObject;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static <F, T extends Record> T mapToRecord(F fromObject, Class<T> toRecordType) {
        try {
            RecordComponent[] components = toRecordType.getRecordComponents();
            Class<?>[] paramTypes = Arrays.stream(components)
                    .map(RecordComponent::getType)
                    .toArray(Class[]::new);
            Constructor<T> constructor = toRecordType.getDeclaredConstructor(paramTypes);
            constructor.setAccessible(true);
            Object[] values = Arrays.stream(components)
                    .map(component -> {
                        try {
                            return getObject(fromObject, component);
                        } catch (NoSuchFieldException e) {
                            return null;
                        } catch (IllegalAccessException e) {
                            log.error("Error accessing field {}: {}", component.getName(), e.getMessage());
                            throw new RuntimeException(e);
                        }
                    })
                    .toArray();
            return constructor.newInstance(values);
        } catch (Exception e) {
            log.error("Error mapping to record: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static <F> Object getObject(F fromObject, RecordComponent component) throws NoSuchFieldException, IllegalAccessException {
        Field sourceField = fromObject.getClass().getDeclaredField(component.getName());
        sourceField.setAccessible(true);
        Object value = sourceField.get(fromObject);
        if (value != null && !component.getType().isAssignableFrom(value.getClass())) {
            throw new IllegalArgumentException(
                    String.format("Type mismatch for field %s: expected %s, got %s",
                            component.getName(), component.getType(), value.getClass()));
        }
        return value;
    }
}
