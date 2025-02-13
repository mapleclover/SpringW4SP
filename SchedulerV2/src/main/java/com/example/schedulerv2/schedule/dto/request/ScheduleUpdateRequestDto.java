package com.example.schedulerv2.schedule.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {
        @NotBlank
        private String title;
        @NotBlank
        private String content;
}
