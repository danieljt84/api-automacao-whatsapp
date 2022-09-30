package com.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "whatsappgroup")
public class WhatsappGroup {
	
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="whatsappgroup_shop",
    joinColumns={@JoinColumn(name="whatsappgroup_id",
     referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="shops_id",
      referencedColumnName="id")})
	private List<Shop> shops;
	@ManyToOne
	private Brand brand;
	
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public List<Shop> getShops() {
		return shops;
	}
	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}
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
}
