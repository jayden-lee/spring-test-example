package com.jayden.study.springtest.controller.dto;

import com.jayden.study.springtest.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private Long id;
    private String username;
    private int age;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.age = member.getAge();
    }
}
