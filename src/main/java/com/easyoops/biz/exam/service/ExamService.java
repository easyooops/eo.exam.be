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

import java.util.Optional;

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

        LOG.debug("createExam parameter -> {} ", examRequestDto);

        Optional<Exam> existExamForPubExamNo = examRepository.findByPubExamNo(examRequestDto.getPub_exam_no());
        if (existExamForPubExamNo.isPresent()) {
            LOG.error("already exist pubExamNo : {}, ", examRequestDto);
            throw new IllegalArgumentException();
        }

        //question validation
        if(examRequestDto.getQst_list() == null){
            LOG.error("qstList not exist : {}", examRequestDto);
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

            //answer validation
            long answerCnt = qst.getAns_list().size();
            if(answerCnt == 0){
                LOG.error("answer is not exist : {}", qst);
                throw new IllegalArgumentException();
            }

            long hitCnt = qst.getAns_list().stream().filter(ans -> ans.getAns_hit_yn().equals("Y")).count();
            if(0 == hitCnt){
                LOG.error("answer hitYn not exist : {}", qst);
                throw new IllegalArgumentException();
            }

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
        LOG.info(stopWatch.toString());

        return exam;
    }
}
