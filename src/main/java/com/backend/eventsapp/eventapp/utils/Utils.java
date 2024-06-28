package com.backend.eventsapp.eventapp.utils;

import java.beans.FeatureDescriptor;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class Utils {
    public static ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors()
                .forEach(err -> errors.put(err.getField(), "Campo " + err.getField() + " " + err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    public static String[] getNullAndExcludedPropertyNames(Object source, String... excludedProperties) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        String[] nullPropertyNames = Stream.of(src.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> src.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);

        String[] result = Stream.concat(Stream.of(nullPropertyNames), Stream.of(excludedProperties))
                .distinct()
                .toArray(String[]::new);

        return result;
    }
}
