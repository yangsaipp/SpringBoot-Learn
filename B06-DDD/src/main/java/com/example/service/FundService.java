package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.TransferAccountDTO;
import com.example.model.AccountBO;
import com.example.repository.AccountRepository;



/**
 * 个人资金服务
 * @author yangsai
 *
 */
@Service
public class FundService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	/**
	 * 汇款转账
	 * @return
	 */
	public boolean transferAccount(TransferAccountDTO transferAccountDTO) {
		// 省略初步检验，如空值判断
		AccountBO fromAccount = accountRepository.find(transferAccountDTO.getFromAccoutId());
		AccountBO toAccount = accountRepository.find(transferAccountDTO.getToAccoutId());
		return true;
	}
}
