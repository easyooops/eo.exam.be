package com.easyoops.biz.member.repository;

import com.easyoops.biz.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmailId(String username);
}
