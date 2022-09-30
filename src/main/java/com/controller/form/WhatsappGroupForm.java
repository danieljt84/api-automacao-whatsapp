package com.controller.form;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.model.Brand;
import com.model.Shop;

public class WhatsappGroupForm {
	
	private Long id;
	private String name;
	private List<Long> shopsId;
	@JsonProperty("brandId")
	private Long brandId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Long> getShopsId() {
		return shopsId;
	}
	public void setShopsId(List<Long> shopsId) {
		this.shopsId = shopsId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
}
