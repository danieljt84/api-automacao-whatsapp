package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long>{

	List<Shop> findByNameContaining(String name);
	
	@Query(value = "select s from WhatsappGroup w, Shop s join w.shops ss where ss.id = s.id and w.id = :whatsappGroupId")
	List<Shop> getByWhatsappGroup(@Param(value = "whatsappGroupId") Long whatsappGroupId);
}
