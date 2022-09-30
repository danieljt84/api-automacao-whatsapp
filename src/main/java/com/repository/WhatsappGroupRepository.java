package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.WhatsappGroup;

public interface WhatsappGroupRepository extends JpaRepository<WhatsappGroup, Long> {

	@Query(value = "select true from whatsappgroup where whatsappgroup.name = :name ",nativeQuery = true)
	Optional<Boolean> checkIfExistsByName(@Param(value = "name") String name);
}
