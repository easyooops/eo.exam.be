package com.easyoops.biz.comn.service;

import com.easyoops.biz.comn.dto.ComnCodeDto;
import com.easyoops.biz.comn.entity.ComnCode;
import com.easyoops.biz.comn.repository.ComnRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ComnService {
    private final ComnRepository comnRepository;

    public List<ComnCodeDto> findAll() {
        log.debug("selectComnCodeList");
        List<ComnCode> comnCodes = new ArrayList<>();
        comnRepository.findAll().stream()
                .filter(s -> "N".equals(s.getDelYn())).collect(Collectors.toList())
                .forEach(c -> comnCodes.add(c));

        List<ComnCodeDto> comnCodeDtoList = new ArrayList<>();
        for (ComnCode comnCode : comnCodes){
            ComnCodeDto comnCodeDto = new ComnCodeDto();
            comnCodeDto.setComnGrpCd(comnCode.getComnGrpCd());
            comnCodeDto.setComnCd(comnCode.getComnCd());
            comnCodeDto.setComnGrpNm(comnCode.getComnGrpNm());
            comnCodeDto.setComnNm(comnCode.getComnNm());
            comnCodeDto.setComnOrd(comnCode.getComnOrd());
            comnCodeDto.setComnDesc(comnCode.getComnDesc());
            comnCodeDtoList.add(comnCodeDto);
        }
        return comnCodeDtoList;
    }
    
}
