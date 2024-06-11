package com.java4rohit.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;



@Data
@ToString
@Schema(name = "Accounts", description = "Schema to hold Account information")
public class AccountsDto {


    @Schema(description = "Account Number of the AxisBank")
    @Column(name = "account_number")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 Digit")
    @NotEmpty(message = "accountNumber can't be null or empty")
    private  Long accountNumber;

    @Schema(description = "Account Type of the AxisBank Account",example = "SAVING")
    @Column(name = "account_type")
    @NotEmpty(message = "accountType can't be null or empty")
    private  String accountType;

    @Schema(description = "BRANCH ADDRESS of the AxisBank Account")
    @Column(name = "branch_address")
    @NotEmpty(message = "branchAddress can't be null or empty")
    private String branchAddress;
}
