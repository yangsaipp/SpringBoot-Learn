package com.example.model;

import com.example.AggregateRoot;
import com.example.dto.TransferAccountDTO;

/**
 * 银行账号
 * @author yangsai
 *
 */
public class AccountBO implements AggregateRoot {
	
	private String accountId;
	
	private String accountNum;
	
	private Double money;
	
	private UserBO user;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public UserBO getUser() {
		return user;
	}

	public void setUser(UserBO user) {
		this.user = user;
	}
	
	/**
	 * 转账到另一个账号
	 * @param toAccount
	 * @param transferAccountDTO
	 */
	public void transfer(AccountBO toAccount,
			TransferAccountDTO transferAccountDTO) {
		this.money = this.money - transferAccountDTO.getMoney();
		transferAccountDTO.setMoney(transferAccountDTO.getMoney() + transferAccountDTO.getMoney());
	}
}
