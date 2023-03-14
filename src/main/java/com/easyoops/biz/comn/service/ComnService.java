package com.easyoops.biz.comn.service;

import com.easyoops.biz.comn.dto.ComnCodeDto;
import com.easyoops.biz.comn.dto.ComnSearchDto;
import com.easyoops.biz.comn.entity.ComnCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ComnService {
    private final EntityManager em;

    public List<ComnCodeDto> findAll(ComnSearchDto comnSearchDto) {
        List<ComnCode> allBySearchList = findAllBySearch(comnSearchDto);
        List<ComnCodeDto> comnCodeDtoList = new ArrayList<>();
        for (int i=0; i<allBySearchList.size(); i++){
            ComnCodeDto comnCodeDto = new ComnCodeDto();
            comnCodeDto.setComnGrpCd(allBySearchList.get(i).getComnGrpCd());
            comnCodeDto.setComnCd(allBySearchList.get(i).getComnCd());
            comnCodeDto.setComnGrpNm(allBySearchList.get(i).getComnGrpNm());
            comnCodeDto.setComnNm(allBySearchList.get(i).getComnNm());
            comnCodeDto.setComnOrd(allBySearchList.get(i).getComnOrd());
            comnCodeDto.setComnDesc(allBySearchList.get(i).getComnDesc());
            comnCodeDtoList.add(comnCodeDto);
        }
        return comnCodeDtoList;
    }

    //동적쿼리 JPQL로 처리
    public List<ComnCode> findAllBySearch(ComnSearchDto comnSearchDto) {

        String jpql = "select c From ComnCode c where c.delYn='N'";
        //그룹코드 검색
        if (!StringUtils.isEmpty(comnSearchDto.getComnGrpCd())) {
            jpql += " and c.comnGrpCd = :comnGrpCd";
        }
        //코드 검색
        if (!StringUtils.isEmpty(comnSearchDto.getComnCd())) {
            jpql += " and c.comnCd = :comnCd";
        }

        TypedQuery<ComnCode> query = em.createQuery(jpql, ComnCode.class);
        if (!StringUtils.isEmpty(comnSearchDto.getComnGrpCd())) {
            query = query.setParameter("comnGrpCd", comnSearchDto.getComnGrpCd());
        }
        if (!StringUtils.isEmpty(comnSearchDto.getComnCd())) {
            query = query.setParameter("comnCd", comnSearchDto.getComnCd());
        }

        return query.getResultList();
    }

    
}
