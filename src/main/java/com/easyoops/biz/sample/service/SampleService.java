package com.easyoops.biz.sample.service;

import com.easyoops.biz.sample.entity.Sample;
import com.easyoops.biz.sample.repository.SampleInterface;
import com.easyoops.biz.sample.repository.SampleRepository;
import com.easyoops.common.config.AppValueConfig;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SampleService {

    private static final Logger LOG = LoggerFactory.getLogger("BIZ_LOGGER");

    private AppValueConfig appConfig;
    private SampleRepository sampleRepository;

    // default > .findAll()
    @Transactional(rollbackFor = Exception.class)
    public List<Sample> selectSampleList(){
        LOG.debug("selectSampleList");
        List<Sample> samples = new ArrayList<>();
        sampleRepository.findAll().forEach(e -> samples.add(e));
        return samples;
    }

    // default > .findById()
    @Transactional(rollbackFor = Exception.class)
    public Sample selectSampleView(Integer no){
        LOG.debug("selectSampleView");
        return sampleRepository.findById(no).get();
    }

    // default > .save()
    @Transactional(rollbackFor = Exception.class)
    public Sample createSample(Sample sample){
        LOG.debug("createSample");
        sample.setCreateId(appConfig.getAppDbWriter());
        sample.setUpdateId(appConfig.getAppDbWriter());
        sampleRepository.save(sample);
        return sample;
    }

    // default > .save() .builder()
    @Transactional(rollbackFor = Exception.class)
    public Sample updateSample(Sample sample) {
        LOG.debug("updateSample");
        Optional<Sample> e = sampleRepository.findById(sample.getNo());

        if (e.isPresent()) {
            sampleRepository.save(
                    Sample.builder()
                            .no(sample.getNo())
                            .title(sample.getTitle())
                            .contents(sample.getContents())
                            .updateId(appConfig.getAppDbWriter())
                    .build());
        }
        return null;
    }

    // default > .deleteById()
    @Transactional(rollbackFor = Exception.class)
    public Sample deleteSample(Integer no) {
        LOG.debug("deleteSample");
        sampleRepository.deleteById(no);
        return null;
    }

    // queryMethod > .findByTitle()
    @Transactional(rollbackFor = Exception.class)
    public List<Sample> searchTitle(String keyword) {
        LOG.debug("searchTitle");
        return sampleRepository.findByTitle(keyword);
    }

    // queryMethod > .findByContents()
    @Transactional(rollbackFor = Exception.class)
    public List<Sample> searchContents(String keyword) {
        LOG.debug("searchContents");
        return sampleRepository.findByContents(keyword);
    }

    // queryMethod > .findByTitleLike()
    @Transactional(rollbackFor = Exception.class)
    public List<Sample> searchLikeTitle(String keyword) {
        LOG.debug("searchLikeTitle");
        return sampleRepository.findByTitleLike(keyword);
    }

    // nativeQuery > .selectSampleSearch()
    @Transactional(rollbackFor = Exception.class)
    public List<SampleInterface> selectSampleSearch(String keyword) {
        LOG.debug("searchSample");
        List<SampleInterface> samples = new ArrayList<>();
        sampleRepository.selectSampleSearch(keyword).forEach(e -> samples.add(e));
        return samples;
    }
}
