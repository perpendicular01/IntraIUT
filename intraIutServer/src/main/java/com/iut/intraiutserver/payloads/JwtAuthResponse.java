package com.iut.intraiutserver.payloads;

import lombok.Data;

@Data // Lombok's annotation for @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
public class JwtAuthResponse {

    private String token;

    private UserDto user;


}