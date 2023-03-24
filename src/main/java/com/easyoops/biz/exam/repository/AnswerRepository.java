package com.easyoops.biz.exam.repository;

import com.easyoops.biz.exam.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
