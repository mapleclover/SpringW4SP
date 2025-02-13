package com.example.schedulerv2.member.service;

import com.example.schedulerv2.member.dto.request.MemberSignUpRequestDto;
import com.example.schedulerv2.member.dto.request.MemberUpdateRequestDto;
import com.example.schedulerv2.member.dto.response.MemberResponseDto;
import com.example.schedulerv2.member.dto.response.MemberSignUpResponseDto;
import com.example.schedulerv2.member.dto.response.MemberUpdateResponseDto;
import com.example.schedulerv2.member.entity.Member;
import com.example.schedulerv2.member.repository.MemberRepository;
import jakarta.persistence.TableGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberSignUpResponseDto signUp(MemberSignUpRequestDto dto) {
        Member member = new Member(
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail()
        );

        Member savedMember = memberRepository.save(member);
        return new MemberSignUpResponseDto(
                savedMember.getId(),
                savedMember.getUsername(),
                savedMember.getEmail(),
                savedMember.getCreatedAt(),
                savedMember.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        members.forEach(member -> memberResponseDtos.add(new MemberResponseDto(
                member.getId(),
                member.getUsername(),
                member.getEmail(),
                member.getCreatedAt(),
                member.getModifiedAt()
        )));

        return memberResponseDtos;
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findByid(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("없는 유저입니다."));
        return new MemberResponseDto(
                member.getId(),
                member.getUsername(),
                member.getEmail(),
                member.getCreatedAt(),
                member.getModifiedAt()
        );
    }

    @Transactional
    public MemberUpdateResponseDto update(Long id, MemberUpdateRequestDto dto) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("없는 유저입니다."));
        member.update(
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail()
        );
        return new MemberUpdateResponseDto(
                member.getId(),
                member.getUsername(),
                member.getEmail(),
                member.getCreatedAt(),
                member.getModifiedAt()
        );
    }

    @Transactional
    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
}
