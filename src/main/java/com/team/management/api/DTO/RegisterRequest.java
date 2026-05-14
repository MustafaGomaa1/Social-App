package com.team.management.api.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RegisterRequest {

    @NotEmpty(message = "Required Field")
    private String username;
    @NotEmpty(message = "Required Field")
    @Email
    private String email;
    @NotEmpty(message = "Required Field")
    private String password;
}
