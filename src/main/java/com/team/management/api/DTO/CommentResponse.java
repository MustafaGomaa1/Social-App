package com.team.management.api.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CommentResponse {

    private String content;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
