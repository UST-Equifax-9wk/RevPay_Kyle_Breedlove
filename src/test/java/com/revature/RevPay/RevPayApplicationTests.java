package com.revature.RevPay;

import com.revature.RevPay.Entities.User;
import com.revature.RevPay.repositories.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class RevPayApplicationTests {
	private ApplicationContext ac;

	@BeforeEach
	public void setup(@Autowired ApplicationContext ac){
		this.ac = ac;
	}
	@Test
	void userRepoLoads() {
		Assertions.assertNotNull(ac.getBean(UserRepository.class));
	}

	@Test
	void transactionRepoLoads() {
		Assertions.assertNotNull(ac.getBean(TransactionRepository.class));
	}
	@Test
	void cardRepoLoads() {
		Assertions.assertNotNull(ac.getBean(CardRepository.class));
	}
	@Test
	void securityQuestionRepoLoads() {
		Assertions.assertNotNull(ac.getBean(SecurityQuestionRepository.class));
	}
	@Test
	void loanRepoLoads() {
		Assertions.assertNotNull(ac.getBean(LoanRepository.class));
	}



}
