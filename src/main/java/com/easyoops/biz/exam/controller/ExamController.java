package com.easyoops.biz.exam.controller;

import com.easyoops.biz.exam.dto.ExamDto;
import com.easyoops.biz.exam.dto.ExamRequestDto;
import com.easyoops.biz.exam.service.ExamService;
import com.easyoops.biz.member.dto.MemberDto;
import com.easyoops.biz.member.dto.MemberLoginRequestDto;
import com.easyoops.biz.member.dto.TokenInfo;
import com.easyoops.biz.member.entity.Member;
import com.easyoops.biz.member.service.MemberService;
import com.easyoops.common.dto.ResponseDTO;
import com.easyoops.common.enums.ResponseCode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
        examService.createExam(examRequestDto);

        ResponseDTO responseDTO = new ResponseDTO<>(ResponseCode.OK, examRequestDto);

        return responseDTO;
    }


}
