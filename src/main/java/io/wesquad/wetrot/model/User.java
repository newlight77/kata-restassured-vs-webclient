package io.wesquad.wetrot.model;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    private UUID uid;
    private String username;
    private String lastname;
    private String firstname;
    private String email;
    private int age;
}
