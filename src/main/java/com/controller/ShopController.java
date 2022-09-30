package com.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controller.dto.ShopDTO;
import com.model.Shop;
import com.service.ShopService;

@RestController
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	ShopService shopService;
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping("/list")
	public ResponseEntity list() {
		return ResponseEntity.ok(convertPOJOsToDTOs(shopService.findAll()));
	}
	@GetMapping
	public ResponseEntity getByNameLike(@RequestParam String name ) {
		return ResponseEntity.ok(convertPOJOsToDTOs(shopService.findLikeName(name)));
	}
	
	public List<ShopDTO> convertPOJOsToDTOs(List<Shop> shops){
		return shops.stream().map(shop -> modelMapper.map(shop, ShopDTO.class)).collect(Collectors.toList());
	}

}
