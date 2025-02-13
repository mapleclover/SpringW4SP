package com.example.schedulerv2.member.controller;

import com.example.schedulerv2.member.dto.request.MemberSignUpRequestDto;
import com.example.schedulerv2.member.dto.request.MemberUpdateRequestDto;
import com.example.schedulerv2.member.dto.response.MemberResponseDto;
import com.example.schedulerv2.member.dto.response.MemberSignUpResponseDto;
import com.example.schedulerv2.member.dto.response.MemberUpdateResponseDto;
import com.example.schedulerv2.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberSignUpResponseDto> singUp(@RequestBody MemberSignUpRequestDto dto) {
        return new ResponseEntity<> (memberService.signUp(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MemberResponseDto> findAll() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public MemberResponseDto findById(@PathVariable Long id) {
        return memberService.findByid(id);
    }

    @PutMapping("/{id}")
    public MemberUpdateResponseDto update(
            @PathVariable Long id,
            @RequestBody MemberUpdateRequestDto dto
    ){
        return memberService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        memberService.deleteById(id);
    }
}
