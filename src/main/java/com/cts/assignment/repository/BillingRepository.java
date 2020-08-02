package com.cts.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.assignment.model.Invoice;


@Repository
public interface BillingRepository extends JpaRepository<Invoice, String> {

}


