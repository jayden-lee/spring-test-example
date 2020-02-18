package com.jayden.study.springtest.controller.dto;

import com.jayden.study.springtest.entity.Member;
import lombok.Getter;

@Getter
public class MemberSaveResponseDto {

    private Long id;

    public MemberSaveResponseDto(Member member) {
        this.id = member.getId();
    }
}
