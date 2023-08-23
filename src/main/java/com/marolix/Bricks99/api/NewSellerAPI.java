package com.marolix.Bricks99.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.marolix.Bricks99.dto.SellerRegistrationDTO;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.service.SellerService;

public class NewSellerAPI extends SellerAPI {
	@Autowired
	private SellerService sellerService;
	@Autowired
	private Environment environment;

	@PostMapping(value = "register")
	public ResponseEntity<SellerRegistrationDTO> registerSeller(@RequestBody @Valid SellerRegistrationDTO sellerDTO)
			throws Bricks99Exception {

		return new ResponseEntity<SellerRegistrationDTO>(sellerService.sellerRegistration(sellerDTO),
				HttpStatus.CREATED);
	}

}
