package com.service;

import java.util.List;

import javax.persistence.EntityExistsException;

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
			if(whatsappGroupRepository.checkIfExistsByName(whatsappGroup.getName()).isPresent()) throw new EntityExistsException("WhatsappGroup já existente");
			whatsappGroupRepository.save(whatsappGroup);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
	
    public void update(WhatsappGroup whatsappGroup) {
    	try {
    		whatsappGroupRepository.save(whatsappGroup);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}
    
    public void delete(WhatsappGroup whatsappGroup) {
    	try {
    		whatsappGroupRepository.delete(whatsappGroup);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
    }
        
    public List<WhatsappGroup> list() {
		return whatsappGroupRepository.findAll();
	}
}
