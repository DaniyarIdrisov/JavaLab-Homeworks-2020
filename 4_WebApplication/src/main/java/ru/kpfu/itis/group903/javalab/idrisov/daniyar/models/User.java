package ru.kpfu.itis.group903.javalab.idrisov.daniyar.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Integer group;

}
