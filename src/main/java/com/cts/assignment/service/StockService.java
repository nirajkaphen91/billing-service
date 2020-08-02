package com.cts.assignment.service;

import com.cts.assignment.exception.StockAlreadyExistsException;
import com.cts.assignment.exception.StockNotFoundException;
import com.cts.assignment.model.Stock;

public interface StockService {
	Stock registerStock(Stock stock) throws StockAlreadyExistsException;

	Stock updateStock(String stockId, Stock stock) throws StockNotFoundException;

	boolean deleteStock(String stockId) throws StockNotFoundException;

	Stock getStockById(String stockid) throws StockNotFoundException;

}
