package com.example.schedulerv2.schedule.service;

import com.example.schedulerv2.member.entity.Member;
import com.example.schedulerv2.member.repository.MemberRepository;
import com.example.schedulerv2.schedule.dto.request.ScheduleSaveRequestDto;
import com.example.schedulerv2.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.schedulerv2.schedule.dto.response.SchduleUpdateResponseDto;
import com.example.schedulerv2.schedule.dto.response.ScheduleResponseDto;
import com.example.schedulerv2.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedulerv2.schedule.entity.Schedule;
import com.example.schedulerv2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ScheduleSaveResponseDto save(ScheduleSaveRequestDto dto) {

        Member member = memberRepository.findById(dto.getUserId()).orElseThrow(
                ()-> new IllegalStateException("유저가 없습니다.")
        );

        Schedule schedule = new Schedule(
                member,
                dto.getTitle(),
                dto.getContent()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleSaveResponseDto(
                savedSchedule.getId(),
                member.getUsername(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();
        schedules.forEach(schedule -> scheduleResponseDtos.add(new ScheduleResponseDto(
                schedule.getId(),
                schedule.getMember().getUsername(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        )));

        return scheduleResponseDtos;
    }

    @Transactional(readOnly = true)
    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 스케쥴은 존재하지 않습니다."));
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getMember().getUsername(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public SchduleUpdateResponseDto update(Long id, ScheduleUpdateRequestDto dto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 스케쥴은 존재하지 않습니다."));

        schedule.update(
                dto.getTitle(),
                dto.getContent()
        );

        return new SchduleUpdateResponseDto(
                schedule.getId(),
                schedule.getMember().getUsername(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional
    public void deleteById(Long id) {
        if(!scheduleRepository.existsById(id)) {
            throw new IllegalStateException();
        }
        scheduleRepository.deleteById(id);
    }
}
