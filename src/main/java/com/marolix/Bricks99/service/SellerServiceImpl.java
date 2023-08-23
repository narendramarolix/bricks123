package com.marolix.Bricks99.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marolix.Bricks99.dto.SellerAddressDTO;
import com.marolix.Bricks99.dto.SellerRegistrationDTO;
import com.marolix.Bricks99.dto.UpdateDetailsDTO;
import com.marolix.Bricks99.dto.UserLoginDTO;
import com.marolix.Bricks99.entity.SellerAddress;
import com.marolix.Bricks99.entity.SellerRegistration;
import com.marolix.Bricks99.entity.SellerStatus;
import com.marolix.Bricks99.entity.UserLogin;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.repository.SellerRegistrationRepository;
import com.marolix.Bricks99.repository.UserLoginRepository;

@Service(value = "sellerService")
@Transactional
public class SellerServiceImpl implements SellerService {

	@Autowired
	public UserLoginRepository userLoginRepository;
	@Autowired
	private SellerRegistrationRepository sellerRegistrationRepository;
	@Autowired
	private Environment environment;

	private String validation(String variable, String password, UserLogin ulogin) throws Bricks99Exception {
		if (ulogin == null)
			throw new Bricks99Exception(environment.getProperty("UserService.USER_NOT_FOUND"));

		if (variable.equals(ulogin.getEmail()) && password.equals(ulogin.getPassword())) {
			return "UserService.Login_STATUS";
		} else
			throw new Bricks99Exception(environment.getProperty("UserService.Invalid_Login"));
	}

	@Override
	public String validLogin(UserLoginDTO dto) throws Bricks99Exception {
		UserLogin ulogin = null;
		if (dto.getUser_name() != null) {
			ulogin = userLoginRepository.findByUserName(dto.getUser_name());
			return validation(dto.getUser_name(), dto.getPassword(), ulogin);
		} else if (dto.getContact() != null) {
			ulogin = userLoginRepository.findByContact(dto.getContact());
			return validation(dto.getContact(), dto.getPassword(), ulogin);

		} else if (dto.getEmail() != null)
			ulogin = userLoginRepository.findByEmail(dto.getEmail());
		return validation(dto.getEmail(), dto.getPassword(), ulogin);

	}

	@Override
	public SellerRegistrationDTO sellerRegistration(SellerRegistrationDTO sellerDTO) throws Bricks99Exception {

		SellerRegistration sr = sellerRegistrationRepository.findByContact(sellerDTO.getContact());
		if (sr != null)
			throw new Bricks99Exception(environment.getProperty("SellerService.Phone_Exists"));
		SellerRegistration sr2 = sellerRegistrationRepository.findByEmail(sellerDTO.getEmail());
		if (sr2 != null)
			throw new Bricks99Exception(environment.getProperty("SellerService.Email_Exists"));
		SellerRegistration sr3 = sellerRegistrationRepository.findByAdhaar(sellerDTO.getAdhaar());
		if (sr3 != null)
			throw new Bricks99Exception(environment.getProperty("SellerService.Adhaar_Exists"));
		SellerRegistration newSR = SellerRegistrationDTO.dtoToEntity(sellerDTO);

		Integer i = sellerRegistrationRepository.save(newSR).getSellerId();
		sellerDTO.setSellerId(i);
		UserLogin u = new UserLogin();
		u.setPassword(sellerDTO.getPassword());
		String s[] = sellerDTO.getEmail().split("@");
		String username = s[0] + sellerDTO.getContact().substring(2, 6);
		u.setUserName(username);

		u.setContact(sellerDTO.getContact());
		u.setEmail(sellerDTO.getEmail());
		userLoginRepository.save(u);
		return sellerDTO;
	}

	@Override
	public SellerRegistrationDTO sellerValidLogin(UserLoginDTO dto) throws Bricks99Exception {

		SellerRegistration s = sellerRegistrationRepository.findByContact(dto.getContact());
	
		if (s == null)
			throw new Bricks99Exception(environment.getProperty("SellerService.Seller_NOT_REGISTERED"));
		if (!dto.getContact().equals(s.getContact()) || !dto.getPassword().equals(s.getPassword())) {
			
			throw new Bricks99Exception(environment.getProperty("SellerService.INVALID_CREDENTIALS"));}
		return SellerRegistrationDTO.entityToDTO(s);
	}

	@Override
	public SellerRegistrationDTO updateDetails(UpdateDetailsDTO dto, Integer sellerId) throws Bricks99Exception {

		Optional<SellerRegistration> opt = sellerRegistrationRepository.findById(sellerId);
		SellerRegistration sr = opt.orElseThrow(() -> new Bricks99Exception("SellerService.Seller_NOT_FOUND"));
		
		SellerRegistration sr1 = sellerRegistrationRepository.findByContact(dto.getContact());
	
		SellerRegistration sr2 = sellerRegistrationRepository.findByEmail(dto.getEmail());
	
		if (sr2 == null) {
			sr.setEmail(dto.getEmail());
		} else {
			if (!sr2.equals(sr)) {

				throw new Bricks99Exception(environment.getProperty("SellerService.Email_Exists"));
			} else
				sr.setEmail(dto.getEmail());

		}
		if (sr1 == null) {
			sr.setContact(dto.getContact());
		} else {
			if (!sr1.equals(sr)) {

				throw new Bricks99Exception(environment.getProperty("SellerService.Phone_Exists"));
			} else
				sr.setContact(dto.getContact());

		}
		sr.setFirstName(dto.getFirstName());
		sr.setLastName(dto.getLastName());

		sellerRegistrationRepository.save(sr);
		System.out.println("check6");
		return SellerRegistrationDTO.entityToDTO(sr);
	}

}
