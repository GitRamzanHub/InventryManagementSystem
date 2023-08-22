package com.inventrymanagementsystem.ramzan.model;

import com.inventrymanagementsystem.ramzan.resource.UserResource;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;


    private String username;


    private String email;


    private String password;

    private String role = "USER";

    // UserResource to UserResource
    public static User toUserEntity(UserResource userResource){
        return User.builder()
                .user_id(userResource.getUserId())
                .username(userResource.getUsername())
                .email(userResource.getEmail())
                .password(userResource.getPassword())
                .role(userResource.getRole())
                .build();
    }

    public static UserResource toUserResource(User user){
        return UserResource.builder()
                .userId(user.getUser_id())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
