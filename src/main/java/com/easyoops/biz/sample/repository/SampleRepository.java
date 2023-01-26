package com.easyoops.biz.sample.repository;

import com.easyoops.biz.sample.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Long> {

    SampleEntity findByNo(String no);
}
