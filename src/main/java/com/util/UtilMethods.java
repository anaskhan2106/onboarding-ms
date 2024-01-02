package com.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class UtilMethods {

    public static boolean isValidUUID(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (Exception var2) {
            log.error("Invalid UUID provided: {}", uuid);
            return false;
        }
    }

    public static void validateRequired(String value, String field) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(field+" cannot be null or empty.");
        }
    }


}
