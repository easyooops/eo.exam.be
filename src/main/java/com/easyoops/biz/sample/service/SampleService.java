package com.easyoops.biz.sample.service;

import com.easyoops.biz.sample.dto.SampleDTO;
import com.easyoops.biz.sample.entity.SampleEntity;
import com.easyoops.biz.sample.mapper.SampleMapper;
import com.easyoops.biz.sample.repository.SampleRepository;
import com.easyoops.common.config.DataSourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SampleService {

    private static final Logger LOG = LoggerFactory.getLogger("BIZ_LOGGER");

    @Autowired
    private SampleMapper sampleMapper;

    @Autowired
    private SampleRepository sampleRepository;

    @Transactional(rollbackFor = Exception.class)
    public List<SampleDTO> selectSampleList(SampleDTO sampleDTO){
        return sampleMapper.selectSampleList(sampleDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    public SampleDTO selectSampleView(String no){
        LOG.debug("selectSampleView");
        return sampleMapper.selectSampleView(no);
    }

    @Transactional(rollbackFor = Exception.class)
    public SampleDTO createSample(SampleDTO sampleDTO){
        sampleDTO.setCreateId("admin");
        sampleDTO.setUpdateId("admin");
        sampleMapper.createSample(sampleDTO);
        return sampleDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public SampleDTO updateSample(SampleDTO sampleDTO){
        sampleDTO.setUpdateId("admin");
        sampleMapper.updateSample(sampleDTO);
        return sampleDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteSample(String no){
        return sampleMapper.deleteSample(no) == 1 ?
                Boolean.TRUE : Boolean.FALSE;
    }

    public SampleEntity findByNo(String no) {
        return sampleRepository.findByNo(no);
    }
}
