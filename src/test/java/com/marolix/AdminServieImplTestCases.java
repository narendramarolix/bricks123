package com.marolix;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marolix.Bricks99.dto.SellerRegistrationDTO;
import com.marolix.Bricks99.entity.SellerAddress;
import com.marolix.Bricks99.entity.SellerRegistration;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.repository.SellerRegistrationRepository;
import com.marolix.Bricks99.service.AdminService;
import com.marolix.Bricks99.service.AdminServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AdminServieImplTestCases {
	@Mock
	private SellerRegistrationRepository sellerRegistrationRepository;
	@InjectMocks
	private AdminService adminService = new AdminServiceImpl();

	@Test
	public void viewSellersValidTest() throws Bricks99Exception {
		List<SellerRegistrationDTO> ldto = new ArrayList<SellerRegistrationDTO>();
		SellerRegistrationDTO dto = new SellerRegistrationDTO();

		
		List<SellerRegistration> isr = new ArrayList<SellerRegistration>();
		SellerRegistration sr = new SellerRegistration();
		sr.setSellerId(1);
		sr.setAdhaar("");
		sr.setEmail("");
		sr.setContact("");
		sr.setFirstName("");
		sr.setLastName("");

		SellerAddress sa = new SellerAddress();
		sa.setAddressLine1("");
		sa.setAddressLine2("");
		sa.setCity("");
		sa.setPincode("");
		sr.setAddress(sa);
		isr.add(sr);
		dto = SellerRegistrationDTO.entityToDTO(sr);
		ldto.add(dto);
		SellerRegistrationDTO.entityToDTO(sr);
		Mockito.when(sellerRegistrationRepository.findAll()).thenReturn(isr);
		Assertions.assertEquals(ldto, adminService.viewAllSellers());

	}
}
