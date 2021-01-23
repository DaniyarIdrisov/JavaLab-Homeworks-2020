package ru.kpfu.itis.group903.idrisov.daniyar.model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class User {

    @Getter @Setter private long id;
    @Getter @Setter private String login;
    @Getter @Setter private String password;
    @Getter @Setter private String UUID;

}
