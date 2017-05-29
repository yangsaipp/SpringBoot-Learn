package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.model.AccountBO;
import com.example.model.UserBO;

@Repository
public class AccountRepository {
	
	public AccountBO find(String accoutId) {
		AccountBO accountBO = new AccountBO();
		accountBO.setUser(new UserBO());
		return accountBO;
	}

	
	public void update(AccountBO accountBO) {
		// update...
	}

}
