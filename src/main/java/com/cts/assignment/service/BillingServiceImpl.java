package com.cts.assignment.service;

import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cts.assignment.exception.BillNotFoundException;
import com.cts.assignment.exception.BillingException;
import com.cts.assignment.exception.StockNotFoundException;
import com.cts.assignment.model.Invoice;
import com.cts.assignment.model.Stock;
import com.cts.assignment.model.StockBean;
import com.cts.assignment.repository.BillingRepository;

@Service
public class BillingServiceImpl implements BillingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BillingServiceImpl.class);
	
	@Autowired
	private BillingRepository billingRepository;

	@Autowired
	private StockService stockService;

	@Override
	@Transactional
	@CachePut(value = "billings", key = "#billing.billingid")
	public Invoice generateInvoice(List<StockBean> list) throws StockNotFoundException, BillingException {
		double billingAmount=0;
		for(StockBean bean:list) {
			Stock stock = stockService.getStockById(bean.getStockid());
			LOGGER.info("Getting stock with ID {}.", bean.getStockid());
			
			if (stock == null || stock.getCostprice()<bean.getCount()) {
				throw new BillingException("Stock "+bean.getStockid()+" is out of stock.");
			}
			billingAmount+=bean.getCount()*stock.getSellingprice();
			
			//update stock
			stock.setCount(stock.getCount()-bean.getCount());
			stockService.updateStock(stock.getStockid(), stock);
		}
		
		Invoice invoice = new Invoice();
		invoice.setBillerid("Niraj");
		invoice.setBillingamount(billingAmount);
		Random r = new Random(100);
		invoice.setCoustomerid("User"+r.nextInt());
		
		billingRepository.save(invoice);
		
		return invoice;
	}
	
	@Cacheable(value = "billings", key = "#billingid")
	@Override
	public Invoice getBillingById(String billingid) throws BillNotFoundException {
		Invoice invoice = billingRepository.findById(billingid).get();
		
		if(invoice == null)
			throw new BillNotFoundException(billingid +" doesn't exist");
		
		return invoice;

	}

}
