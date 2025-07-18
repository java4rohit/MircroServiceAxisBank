package com.java4rohit.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "CustomerDetailsDto", description = "Schema to hold Customer and Account information")
public class CustomerDetailsDto {

    @Schema(description = "Name of the Customer",example = "Rohit Yadav")
    @NotEmpty(message = "name can't be null or empty")
    @Size(min = 5,max = 30 ,message = "The length of customer name should be between 5 and 30 ")
    private String name;

    @Schema(description = "Email of the Customer",example = "rohitforAxel@gmail.com")
    @NotEmpty(message = "name can't be null or empty")
    @Email(message = "email address should be valid value")
    private String email;

    @Schema(description = "Mobile Number of the Customer",example = "9994443339")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 Digit")
    private  String mobileNumber;

    @Schema(description = "Account Details of the Customer")
    private AccountsDto accountsDto;


    @Schema(description = "Cards Details of the Customer")
    private CardsDto cardsDto;

    @Schema(description = "Loans Details of the Customer")
    private LoansDto loansDto;

}
