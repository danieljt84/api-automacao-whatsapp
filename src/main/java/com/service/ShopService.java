package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Shop;
import com.repository.ShopRepository;

@Service
public class ShopService {

	@Autowired
	ShopRepository shopRepository;
	
	public Shop findById(Long id) {
		var shop = shopRepository.findById(id);
		if(shop.isPresent()) return shop.get();
		return null;
	}
	
	public List<Shop> findAll(){
		return shopRepository.findAll();
	}
	
	public List<Shop> findLikeName(String name){
		return shopRepository.findByNameContaining(name);
	}
	
	public List<Shop> getByWhatsappGroup(Long whatsappGroupId){
		return shopRepository.getByWhatsappGroup(whatsappGroupId);
	}
}
