package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
	
	
	@Query(value = "select true from brand where brand.name = :name ",nativeQuery = true)
	Optional<Boolean> checkIfExistsByName(@Param(value = "name") String name);
    List<Brand> findByNameContaining(String name);
}
