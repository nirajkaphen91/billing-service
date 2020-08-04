package com.cts.assignment.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.assignment.exception.BillNotFoundException;
import com.cts.assignment.exception.BillingException;
import com.cts.assignment.exception.StockNotFoundException;
import com.cts.assignment.model.Invoice;
import com.cts.assignment.model.Stock;
import com.cts.assignment.model.StockBean;
import com.cts.assignment.repository.BillingRepository;

public class BillingServiceImplTest {

	@Mock
	private BillingRepository billingRepository;
	@Mock
	private StockServiceImpl stockService;
	private Stock stock;
	private StockBean stockBean;
	private Invoice invoice;
	@InjectMocks
	private BillingServiceImpl billingService;
	private List<StockBean> stockBeanList = null;
	private Optional<Invoice> options;

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
		
		invoice = new Invoice();
		invoice.setBillerid("Niraj");
		invoice.setBillingamount(100);
		invoice.setBillingdate(new Date());
		invoice.setBillingid("BILL000001");
		invoice.setCoustomerid("CUST001");
		
		stockBean = new StockBean();
		stockBean.setCount(2);
		stockBean.setStockid("s001");

		stockBeanList = new ArrayList<>();
		stockBeanList.add(stockBean);
		options = Optional.of(invoice);
	}
	
	@Test
	public void testGenerateInvoice() throws StockNotFoundException, BillingException {
		when(stockService.getStockById(stock.getStockid())).thenReturn(stock);
		when(stockService.updateStock(stock.getStockid(), stock)).thenReturn(stock);
		when(billingRepository.save(invoice)).thenReturn(invoice);
		Invoice invoice = billingService.generateInvoice(stockBeanList);
		assertNotNull(invoice);
	}

	@Test
	public void testGetBillingById() throws BillNotFoundException {
		when(billingRepository.findById(invoice.getBillingid())).thenReturn(options);
		Invoice fetchedBilling = billingService.getBillingById(invoice.getBillingid());
		assertEquals(invoice, fetchedBilling);
		assertNotNull(invoice.getBillerid());
		assertNotNull(invoice.getBillingamount());
		assertNotNull(invoice.getBillingdate());
		assertNotNull(invoice.getBillingid());
		assertNotNull(invoice.getCoustomerid());





	}

}
