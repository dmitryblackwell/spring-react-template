package com.blackwell.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
