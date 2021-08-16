package com.everis.bcvipaccount.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AccountClientResponseDto {
    private String message;
    private HttpStatus status;
    private Map<String, Object> body;
}
