package com.easyoops.biz.comn.controller;

import com.easyoops.biz.comn.entity.ComnCode;
import com.easyoops.biz.comn.service.ComnService;
import com.easyoops.common.dto.ResponseDTO;
import com.easyoops.common.enums.ResponseCode;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ComnController {

    private final ComnService comnService;

    @GetMapping("/comn")
    @ApiOperation(value = "공통 코드 조회")
    public ResponseDTO<List<ComnCode>> comnCodeList() {
        return new ResponseDTO<>(ResponseCode.OK, comnService.findComnCodeList());
    }


}
