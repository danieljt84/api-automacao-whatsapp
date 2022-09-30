package com.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controller.dto.BrandDTO;
import com.controller.form.BrandForm;
import com.controller.form.BrandWithOutIdForm;
import com.model.Brand;
import com.service.BrandService;


@RestController
@RequestMapping("/brand")
public class BrandController {
	@Autowired
	BrandService brandService;
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping("/create")
	public ResponseEntity create(@RequestBody String nameBrand){
		try {
			brandService.create(nameBrand);
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	@PostMapping("/update")
	public ResponseEntity update(@RequestBody BrandForm brandForm){
		try {
			brandService.update(convertFormToPOJO(brandForm));
			return ResponseEntity.ok().build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity findBynameLike(@RequestParam String name){
		try {
			return ResponseEntity.ok(convertPOJOstoDTOs(brandService.findByNameLike(name)));
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/list")
	public ResponseEntity list(){
		return ResponseEntity.ok(convertPOJOstoDTOs(brandService.findAll()));
	}
	
	private Brand convertFormToPOJO(BrandForm brandForm){
		return modelMapper.map(brandForm, Brand.class);
	}
	
	private List<BrandDTO> convertPOJOstoDTOs(List<Brand>brands){
		return brands.stream().map(brand -> modelMapper.map(brand, BrandDTO.class)).collect(Collectors.toList());
	}
}
