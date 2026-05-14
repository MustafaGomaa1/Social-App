package com.team.management.api.DTO;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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

public class ProfileRequest {

    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String photoUrl;
    @CreationTimestamp
    private LocalDateTime createTime;
}
