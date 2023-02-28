package com.easyoops.biz.sample.repository;

import com.easyoops.biz.sample.entity.Sample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Integer> {

    List<Sample> findByContents(String keyword);
    List<Sample> findByTitle(String keyword);
    List<Sample> findByTitleLike(String keyword);

    @Query(value = "select no, title, contents from sample where title like %:keyword%", nativeQuery = true)
    List<SampleInterface> selectSampleSearch(@Param(value = "keyword") String keyword);
}
