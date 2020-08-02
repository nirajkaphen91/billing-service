package com.cts.assignment.service;

import java.util.List;

import com.cts.assignment.exception.BillNotFoundException;
import com.cts.assignment.exception.BillingException;
import com.cts.assignment.exception.StockNotFoundException;
import com.cts.assignment.model.Invoice;
import com.cts.assignment.model.StockBean;

public interface BillingService {
	Invoice generateInvoice(List<StockBean> list) throws StockNotFoundException, BillingException;

	Invoice getBillingById(String billingid) throws BillNotFoundException;
}
