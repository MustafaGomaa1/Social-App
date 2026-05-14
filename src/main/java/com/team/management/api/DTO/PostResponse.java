package com.team.management.api.DTO;

import java.time.LocalDateTime;

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

public class PostResponse {
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
