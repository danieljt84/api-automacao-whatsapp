package com.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.DataFile;

@Repository
public interface DataFileRepository extends JpaRepository<DataFile, Long>{
	
	@Query(value = "select d from DataFile d where d.data=:date and d.brand.id=:idBrand and d.id not in (select s.id from SendStatus s)")
	Optional<List<DataFile>> findByBrandIdAndDate(@Param("idBrand") Long idBrand, @Param("date") LocalDate date);

}
