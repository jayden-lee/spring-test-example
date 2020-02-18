package com.jayden.study.springtest.service;

import com.jayden.study.springtest.controller.dto.MemberSaveRequestDto;
import com.jayden.study.springtest.entity.Member;
import com.jayden.study.springtest.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findById(Long id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Not Found Member id=" + id));
        return member;
    }

    public Member save(MemberSaveRequestDto request) {
        return memberRepository.save(request.toEntity());
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
