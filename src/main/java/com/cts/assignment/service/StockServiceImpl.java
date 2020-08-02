package com.cts.assignment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cts.assignment.exception.StockAlreadyExistsException;
import com.cts.assignment.exception.StockNotFoundException;
import com.cts.assignment.model.Stock;
import com.cts.assignment.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StockServiceImpl.class);

	private StockRepository StockRepository;

	@Autowired
	public StockServiceImpl(StockRepository StockRepository) {
		this.StockRepository = StockRepository;
	}

	@CachePut(value = "stocks", key = "#stock.stockid")
	@Override
	public Stock registerStock(Stock stock) throws StockAlreadyExistsException {
		stock = StockRepository.save(stock);
		if (stock == null) {
			throw new StockAlreadyExistsException("stock already exist");
		} else {
			return stock;
		}
	}

	@CachePut(value = "stocks", key = "#stock.stockid")
	@Override
	public Stock updateStock(String stockId, Stock stock) throws StockNotFoundException {
		StockRepository.save(stock);
		return StockRepository.findById(stockId).get();

	}

	@CacheEvict(value = "stocks", allEntries = true)
	@Override
	public boolean deleteStock(String stockId) throws StockNotFoundException {
		try {
			StockRepository.deleteById(stockId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
		return true;
	}

	@Cacheable(value = "stocks", key = "#stockid")
	@Override
	public Stock getStockById(String stockid) throws StockNotFoundException {
		return StockRepository.findById(stockid).get();

	}

}
