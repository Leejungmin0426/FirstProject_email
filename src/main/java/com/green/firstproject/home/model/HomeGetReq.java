package com.green.firstproject.home.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HomeGetReq {

    @NotNull
    @Positive
    @Schema(name = "signedUserNo", description = "유저 아이디", type= "long", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private long signedUserNo;

    @NotNull
    @Pattern(regexp = "^[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}")
    @Schema(name = "date", description = "달력 날짜", type= "string", example = "YYYY-mm-dd", requiredMode = Schema.RequiredMode.REQUIRED)
    private String date;
}