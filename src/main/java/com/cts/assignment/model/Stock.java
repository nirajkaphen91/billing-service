package com.cts.assignment.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String stockid;
	private String name;
	private double costprice;
	private double sellingprice;
	private int count;
	private String description;

	public Stock() {
	}

	public Stock(String stockid, String name, double costprice, double sellingprice, int count, String description) {
		this.stockid = stockid;
		this.name = name;
		this.costprice = costprice;
		this.sellingprice = sellingprice;
		this.count = count;
		this.description = description;
	}

	public String getStockid() {
		return stockid;
	}

	public void setStockid(String stockid) {
		this.stockid = stockid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCostprice() {
		return costprice;
	}

	public void setCostprice(double costprice) {
		this.costprice = costprice;
	}

	public double getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(double sellingprice) {
		this.sellingprice = sellingprice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Stock [stockid=" + stockid + ", name=" + name + ", costprice=" + costprice + ", sellingprice="
				+ sellingprice + ", count=" + count + ", description=" + description + "]";
	}
}
