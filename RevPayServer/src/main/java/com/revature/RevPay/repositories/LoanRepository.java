package com.revature.RevPay.repositories;

import com.revature.RevPay.Entities.Loan;
import com.revature.RevPay.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Integer> {
    @Query(nativeQuery = true, value="SELECT * FROM loan")
    List<Loan> getAll();
    Loan getByUser(User user);

    Loan deleteLoanByLoanID(Integer id);
}
