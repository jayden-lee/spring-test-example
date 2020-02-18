package com.jayden.study.springtest.repository;

import com.jayden.study.springtest.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByUsername(String username);

}
