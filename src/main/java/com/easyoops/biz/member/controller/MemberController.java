package com.easyoops.biz.member.controller;

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
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseDTO<TokenInfo> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
        return new ResponseDTO<>(ResponseCode.OK, memberService.login(memberLoginRequestDto));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "맴버 정보 조회")
    public ResponseDTO<MemberDto> getMemberById(@ApiParam(value = "id", required = true, example = "1000000001") @PathVariable int id) throws Exception {
        Member member = memberService.getMemberById(id);
        return new ResponseDTO<>(ResponseCode.OK, MemberDto.builder()
                .id(member.getId())
                .emailId(member.getEmailId())
                .memberName(member.getMemberName())
                .roleId(member.getRole().getId())
                .build());
    }


}
