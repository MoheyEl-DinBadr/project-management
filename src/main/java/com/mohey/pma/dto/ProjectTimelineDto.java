package com.mohey.pma.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public interface ProjectTimelineDto {

    String getName();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date getStartDate();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date getEndDate();
}
