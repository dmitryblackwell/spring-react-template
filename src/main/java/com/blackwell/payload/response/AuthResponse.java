package com.blackwell.payload.response;

import com.blackwell.payload.UserSummary;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class AuthResponse {
    private UserSummary user;
}
