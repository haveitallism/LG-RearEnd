package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {

    int code;
    String message;
    T data;

    public ResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public ResponseEntity(int code, T data) {
        this.code = code;
        this.data = data;
    }
}
