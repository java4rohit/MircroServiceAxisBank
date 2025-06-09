package com.java4rohit.accounts.service.client;

import com.java4rohit.accounts.dto.CardsDto;
import com.java4rohit.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient("loans")
public interface LoansFeignClient {
    @GetMapping(value = "/api/fetch",consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber);
}
