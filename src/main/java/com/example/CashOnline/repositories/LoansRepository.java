package com.example.CashOnline.repositories;

import com.example.CashOnline.models.Loans;
import com.example.CashOnline.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface LoansRepository extends JpaRepository<Loans, Long> {
    Optional<List<Loans>> findByUserId(@Param("user_id") User userId);

}