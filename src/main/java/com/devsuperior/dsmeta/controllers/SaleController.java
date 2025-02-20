package com.devsuperior.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinSummaryDTO;
import com.devsuperior.dsmeta.services.SaleService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<SaleMinDTO>> getReport(@RequestParam(defaultValue = "") String minDate,
			@RequestParam(defaultValue = "") String maxDate, @RequestParam(defaultValue = "") String name,
			@PageableDefault(size = 40) Pageable pageable) {
		Page<SaleMinDTO> result = service.searchToReport(minDate, maxDate, name, pageable);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<?> getSummary(@RequestParam(defaultValue = "") String minDate,
			@RequestParam(defaultValue = "") String maxDate, @PageableDefault(size = 40) Pageable pageable) {
		Page<SaleMinSummaryDTO> result = service.searchToSummary(minDate, maxDate, pageable);
		return ResponseEntity.ok(result);
	}
}
