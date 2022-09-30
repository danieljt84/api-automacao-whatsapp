package com.service;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Brand;
import com.repository.BrandRepository;

@Service
public class BrandService {
	
	@Autowired
	BrandRepository brandRepository;
    Logger logger = LoggerFactory.getLogger(BrandService.class);

	public void create(String name) {
		try {
			Brand brand = new Brand();
			brand.setName(name);
			if(brandRepository.checkIfExistsByName(name).isPresent()) throw new EntityExistsException("Brand já existente");
		    brandRepository.save(brand);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	public void update(Brand brand) {
		try {
		    brandRepository.save(brand);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	public List<Brand> findByNameLike(String name){
		try {
		   return brandRepository.findByNameContaining(name);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	public List<Brand> findAll() {
		try {
		   return brandRepository.findAll();
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

}
