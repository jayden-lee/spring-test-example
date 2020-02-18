package com.jayden.study.springtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayden.study.springtest.controller.dto.MemberSaveRequestDto;
import com.jayden.study.springtest.entity.Member;
import com.jayden.study.springtest.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ActiveProfiles("test")
class MemberApiControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MemberService memberService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("멤버 단건 조회 테스트")
    public void getOneMemberTest() throws Exception {
        // given
        Long id = 1L;
        String username = "java";
        int age = 15;

        Member member = createMember(id, username, age);

        when(memberService.findById(id)).thenReturn(member);

        // when
        ResultActions actions = mvc.perform(get("/api/v1/members/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print());

        // then
        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.id").value(id))
            .andExpect(jsonPath("$.data.username").value(username))
            .andExpect(jsonPath("$.data.age").value(age));
    }

    @Test
    @DisplayName("멤버 목록 조회 테스트")
    public void getMemberListTest() throws Exception {
        // given
        Member member1 = createMember(1L, "java", 15);
        Member member2 = createMember(2L, "kotlin", 4);
        Member member3 = createMember(3L, "go", 3);

        List<Member> members = List.of(member1, member2, member3);

        when(memberService.findAll()).thenReturn(members);

        // when
        ResultActions actions = mvc.perform(get("/api/v1/members")
            .contentType(MediaType.APPLICATION_JSON))
            .andDo(print());

        // then
        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data", hasSize(3)));
    }

    @Test
    @DisplayName("멤버 생성 테스트")
    void createMemberTest() throws Exception {
        // given
        Long id = 1L;
        String username = "java";
        int age = 15;

        MemberSaveRequestDto requestDto = MemberSaveRequestDto.builder()
            .username(username)
            .age(age)
            .build();

        Member member = createMember(id, username, age);

        when(memberService.save(any(MemberSaveRequestDto.class))).thenReturn(member);

        // when
        ResultActions actions = mvc.perform(post("/api/v1/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsBytes(requestDto)))
            .andDo(print());

        // then
        actions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.id").value(id));
    }

    private Member createMember(Long id, String username, int age) {
        return Member.builder()
            .id(id)
            .username(username)
            .age(age)
            .build();
    }
}