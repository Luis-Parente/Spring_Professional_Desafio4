package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleMinSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinDTO(obj.id, obj.date, obj.amount, obj.seller.name) "
			+ "FROM Sale obj " + "WHERE obj.date BETWEEN :minDate AND :maxDate "
			+ "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
	Page<SaleMinDTO> searchToReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleMinSummaryDTO(obj.seller.name, SUM(obj.amount) AS total) "
			+ "FROM Sale obj " + "WHERE obj.date BETWEEN :minDate AND :maxDate " + "GROUP BY obj.seller.name")
	Page<SaleMinSummaryDTO> searchToSummary(LocalDate minDate, LocalDate maxDate, Pageable pageable);
}
