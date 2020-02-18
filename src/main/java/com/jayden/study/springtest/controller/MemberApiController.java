package com.jayden.study.springtest.controller;

import com.jayden.study.springtest.controller.dto.MemberResponseDto;
import com.jayden.study.springtest.controller.dto.MemberSaveRequestDto;
import com.jayden.study.springtest.controller.dto.MemberSaveResponseDto;
import com.jayden.study.springtest.entity.Member;
import com.jayden.study.springtest.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/{id}")
    public Result getMember(@PathVariable Long id) {
        Member findMember = memberService.findById(id);
        MemberResponseDto data = new MemberResponseDto(findMember);
        return new Result(data);
    }

    @GetMapping
    public Result getMemberList() {
        List<Member> members = memberService.findAll();
        List<MemberResponseDto> data = members.stream()
            .map(MemberResponseDto::new)
            .collect(toList());
        return new Result(data);
    }

    @PostMapping
    public Result saveMember(@RequestBody MemberSaveRequestDto request) {
        Member savedMember = memberService.save(request);
        MemberSaveResponseDto data = new MemberSaveResponseDto(savedMember);
        return new Result(data);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
