package com.cts.assignment.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Invoice implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String billingid;
	private Date billingdate;
	private double billingamount;
	private String billerid;
	private String coustomerid;

	public Invoice() {
		this.billingid = UUID.randomUUID() + "";
		this.billingdate = new Date();
	}

	public Invoice(double billingamount, String billerid, String coustomerid) {
		this.billingid = UUID.randomUUID() + "";
		this.billingdate = new Date();
		this.billingamount = billingamount;
		this.billerid = billerid;
		this.coustomerid = coustomerid;
	}

	public String getBillingid() {
		return billingid;
	}

	public void setBillingid(String billingid) {
		this.billingid = billingid;
	}

	public Date getBillingdate() {
		return billingdate;
	}

	public void setBillingdate(Date billingdate) {
		this.billingdate = billingdate;
	}

	public double getBillingamount() {
		return billingamount;
	}

	public void setBillingamount(double billingamount) {
		this.billingamount = billingamount;
	}

	public String getBillerid() {
		return billerid;
	}

	public void setBillerid(String billerid) {
		this.billerid = billerid;
	}

	public String getCoustomerid() {
		return coustomerid;
	}

	public void setCoustomerid(String coustomerid) {
		this.coustomerid = coustomerid;
	}

	@Override
	public String toString() {
		return "Invoice [billingid=" + billingid + ", billingdate=" + billingdate + ", billingamount=" + billingamount
				+ ", billerid=" + billerid + ", coustomerid=" + coustomerid + "]";
	}

}
