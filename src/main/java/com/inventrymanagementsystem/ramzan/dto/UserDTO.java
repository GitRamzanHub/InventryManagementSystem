package com.inventrymanagementsystem.ramzan.dto;

import com.inventrymanagementsystem.ramzan.enums.Role;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class UserDTO {
    private Long userId;

    @UniqueElements
    @NotBlank(message = "username is Mandatory")
    private String username;

    @NotBlank(message = "Email is Mandatory")
    private String email;

    @NotBlank(message = "password is Mandatory")
    private String password;

    private Role role;
}
