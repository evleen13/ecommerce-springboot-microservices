package com.evleen.userservice.dto;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ApiErrorResponseDtoTests {

    @Test
    public void testConstructorAndGetters() {
        LocalDateTime dateTime = LocalDateTime.now();
        String code = "ERROR_CODE";
        String message = "Error message";
        String details = "Error details";

        ApiErrorResponseDto dto = new ApiErrorResponseDto(dateTime, code, message, details);

        assertEquals(dateTime, dto.getDateTime());
        assertEquals(code, dto.getCode());
        assertEquals(message, dto.getMessage());
        assertEquals(details, dto.getDetails());
    }

    @Test
    public void testSetters() {
        LocalDateTime dateTime = LocalDateTime.now();
        String code = "ERROR_CODE";
        String message = "Error message";
        String details = "Error details";

        ApiErrorResponseDto dto = new ApiErrorResponseDto();

        dto.setDateTime(dateTime);
        dto.setCode(code);
        dto.setMessage(message);
        dto.setDetails(details);

        assertEquals(dateTime, dto.getDateTime());
        assertEquals(code, dto.getCode());
        assertEquals(message, dto.getMessage());
        assertEquals(details, dto.getDetails());
    }
}

