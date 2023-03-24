package com.easyoops.biz.exam.service;

import com.easyoops.biz.exam.dto.ExamRequestDto;
import com.easyoops.biz.exam.entity.Answer;
import com.easyoops.biz.exam.entity.Exam;
import com.easyoops.biz.exam.entity.Question;
import com.easyoops.biz.exam.repository.AnswerRepository;
import com.easyoops.biz.exam.repository.ExamRepository;
import com.easyoops.biz.exam.repository.QuestionRepository;
import com.easyoops.common.config.AppValueConfig;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ExamService {

    private static final Logger LOG = LoggerFactory.getLogger("BIZ_LOGGER");

    private AppValueConfig appConfig;
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Transactional(rollbackFor = Exception.class)
    public Exam createExam(ExamRequestDto examRequestDto) throws Exception{

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        if(examRequestDto.getQst_list() == null){
            LOG.error("pubExamNo : {}, qstList is null", examRequestDto.getPub_exam_no());
            throw new IllegalArgumentException();
        }

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


        examRequestDto.getQst_list().parallelStream().forEach(qst -> {
            Question question = Question.builder()
                        .examNo(exam.getExamNo())
                        .qstCont(qst.getQst_cont())
                        .createId(appConfig.getAppDbAdmin())
                        .updateId(appConfig.getAppDbAdmin())
                    .build();

            questionRepository.save(question);

            qst.getAns_list().parallelStream().forEach(ans -> {
                Answer answer = Answer.builder()
                        .examNo(exam.getExamNo())
                        .qstNo(question.getQstNo())
                        .ansCont(ans.getAns_cont())
                        .ansHitYn(ans.getAns_hit_yn())
                        .createId(appConfig.getAppDbAdmin())
                        .updateId(appConfig.getAppDbAdmin())
                    .build();

                answerRepository.save(answer);
            });
        });

        stopWatch.stop();
        LOG.warn(stopWatch.toString());

        return exam;
    }
}
