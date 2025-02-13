package com.example.schedulerv2.member.repository;

import com.example.schedulerv2.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
