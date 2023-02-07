package com.easyoops.biz.sample.repository;

import com.easyoops.biz.sample.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleRepository extends JpaRepository<SampleEntity, Integer> {

    List<SampleEntity> findByContents(String keyword);
    List<SampleEntity> findByTitle(String keyword);
    List<SampleEntity> findByTitleLike(String keyword);

    @Query(value = "select no, title, contents from sample where title like %:keyword%", nativeQuery = true)
    List<SampleInterface> selectSampleSearch(@Param(value = "keyword") String keyword);
}
