package com.jayden.study.springtest.repository;

import com.jayden.study.springtest.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("멤버 단건 조회 테스트")
    void findMemberByIdTest() {
        // given
        String username = "java";
        int age = 15;

        Member member = createMember(username, age);

        testEntityManager.persist(member);

        // when
        Member findMember = memberRepository.findByUsername(username);

        // then
        assertThat(findMember).isNotNull();
        assertThat(findMember.getUsername()).isEqualTo(username);
        assertThat(findMember.getAge()).isEqualTo(age);
    }

    @Test
    @DisplayName("멤버 목록 조회 테스트")
    void findMemberAllTest() {
        // given
        Member member1 = createMember("java", 14);
        Member member2 = createMember("kotlin", 5);
        Member member3 = createMember("go", 4);

        testEntityManager.persist(member1);
        testEntityManager.persist(member2);
        testEntityManager.persist(member3);

        // when
        List<Member> members = memberRepository.findAll();

        // then
        assertThat(members).isNotNull();
        assertThat(members).hasSize(3);
        assertThat(members).extracting(Member::getUsername).contains("java", "kotlin", "go");
    }

    private Member createMember(String username, int age) {
        return Member.builder()
            .username(username)
            .age(age)
            .build();
    }
}