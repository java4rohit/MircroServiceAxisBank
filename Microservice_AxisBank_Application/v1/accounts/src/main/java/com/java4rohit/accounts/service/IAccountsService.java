package com.java4rohit.accounts.service;

import com.java4rohit.accounts.dto.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface IAccountsService {

    /**
     *
     * @param customerDto - customerDto Object
     */
    public void createAccount(CustomerDto customerDto);


    public CustomerDto fetchAccount(String mobileNumber);

    public boolean updateAccount(CustomerDto customerDto);


    public boolean deleteAccount(String mobileNumber);
}
