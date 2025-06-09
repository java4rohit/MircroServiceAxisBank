package com.java4rohit.accounts.service.Impl;

import com.java4rohit.accounts.Exception.ResourceNotFoundException;
import com.java4rohit.accounts.dto.AccountsDto;
import com.java4rohit.accounts.dto.CardsDto;
import com.java4rohit.accounts.dto.CustomerDetailsDto;
import com.java4rohit.accounts.dto.LoansDto;
import com.java4rohit.accounts.entities.Accounts;
import com.java4rohit.accounts.entities.Customer;
import com.java4rohit.accounts.mapper.AccountsMapper;
import com.java4rohit.accounts.mapper.CustomerMapper;
import com.java4rohit.accounts.repositories.AccountRepository;
import com.java4rohit.accounts.repositories.CustomerRepository;
import com.java4rohit.accounts.service.ICustomerService;
import com.java4rohit.accounts.service.client.CardsFeignClient;
import com.java4rohit.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Costomer", "mobileNumber", mobileNumber));

        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        return customerDetailsDto;
    }
}
