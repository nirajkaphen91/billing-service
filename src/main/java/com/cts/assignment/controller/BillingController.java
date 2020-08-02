package com.cts.assignment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.assignment.exception.BillNotFoundException;
import com.cts.assignment.model.Invoice;
import com.cts.assignment.model.StockBean;
import com.cts.assignment.service.BillingService;

@RestController
public class BillingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BillingController.class);

	private BillingService billingService;

	@Autowired
	public BillingController(BillingService billingService) {
		this.billingService = billingService;
	}

	@PostMapping("/invoice/create")
	public ResponseEntity<Invoice> generateBill(@RequestBody List<StockBean> list) {
		LOGGER.info("Generatiing billing  ....");

		Invoice invoice = null;
		try {
			invoice = billingService.generateInvoice(list);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			return new ResponseEntity<Invoice>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Invoice>(invoice, HttpStatus.CREATED);
	}

	@GetMapping("/invoice/{billingid}")
	public ResponseEntity<Invoice> getBillById(@PathVariable String billingid) {
		try {
			Invoice product = billingService.getBillingById(billingid);
			LOGGER.info("Getting billing details with ID {}.", billingid);
			if (product == null) {
				return new ResponseEntity<Invoice>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<Invoice>(product, HttpStatus.OK);
			}

		} catch (BillNotFoundException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<Invoice>(HttpStatus.NOT_FOUND);
		}
	}

}
