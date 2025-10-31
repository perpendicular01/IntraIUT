package com.iut.intraiutserver.payloads;

import lombok.Data;

@Data // Lombok's annotation for @Getter, @Setter, @ToString, @EqualsAndHashCode
public class JwtAuthRequest {

    private String username; // This will hold the user's email

    private String password;

}