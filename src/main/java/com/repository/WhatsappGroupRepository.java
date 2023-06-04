package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Brand;
import com.model.WhatsappGroup;

public interface WhatsappGroupRepository extends JpaRepository<WhatsappGroup, Long> {

	@Query(value = "select true from whatsapp.whatsappgroup where whatsapp.whatsappgroup.name = :name ",nativeQuery = true)
	Optional<Boolean> checkIfExistsByName(@Param(value = "name") String name);
	
	@Query(value = "select distinct b from whatsappgroup w inner join brand b on b.id = w.brand_id order by b.id", nativeQuery = true)
	List<Brand> findBrandInGroup(@Param(value = "limit") Integer limit,@Param(value = "offset") Integer offset);
	@Query(value = "select w from WhatsappGroup w where w.brand.id=:idBrand")
	Optional<List<WhatsappGroup>> findByBrand(@Param(value = "idBrand")Long id);
}
