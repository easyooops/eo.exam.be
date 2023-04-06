package com.easyoops.biz.exam.controller;

import com.easyoops.biz.exam.dto.ExamDto;
import com.easyoops.biz.exam.dto.ExamRequestDto;
import com.easyoops.biz.exam.entity.Exam;
import com.easyoops.biz.exam.service.ExamService;
import com.easyoops.common.dto.ResponseDTO;
import com.easyoops.common.enums.ResponseCode;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ExamController {
    private final ExamService examService;

    @PostMapping("/exam")
    @ApiOperation(value = "시험 등록")
    public ResponseDTO<ExamDto> createExam(@RequestBody ExamRequestDto examRequestDto) throws Exception{

        //todo - 권한체크 로직 필요
        Exam exam = examService.createExam(examRequestDto);

        HashMap resultMap = new HashMap();
        resultMap.put("exam_no", exam.getExamNo());

        ResponseDTO responseDTO = new ResponseDTO<>(ResponseCode.OK, resultMap);

        return responseDTO;
    }


}
