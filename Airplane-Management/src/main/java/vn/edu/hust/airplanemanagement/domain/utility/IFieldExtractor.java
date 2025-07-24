package vn.edu.hust.airplanemanagement.domain.utility;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface IFieldExtractor {
    Set<String> extract(Class<?> className);
}
