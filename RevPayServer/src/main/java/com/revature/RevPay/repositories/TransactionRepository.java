package com.revature.RevPay.repositories;

import com.revature.RevPay.Entities.Transaction;
import com.revature.RevPay.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    public List<Transaction> getByPayee(User payee);
    public List<Transaction> getByPayer(User payer);

    @Query(nativeQuery = true, value="SELECT * FROM transaction WHERE payee_username = ?1 OR payer_username = ?1")
    public List<Transaction> getByPayeeOrPayer(String username);
    @Query(nativeQuery = true, value="SELECT * FROM transaction WHERE payee_username = ?1 AND payer_username = ?2")
    public List<Transaction> getByPayeeAndPayer(String payee, String payer);
    @Query(nativeQuery = true, value="SELECT * FROM transaction WHERE date = ?2 AND (payee_username = ?1 OR payer_username = ?1)")
    public List<Transaction> getByPayeeOrPayerAndDate(String username, LocalDateTime date);
}
