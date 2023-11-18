package com.spotlightkonex.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface EnterpriseDTO {
    String getCorpCode();
    String getCorpName();
    String getLogo();
    Long getPrice();
    Long getPrevPrice();
}
