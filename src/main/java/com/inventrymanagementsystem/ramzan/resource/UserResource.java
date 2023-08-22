package com.inventrymanagementsystem.ramzan.resource;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class UserResource {
    private Long userId;

    @UniqueElements
    @NotBlank(message = "username is Mandatory")
    private String username;

    @NotBlank(message = "Email is Mandatory")
    private String email;

    @NotBlank(message = "password is Mandatory")
    private String password;

    private String role = "USER";

}
