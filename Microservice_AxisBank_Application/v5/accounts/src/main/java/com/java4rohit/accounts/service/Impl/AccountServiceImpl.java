package com.java4rohit.accounts.service.Impl;

import com.java4rohit.accounts.Exception.CustomerAlreadyExistsException;
import com.java4rohit.accounts.Exception.ResourceNotFoundException;
import com.java4rohit.accounts.constants.AccountsConstants;
import com.java4rohit.accounts.dto.AccountsDto;
import com.java4rohit.accounts.dto.CustomerDto;
import com.java4rohit.accounts.entities.Accounts;
import com.java4rohit.accounts.entities.Customer;
import com.java4rohit.accounts.mapper.AccountsMapper;
import com.java4rohit.accounts.mapper.CustomerMapper;
import com.java4rohit.accounts.repositories.AccountRepository;
import com.java4rohit.accounts.repositories.CustomerRepository;
import com.java4rohit.accounts.service.IAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements IAccountsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     *
     * @param customerDto - customerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());

        if(optionalCustomer.isPresent()){
            throw  new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customer.getMobileNumber());
        }
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("Anonymous");
        Customer saveCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccounts(saveCustomer));


    }

    private Accounts createNewAccounts(Customer customer){

        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(customer.getCustomerId());

        long randomAccountNumber = 10000000000L + new Random().nextInt(900000000);

//        newAccounts.setCreatedAt(LocalDateTime.now());
//        newAccounts.setCreatedBy("Anonymous");
        newAccounts.setAccountNumber(randomAccountNumber);
        newAccounts.setAccountType(AccountsConstants.SAVINGS);
        newAccounts.setBranchAddress(AccountsConstants.ADDRESS);
        return  newAccounts;
    }


    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Costomer", "mobileNumber", mobileNumber));


        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;

        AccountsDto accountsDto = customerDto.getAccountsDto();

        Accounts accounts = null;
        if (accountsDto != null) {
            accounts = accountRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString()));


            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();

            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString()));

            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);

        isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

         accountRepository.deleteByCustomerId(customer.getCustomerId());

         customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

}
