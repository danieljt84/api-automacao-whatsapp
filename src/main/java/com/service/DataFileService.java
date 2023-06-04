package com.service;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.DataFile;
import com.model.SendStatus;
import com.repository.DataFileRepository;

@Service
public class DataFileService {

	@Autowired
	DataFileRepository dataFileRepository;
	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public void updateSendStatus(Long id){
		var datafile = dataFileRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		SendStatus sendStatus = new SendStatus();
		sendStatus.setDatafile(datafile);
		sendStatus.setSend_photo(true);
	    sendStatus.setSend_detail(false);
	    entityManager.merge(sendStatus);
	    entityManager.flush();
	}
	
	
	public List<DataFile> findByBrandAndDate(Long idBrand, LocalDate date){
		return dataFileRepository.findByBrandIdAndDate(idBrand, date).orElseThrow(EntityNotFoundException::new);
	}
	
}
