package com.jayden.study.springtest.controller.dto;

import com.jayden.study.springtest.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSaveRequestDto {

    private String username;
    private int age;

    @Builder
    public MemberSaveRequestDto(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public Member toEntity() {
        return Member.builder()
            .username(username)
            .age(age)
            .build();
    }
}
