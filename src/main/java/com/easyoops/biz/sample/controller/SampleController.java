package com.easyoops.biz.sample.controller;

import com.easyoops.biz.sample.entity.Sample;
import com.easyoops.biz.sample.repository.SampleInterface;
import com.easyoops.biz.sample.service.SampleService;
import com.easyoops.common.config.AppValueConfig;
import com.easyoops.common.dto.ResponseDTO;
import com.easyoops.common.enums.ResponseCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@Api(value = "Sample API")
@RestController
@AllArgsConstructor
@RequestMapping("${app.api.v1.path}/samples")
public class SampleController {

    private static final Logger LOG = LoggerFactory.getLogger("BIZ_LOGGER");

    private AppValueConfig appConfig;
    private SampleService sampleService;

    @GetMapping("/")
    @ApiOperation(value = "샘플 목록 조회")
    public ResponseDTO<List<Sample>> selectSempleList() {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.selectSampleList());
    }

    @GetMapping("/{no}")
    @ApiOperation(value = "샘플 목록 상세 조회")
    public ResponseDTO<Sample> selectSempleView(@ApiParam(value = "no", required = true, example = "1") @PathVariable Integer no) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.selectSampleView(no));
    }

    @PostMapping("/")
    @ApiOperation(value = "샘플 생성")
    public ResponseDTO<Sample> createSample(@Valid @RequestBody Sample sample) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.createSample(sample));
    }

    @PutMapping("/")
    @ApiOperation(value = "샘플 수정")
    public ResponseDTO<Sample> updateSample(@Valid @RequestBody Sample sample) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.updateSample(sample));
    }

    @DeleteMapping("/{no}")
    @ApiOperation(value = "샘플 삭제")
    public ResponseDTO<Sample> deleteSample(@ApiParam(value = "no", required = true, example = "1") @PathVariable Integer no) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.deleteSample(no));
    }

    @GetMapping("/search/{keyword}")
    @ApiOperation(value = "샘플 목록 검색 조회")
    public ResponseDTO<List<SampleInterface>> selectSampleSearch(@ApiParam(value = "keyword") @PathVariable String keyword) {
        return new ResponseDTO<>(ResponseCode.OK, sampleService.selectSampleSearch(keyword));
    }
}
