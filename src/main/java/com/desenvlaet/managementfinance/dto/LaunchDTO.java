package com.desenvlaet.managementfinance.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LaunchDTO {

    private Long id;

    private String description;

    private Integer month;

    private Integer year;

    private Long user;

    private BigDecimal value;

    private String typeLaunch;

    private String statusLaunch;
}
