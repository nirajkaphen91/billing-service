package com.cts.assignment.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
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
import org.springframework.http.ResponseEntity;

import com.cts.assignment.exception.BillNotFoundException;
import com.cts.assignment.exception.BillingException;
import com.cts.assignment.exception.StockNotFoundException;
import com.cts.assignment.model.Invoice;
import com.cts.assignment.model.Stock;
import com.cts.assignment.model.StockBean;
import com.cts.assignment.service.BillingServiceImpl;

public class BillingControllerTest {

	@Mock
	private BillingServiceImpl billingService;
	@InjectMocks
	private BillingController billingController;
	private List<StockBean> stockBeanList = null;
	private Stock stock;
	private StockBean stockBean;
	private Invoice invoice;
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
	public void testGenerateBill() throws StockNotFoundException, BillingException {
		when(billingService.generateInvoice(stockBeanList)).thenReturn(invoice);
		ResponseEntity<Invoice> invoiceSaved = billingController.generateBill(stockBeanList);
		assertNotNull(invoiceSaved);
	}

	@Test
	public void testGetBillById() throws BillNotFoundException {
		when(billingService.getBillingById(invoice.getBillingid())).thenReturn(invoice);
		ResponseEntity<Invoice> invoiceSaved = billingController.getBillById(invoice.getBillingid());
		assertNotNull(invoiceSaved);
	}

}
