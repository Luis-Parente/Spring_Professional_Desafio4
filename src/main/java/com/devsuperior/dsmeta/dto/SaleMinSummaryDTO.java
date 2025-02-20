package com.devsuperior.dsmeta.dto;

public class SaleMinSummaryDTO {

	private String name;
	private Double total;

	public SaleMinSummaryDTO() {

	}

	public SaleMinSummaryDTO(String name, Double total) {
		this.name = name;
		this.total = total;
	}

	public String getName() {
		return name;
	}

	public Double getTotal() {
		return total;
	}

}
