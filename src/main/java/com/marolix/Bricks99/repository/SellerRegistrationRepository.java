package com.marolix.Bricks99.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.marolix.Bricks99.entity.SellerRegistration;
import com.marolix.Bricks99.entity.SellerStatus;

public interface SellerRegistrationRepository extends CrudRepository<SellerRegistration, Integer> {
	SellerRegistration findByEmail(String email);

	SellerRegistration findByContact(String contactNumber);

	List<SellerRegistration> findByStatus(SellerStatus status);
	SellerRegistration findByAdhaar(String adhaar);
	
}
