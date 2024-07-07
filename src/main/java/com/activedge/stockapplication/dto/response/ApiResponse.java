package com.activedge.stockapplication.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T>{
    private String code;
    private String message;
    private T data;
    private String status;

    @JsonIgnore
    private HttpStatus httpStatus;

    public ApiResponse(String code, String message, T data, String status) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.status = status;
    }
}
