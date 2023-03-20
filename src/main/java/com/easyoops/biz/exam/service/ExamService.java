package com.easyoops.biz.exam.service;

import com.easyoops.biz.exam.dto.ExamRequestDto;
import com.easyoops.biz.exam.entity.Exam;
import com.easyoops.biz.exam.repository.ExamRepository;
import com.easyoops.common.config.AppValueConfig;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ExamService {

    private static final Logger LOG = LoggerFactory.getLogger("BIZ_LOGGER");

    private AppValueConfig appConfig;
    private final ExamRepository examRepository;

    @Transactional(rollbackFor = Exception.class)
    public Exam createExam(ExamRequestDto examRequestDto){

        Exam exam = Exam.builder()
                .pubExamNo(examRequestDto.getPub_exam_no())
                .examOpenYn(examRequestDto.getExam_open_yn())
                .examRegTp(examRequestDto.getExam_reg_tp())
                .examRndYn(examRequestDto.getExam_rnd_yn())
                .dumpFile(examRequestDto.getDump_file())
                .qstCnt(examRequestDto.getQst_list().size())
                .createId(appConfig.getAppDbAdmin())
                .updateId(appConfig.getAppDbAdmin())
                .build();

        examRepository.save(exam);

        return exam;
    }
}
