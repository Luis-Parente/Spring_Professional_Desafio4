package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleMinDTO> searchToReport(String min, String max, String name, Pageable pageable) {
		LocalDate minDate = null;
		LocalDate maxDate = null;

		if (min.isEmpty()) {
			minDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1);
		} else {
			minDate = LocalDate.parse(min);
		}
		if (max.isEmpty()) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			maxDate = LocalDate.parse(max);
		}

		Page<SaleMinDTO> result = repository.searchToReport(minDate, maxDate, name, pageable);

		return result;
	}

	public Page<SaleMinSummaryDTO> searchToSummary(String min, String max, Pageable pageable) {
		LocalDate minDate = null;
		LocalDate maxDate = null;

		if (min.isEmpty()) {
			minDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1);
		} else {
			minDate = LocalDate.parse(min);
		}
		if (max.isEmpty()) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			maxDate = LocalDate.parse(max);
		}

		Page<SaleMinSummaryDTO> result = repository.searchToSummary(minDate, maxDate, pageable);

		return result;
	}
}
