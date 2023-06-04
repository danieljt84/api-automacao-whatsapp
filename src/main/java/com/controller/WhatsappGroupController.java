package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controller.form.BrandForm;
import com.controller.form.WhatsappGroupForm;
import com.model.Brand;
import com.model.Shop;
import com.model.WhatsappGroup;
import com.repository.WhatsappGroupRepository;
import com.service.BrandService;
import com.service.ShopService;
import com.service.WhatsappGroupService;

@RestController
@RequestMapping("/whatsappgroup")
public class WhatsappGroupController {
	@Autowired
	WhatsappGroupService whatsappGroupService;
	@Autowired
	ShopService shopService;
	@Autowired
	BrandService brandService;
	@Autowired
	ModelMapper modelMapper;
	
	

	@PostMapping("/create")
	public ResponseEntity create(@RequestBody WhatsappGroupForm whatsappGroupForm){
		try {
			whatsappGroupService.create(convertFormToPOJO(whatsappGroupForm));
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity update(@RequestBody WhatsappGroupForm whatsappGroupForm){
		try {
			whatsappGroupService.update(convertFormToPOJO(whatsappGroupForm));
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity list(){
		try {
			return ResponseEntity.ok(whatsappGroupService.list());
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity getByBrand(@RequestParam Long idbrand){
		try {
			return ResponseEntity.ok(whatsappGroupService.getByBrand(idbrand));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable(name = "id") Long id){
		try {
			whatsappGroupService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	

	@GetMapping("/{id}/shops")
	public ResponseEntity getShopsByGrouup(@PathVariable(name = "id") Long whatsappGroupId) {
		try {
			return ResponseEntity.ok(shopService.getByWhatsappGroup(whatsappGroupId));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/brand")
	public ResponseEntity getBrandByGroup(@RequestParam(required = false) Integer limit,@RequestParam(required = false) Integer offset) {
		try {
			return ResponseEntity.ok(brandService.getBrandByGroup(limit,offset));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	private WhatsappGroup convertFormToPOJO(WhatsappGroupForm whatsappGroupForm){
	 WhatsappGroup whatsappGroup =  modelMapper.map(whatsappGroupForm, WhatsappGroup.class);
	 List<Shop> shops = new ArrayList<>();
	 for(Long idShop: whatsappGroupForm.getShopsId()) {
		 Shop shop = new Shop();
		 shop.setId(idShop);
		 shops.add(shop);
	 }
	 whatsappGroup.setShops(shops);
	 Brand brand = new Brand();
	 brand.setId(whatsappGroupForm.getBrandId());
	 whatsappGroup.setBrand(brand);
	 return whatsappGroup;
	}
}
