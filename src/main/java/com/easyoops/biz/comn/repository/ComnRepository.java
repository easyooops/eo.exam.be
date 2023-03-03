package com.easyoops.biz.comn.repository;

import com.easyoops.biz.comn.entity.ComnCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComnRepository extends JpaRepository<ComnCode, Integer> {

    List<ComnCode> findAll();
}
