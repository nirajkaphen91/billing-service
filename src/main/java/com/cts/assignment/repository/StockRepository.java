package com.cts.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.assignment.model.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {

}
