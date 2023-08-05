package com.desenvlaet.managementfinance.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateStatusDTO {

    private String status;
}
