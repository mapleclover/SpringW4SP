package com.example.schedulerv2.schedule.controller;

import com.example.schedulerv2.schedule.dto.request.ScheduleSaveRequestDto;
import com.example.schedulerv2.schedule.dto.request.ScheduleUpdateRequestDto;
import com.example.schedulerv2.schedule.dto.response.SchduleUpdateResponseDto;
import com.example.schedulerv2.schedule.dto.response.ScheduleResponseDto;
import com.example.schedulerv2.schedule.dto.response.ScheduleSaveResponseDto;
import com.example.schedulerv2.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleSaveResponseDto> save(@RequestBody ScheduleSaveRequestDto dto) {
        return new ResponseEntity<>(scheduleService.save(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScheduleResponseDto> findAll() {
        return scheduleService.findAll();
    }

    @GetMapping("/{id}")
    public ScheduleResponseDto findById(@PathVariable Long id) {
        return scheduleService.findById(id);
    }

    @PutMapping("/{id}")
    public SchduleUpdateResponseDto update(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateRequestDto dto
    ){
        return scheduleService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        scheduleService.deleteById(id);
    }
}
