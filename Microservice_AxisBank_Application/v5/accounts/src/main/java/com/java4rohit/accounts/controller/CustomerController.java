package com.java4rohit.accounts.controller;


import com.java4rohit.accounts.dto.CustomerDetailsDto;
import com.java4rohit.accounts.dto.CustomerDto;
import com.java4rohit.accounts.service.ICustomerService;
import feign.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CRUD REST API for Customer for AxisBank",
   description = "REST API int AxisBank TO FETCH  Customer Details")
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerController {

    private  final ICustomerService IcustomerService;

    public CustomerController(ICustomerService icustomerService) {
        this.IcustomerService = icustomerService;
    }

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto>  fetchCustomerDetails(@RequestParam
                                                             @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 Digit")
                                                             String mobileNumber){

        CustomerDetailsDto customerDetailsDto = IcustomerService.fetchCustomerDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
