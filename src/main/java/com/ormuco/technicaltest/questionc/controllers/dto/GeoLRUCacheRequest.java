package com.ormuco.technicaltest.questionc.controllers.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class GeoLRUCacheRequest {
    
    @Getter
    @Setter
    private String origin;

    @Getter
    @Setter
    @NotBlank
    @NotNull
    private String key;

    @Getter
    @Setter
    @NotBlank
    @NotNull
    private String value;
}
