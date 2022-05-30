package com.easyoops.biz.sample.controller;

import com.easyoops.biz.sample.dto.SampleDTO;
import com.easyoops.biz.sample.service.SampleService;
import com.easyoops.common.dto.ResponseDTO;
import com.easyoops.common.enums.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@Api(value = "Sample API")
@RestController
@RequestMapping("/api/v1/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @PostMapping
    @ApiOperation(value = "샘플 목록 조회")
    public ResponseDTO<List<SampleDTO>> selectSempleList(@Valid @RequestBody SampleDTO sampleDTO) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.selectSampleList(sampleDTO));
    }

    @GetMapping("/{no}")
    @ApiOperation(value = "샘플 목록 상세 조회")
    public ResponseDTO<SampleDTO> selectSempleView(@ApiParam(value = "no", required = true, example = "1") @PathVariable String no) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.selectSampleView(no));
    }

    @PostMapping("/")
    @ApiOperation(value = "샘플 생성")
    public ResponseDTO<SampleDTO> createSample(@Valid @RequestBody SampleDTO sampleDTO) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.createSample(sampleDTO));
    }

    @PutMapping("/")
    @ApiOperation(value = "샘플 수정")
    public ResponseDTO<SampleDTO> updateSample(@Valid @RequestBody SampleDTO sampleDTO) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.updateSample(sampleDTO));
    }

    @DeleteMapping("/{no}")
    @ApiOperation(value = "샘플 삭제")
    public ResponseDTO<Boolean> deleteSample(@ApiParam(value = "no", required = true, example = "1") @PathVariable String no) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.deleteSample(no));
    }
}
