package com.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.DataFileService;

@RestController
@RequestMapping("/datafile")
public class DataFileController {
	@Autowired
	DataFileService dataFileService;
	
	@GetMapping
	public ResponseEntity getByBrandAndDate(@RequestParam(required = true) Long idbrand, @RequestParam(required = true) String date) {
		try{
			return ResponseEntity.ok(dataFileService.findByBrandAndDate(idbrand, LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PutMapping("/sendstatus/{id}")
	public ResponseEntity updateSendStatus(@PathVariable Long id) {
		try {
			dataFileService.updateSendStatus(id);
			return ResponseEntity.ok(null);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	

}
