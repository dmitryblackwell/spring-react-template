package com.blackwell.payload;

import com.blackwell.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserSummary {
    private String id;
    private String username;

    // TODO create some kind of a Mapper
    public UserSummary(User user) {
        this.id = user.getId().toString();
        this.username= user.getUsername();
    }
}
