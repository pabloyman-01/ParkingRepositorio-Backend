package com.parkcontrol.backend.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private PaginationMeta meta;

    public static <T> ApiResponse<T> ok(T data) {
        return ApiResponse.<T>builder().success(true).data(data).build();
    }

    public static <T> ApiResponse<T> ok(T data, PaginationMeta meta) {
        return ApiResponse.<T>builder().success(true).data(data).meta(meta).build();
    }

    public static <T> ApiResponse<T> created(T data) {
        return ApiResponse.<T>builder().success(true).data(data).build();
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder().success(false).message(message).build();
    }

    public static <T> ApiResponse<T> error(String message, T details) {
        return ApiResponse.<T>builder().success(false).message(message).data(details).build();
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class PaginationMeta {
        private long total;
        private int page;
        private int limit;
        private int totalPages;
    }
}
