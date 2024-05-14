package com.example.bankapp.service;

import java.util.List;

import com.example.bankapp.entity.Account;

public interface AccountService {
	public Account createAccount(Account account);
	public Account getAccountDetailsByAccountNumber(Long accountNumber);
	public List<Account> getAllAccountDetails();
	public Account depositAmount(long accountNumber,double amount);
	public Account withdrawAmount(long accountNumber,double amount);
	public void closeAccount(long accountNumber);
	

}
