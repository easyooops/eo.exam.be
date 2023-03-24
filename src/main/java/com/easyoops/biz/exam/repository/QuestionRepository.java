package com.easyoops.biz.exam.repository;

import com.easyoops.biz.exam.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
