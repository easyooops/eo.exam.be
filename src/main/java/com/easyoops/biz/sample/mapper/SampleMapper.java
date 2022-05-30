package com.easyoops.biz.sample.mapper;

import com.easyoops.biz.sample.dto.SampleDTO;

import java.util.List;

public interface SampleMapper {
    List<SampleDTO> selectSampleList(SampleDTO sampleDTO);
    SampleDTO selectSampleView(String no);
    void createSample(SampleDTO sampleDTO);
    void updateSample(SampleDTO sampleDTO);
    int deleteSample(String no);
}