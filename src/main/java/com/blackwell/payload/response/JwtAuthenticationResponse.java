package com.blackwell.payload.response;

import com.blackwell.payload.UserSummary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JwtAuthenticationResponse extends AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken, UserSummary userSummary) {
        super(userSummary);
        this.accessToken = accessToken;
    }
}
