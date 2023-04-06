package com.easyoops.biz.exam.repository;

import com.easyoops.biz.exam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

    Optional<Exam> findByPubExamNo(String pubExamNo);
}
