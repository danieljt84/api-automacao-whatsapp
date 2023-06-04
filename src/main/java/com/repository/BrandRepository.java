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
    
    @Query(value = "select * from operation.brand b order by b.id asc limit :limit offset :offset",nativeQuery = true)
    List<Brand> findAll(@Param(value = "limit") Integer limit,@Param(value = "offset") Integer offset);
    
    @Query(value = "select distinct b.name ,b.id  from whatsapp.whatsappgroup w inner join operation.brand b on b.id = w.brand_id order by b.id limit :limit offset :offset", nativeQuery = true)
	List<Brand> findBrandInGroup(@Param(value = "limit") Integer limit,@Param(value = "offset") Integer offset);
}
