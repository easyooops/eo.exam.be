package com.easyoops.biz.exam.repository;

import com.easyoops.biz.exam.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
}
