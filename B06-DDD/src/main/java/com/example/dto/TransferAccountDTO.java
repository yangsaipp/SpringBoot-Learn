package com.example.dto;

public class TransferAccountDTO {

	private String fromAccoutId;
	private String toAccoutId;
	private Double money;
	private String mark;
	
	public String getFromAccoutId() {
		return fromAccoutId;
	}
	public void setFromAccoutId(String fromAccoutId) {
		this.fromAccoutId = fromAccoutId;
	}
	public String getToAccoutId() {
		return toAccoutId;
	}
	public void setToAccoutId(String toAccoutId) {
		this.toAccoutId = toAccoutId;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
}
