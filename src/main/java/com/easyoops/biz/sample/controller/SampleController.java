package com.easyoops.biz.sample.controller;

import com.easyoops.biz.sample.entity.SampleEntity;
import com.easyoops.biz.sample.repository.SampleInterface;
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

    @GetMapping("/")
    @ApiOperation(value = "샘플 목록 조회")
    public ResponseDTO<List<SampleEntity>> selectSempleList() {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.selectSampleList());
    }

    @GetMapping("/{no}")
    @ApiOperation(value = "샘플 목록 상세 조회")
    public ResponseDTO<SampleEntity> selectSempleView(@ApiParam(value = "no", required = true, example = "1") @PathVariable Integer no) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.selectSampleView(no));
    }

    @PostMapping("/")
    @ApiOperation(value = "샘플 생성")
    public ResponseDTO<SampleEntity> createSample(@Valid @RequestBody SampleEntity sampleEntity) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.createSample(sampleEntity));
    }

    @PutMapping("/")
    @ApiOperation(value = "샘플 수정")
    public ResponseDTO<SampleEntity> updateSample(@Valid @RequestBody SampleEntity sampleEntity) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.updateSample(sampleEntity));
    }

    @DeleteMapping("/{no}")
    @ApiOperation(value = "샘플 삭제")
    public ResponseDTO<SampleEntity> deleteSample(@ApiParam(value = "no", required = true, example = "1") @PathVariable Integer no) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.deleteSample(no));
    }

    @GetMapping("/search/{keyword}")
    @ApiOperation(value = "샘플 목록 검색 조회")
    public ResponseDTO<List<SampleInterface>> selectSampleSearch(@ApiParam(value = "keyword") @PathVariable String keyword) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.selectSampleSearch(keyword));
    }
}
