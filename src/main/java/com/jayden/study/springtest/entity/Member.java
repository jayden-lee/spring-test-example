package com.jayden.study.springtest.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private int age;

    @Builder
    public Member(Long id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public void update(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
