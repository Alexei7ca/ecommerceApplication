package com.gft.ecommerce.shared.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String error;
    private String timestamp;
}
