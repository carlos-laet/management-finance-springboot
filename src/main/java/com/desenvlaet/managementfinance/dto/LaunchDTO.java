package com.desenvlaet.managementfinance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
