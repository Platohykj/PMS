package org.example.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Response <T> {
    private Integer code;
    private String message;
    private T content;

    public static <K> Response<K> newSuccess(K data, String message) {
        Response<K> response = new Response<>();
        response.setCode(HttpStatus.OK.value());
        response.setMessage(message);
        response.setContent(data);
        return response;
    }

    public static Response<Void> newError(Integer Status, String errorMessage) {
        Response<Void> response = new Response<>();
        response.setCode(Status);
        response.setMessage(errorMessage);
        return response;
    }

}

