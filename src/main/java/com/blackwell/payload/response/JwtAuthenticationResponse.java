package com.blackwell.payload.response;

import com.blackwell.payload.UserSummary;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter @Setter
public class JwtAuthenticationResponse extends AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken, UserSummary userSummary) {
        super(userSummary);
        this.accessToken = accessToken;
    }
}
