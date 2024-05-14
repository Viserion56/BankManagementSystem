package com.example.bankapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankapp.entity.Account;
import com.example.bankapp.repo.AccountRepository;


@Service
public class AccountServiceImplementation implements AccountService{
	
	@Autowired
	AccountRepository acc_repo;
	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		Account saved_acc=acc_repo.save(account);
		return saved_acc;
	}

	@Override
	public Account getAccountDetailsByAccountNumber(Long accountNumber) {
		// TODO Auto-generated method stub
		Optional<Account> account=acc_repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account Doesn't Exist");
		}
		Account generated_Account=account.get();
		
		return generated_Account;
	}

	@Override
	public List<Account> getAllAccountDetails() {
		// TODO Auto-generated method stub
		List<Account> listOfAccounts=acc_repo.findAll();
		return listOfAccounts;
	}

	@Override
	public Account depositAmount(long accountNumber, double amount) {
		Optional<Account> account=acc_repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account existingAccount=account.get();
		 double total=existingAccount.getAccount_balance()+amount;
		 existingAccount.setAccount_balance(total);
		 acc_repo.save(existingAccount);
		 return existingAccount;
	}

	@Override
	public Account withdrawAmount(long accountNumber, double amount) {
		// TODO Auto-generated method stub
		Optional<Account> account=acc_repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account existingAccount=account.get();
		if(existingAccount.getAccount_balance()<amount) {
			throw new RuntimeException("Not Enough Balance Please Add Money");
		}
		double balance=existingAccount.getAccount_balance()-amount;
		existingAccount.setAccount_balance(balance);
		acc_repo.save(existingAccount);
		
		return existingAccount;
	}

	@Override
	public void closeAccount(long accountNumber) {
		// TODO Auto-generated method stub
		getAccountDetailsByAccountNumber(accountNumber);
		acc_repo.deleteById(accountNumber);
		
		
	}

}
