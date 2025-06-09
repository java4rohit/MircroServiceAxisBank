package com.java4rohit.accounts.dto;

import com.java4rohit.accounts.constants.AccountsConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer Information")
public class CustomerDto {

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

}
