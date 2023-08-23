package com.marolix.Bricks99.service;

import com.marolix.Bricks99.dto.SellerRegistrationDTO;
import com.marolix.Bricks99.dto.UpdateDetailsDTO;
import com.marolix.Bricks99.dto.UserLoginDTO;
import com.marolix.Bricks99.exception.Bricks99Exception;

public interface SellerService {
	public String validLogin(UserLoginDTO dto) throws Bricks99Exception;

	public SellerRegistrationDTO sellerRegistration(SellerRegistrationDTO sellerDTO) throws Bricks99Exception;

	SellerRegistrationDTO sellerValidLogin(UserLoginDTO dto) throws Bricks99Exception;

	SellerRegistrationDTO updateDetails(UpdateDetailsDTO dto,Integer sellerId) throws Bricks99Exception;
}
