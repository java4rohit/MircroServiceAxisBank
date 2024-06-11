package com.java4rohit.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Schema to hold Successful Response Information")
public class ResponseDto {

    @Schema(description = "Status Code in the Response")
    private String statusCode;

    @Schema(description = "Status Message in the Response ")
    private String statusMgs;



}
