package com.cts.assignment.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.assignment.exception.StockAlreadyExistsException;
import com.cts.assignment.exception.StockNotFoundException;
import com.cts.assignment.model.Stock;
import com.cts.assignment.repository.StockRepository;

public class StockServiceImplTest {
	@Mock
	private StockRepository stockRepository;
	private Stock stock;
	@InjectMocks
	private StockServiceImpl stockService;
	private List<Stock> stockList = null;
	private Optional<Stock> options;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		stock = new Stock();
		stock.setCostprice(70);
		stock.setCount(10);
		stock.setDescription("Microsoft mouse");
		stock.setName("Mouse");
		stock.setSellingprice(100);
		stock.setStockid("s001");

		stockList = new ArrayList<>();
		stockList.add(stock);
		options = Optional.of(stock);
	}

	@Test
	public void testRegisterStock() throws StockAlreadyExistsException {
		when(stockRepository.save((Stock) any())).thenReturn(stock);
		Stock stockSaved = stockService.registerStock(stock);
		assertEquals(stock, stockSaved);
	}

	@Test
	public void testUpdateStock() throws StockNotFoundException {
		when(stockRepository.findById(stock.getStockid())).thenReturn(options);
		stock.setCount(5);
		Stock fetchedStock = stockService.updateStock(stock.getStockid(), stock);
		assertEquals(stock, fetchedStock);
	}

	@Test
	public void testDeleteStock() throws StockNotFoundException {
		when(stockRepository.findById(stock.getStockid())).thenReturn(options);
		boolean flag = stockService.deleteStock(stock.getStockid());
		assertEquals(true, flag);
	}

	@Test
	public void testGetStockById() throws StockNotFoundException {
		when(stockRepository.findById(stock.getStockid())).thenReturn(options);
		Stock fetchedstock = stockService.getStockById(stock.getStockid());
		assertEquals(stock, fetchedstock);
		assertNotNull(stock.getCostprice());
		assertNotNull(stock.getCount());
		assertNotNull(stock.getDescription());
		assertNotNull(stock.getName());
		assertNotNull(stock.getSellingprice());
		assertNotNull(stock.getStockid());
	}
}