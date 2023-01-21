package org.course.mscsdocker.mscsdocker.application.helpers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

public class Validator {
    public static ResponseEntity<Map<String, String>> getResultValidation(BindingResult result) {
        Map<String,String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(),err.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
