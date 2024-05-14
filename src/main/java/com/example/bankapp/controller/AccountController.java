package com.example.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankapp.entity.Account;
import com.example.bankapp.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	AccountService service;
	
	
	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		Account createaccount=service.createAccount(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(createaccount);
	}
	
	@GetMapping("/{accountNumber}")
	public Account getAccountByNumber(@PathVariable Long accountNumber) {
		Account account=service.getAccountDetailsByAccountNumber(accountNumber);
		return account;
	}
	
	
	@GetMapping("/getAllAccounts")
	public List<Account> getAllAccountDetails(){
		List<Account> allAccountDetails=service.getAllAccountDetails();
				return allAccountDetails;
	
	}
	
	@PutMapping("/deposit/{no}/{amount}")
	public Account depositAmount(@PathVariable long no,@PathVariable double amount) {
		Account deposited_acc=service.depositAmount(no, amount);
		return deposited_acc;
	}
	
	
	@PutMapping("/withdraw/{no}/{amount}")
	public Account withdraw(@PathVariable long no,@PathVariable double amount ) {
		Account account =service.withdrawAmount(no, amount);
		return account;
	}
	
	
	@DeleteMapping("/delete/{acc_no}")
	public ResponseEntity<String> deleteAccount(@PathVariable long acc_no) {
		service.closeAccount(acc_no);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Account closed");
	}
	
	
}
