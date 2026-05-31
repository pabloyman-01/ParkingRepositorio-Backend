package com.parkcontrol.backend.common.exception;

public class BusinessException extends RuntimeException {
    private final int statusCode;

    public BusinessException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public BusinessException(String message) {
        this(message, 400);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public static BusinessException notFound(String resource) {
        return new BusinessException(resource + " no encontrado", 404);
    }

    public static BusinessException conflict(String message) {
        return new BusinessException(message, 409);
    }

    public static BusinessException unauthorized(String message) {
        return new BusinessException(message, 401);
    }

    public static BusinessException forbidden(String message) {
        return new BusinessException(message, 403);
    }
}
