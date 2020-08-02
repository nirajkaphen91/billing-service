package com.cts.assignment.model;

public class StockBean {
	private String stockid;
	private int count;

	public StockBean() {
	}

	public StockBean(String stockid, int count) {
		super();
		this.stockid = stockid;
		this.count = count;
	}

	public String getStockid() {
		return stockid;
	}

	public void setStockid(String stockid) {
		this.stockid = stockid;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "StockBean [stockid=" + stockid + ", count=" + count + "]";
	}
}
