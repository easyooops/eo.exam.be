package com.easyoops.biz.comn.service;

import com.easyoops.biz.comn.entity.ComnCode;
import com.easyoops.biz.comn.repository.ComnRepository;
import com.easyoops.biz.sample.entity.Sample;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ComnService {
    private final ComnRepository comnRepository;

    public List<ComnCode> findComnCodeList() {
        log.debug("selectComnCodeLIst");
        List<ComnCode> comnCodes = new ArrayList<>();
        comnRepository.findAll().forEach(e -> comnCodes.add(e));
        return comnCodes;
    }
}
