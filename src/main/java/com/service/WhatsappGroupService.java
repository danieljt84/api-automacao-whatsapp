package com.service;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Brand;
import com.model.WhatsappGroup;
import com.repository.WhatsappGroupRepository;

@Service
public class WhatsappGroupService {

	@Autowired
	WhatsappGroupRepository whatsappGroupRepository;
	Logger logger = LoggerFactory.getLogger(WhatsappGroupService.class);

	public void create(WhatsappGroup whatsappGroup) {
		try {
			if (whatsappGroupRepository.checkIfExistsByName(whatsappGroup.getName()).isPresent())
				throw new EntityExistsException("WhatsappGroup já existente");
			whatsappGroupRepository.save(whatsappGroup);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public void update(WhatsappGroup whatsappGroup) {
		try {
			var element = whatsappGroupRepository.findById(whatsappGroup.getId())
					.orElseThrow(EntityNotFoundException::new);
			element.setBrand(whatsappGroup.getBrand());
			element.setDaysToSend(whatsappGroup.getDaysToSend());
			element.setName(whatsappGroup.getName());
			element.setSendDetail(whatsappGroup.isSendDetail());
			element.setShops(whatsappGroup.getShops());
			whatsappGroupRepository.save(element);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public void delete(Long id) {
		try {
			var whatsappGroup = new WhatsappGroup();
			whatsappGroup.setId(id);
			whatsappGroupRepository.delete(whatsappGroup);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
	public List<WhatsappGroup> getByBrand(Long idbrand){
		try {	
			return whatsappGroupRepository.findByBrand(idbrand).orElseThrow(EntityNotFoundException::new);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	

	public List<WhatsappGroup> list() {
		return whatsappGroupRepository.findAll();
	}
}
