package com.marolix.Bricks99.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.marolix.Bricks99.dto.SellerRegistrationDTO;

import com.marolix.Bricks99.entity.SellerRegistration;
import com.marolix.Bricks99.entity.SellerStatus;
import com.marolix.Bricks99.exception.Bricks99Exception;
import com.marolix.Bricks99.repository.SellerRegistrationRepository;

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private SellerRegistrationRepository sellerRegistrationRepository;
	@Autowired
	private Environment environment;

	@Override
	public List<SellerRegistrationDTO> viewAllRegisteredSellers(SellerStatus status) throws Bricks99Exception {
		List<SellerRegistration> list = sellerRegistrationRepository.findByStatus(status);
		return list.stream().map(p -> {
			SellerRegistrationDTO dto = SellerRegistrationDTO.entityToDTO(p);
			return dto;
		}).collect(Collectors.toList());

	}

	@Override
	public SellerRegistrationDTO viewRegisteredSeller(Integer seller_id) throws Bricks99Exception {
		Optional<SellerRegistration> op = sellerRegistrationRepository.findById(seller_id);
		SellerRegistration sr = op
				.orElseThrow(() -> new Bricks99Exception(environment.getProperty("AdminService.SellerNotRegistered")));
		return SellerRegistrationDTO.entityToDTO(sr);

	}

	@Override
	public void approveAllSellers() throws Bricks99Exception {
		List<SellerRegistration> list = sellerRegistrationRepository.findByStatus(SellerStatus.PENDING);
		if (list == null)
			throw new Bricks99Exception(environment.getProperty("AdminService.NO_NEW_SELLERS_FOUND"));
		else
			list.stream().forEach(t -> {
				t.setStatus(SellerStatus.APPROVED);
				sellerRegistrationRepository.save(t);
			});

	}

	@Override
	public void approveSeller(Integer seller_id) throws Bricks99Exception {
		Optional<SellerRegistration> op = sellerRegistrationRepository.findById(seller_id);
		SellerRegistration sr = op
				.orElseThrow(() -> new Bricks99Exception(environment.getProperty("AdminService.SellerNotRegistered")));
		sr.setStatus(SellerStatus.APPROVED);
		sellerRegistrationRepository.save(sr);
	}

	@Override
	public void rejectAllSellers() throws Bricks99Exception {
		List<SellerRegistration> list = sellerRegistrationRepository.findByStatus(SellerStatus.PENDING);
		if (list == null)
			throw new Bricks99Exception(environment.getProperty("AdminService.NO_NEW_SELLERS_FOUND"));
		else
			list.stream().forEach(t -> {
				t.setStatus(SellerStatus.REJECTED);
				sellerRegistrationRepository.save(t);
			});

	}

	@Override
	public void rejectSeller(Integer seller_id) throws Bricks99Exception {
		Optional<SellerRegistration> op = sellerRegistrationRepository.findById(seller_id);
		SellerRegistration sr = op
				.orElseThrow(() -> new Bricks99Exception(environment.getProperty("AdminService.SellerNotRegistered")));
		sr.setStatus(SellerStatus.REJECTED);
		sellerRegistrationRepository.save(sr);
	}

	@Override
	public List<SellerRegistrationDTO> viewAllSellers() throws Bricks99Exception {
		Iterable<SellerRegistration> i = sellerRegistrationRepository.findAll();
		List<SellerRegistrationDTO> dto = new ArrayList<SellerRegistrationDTO>();

		i.forEach(t -> {

			dto.add(SellerRegistrationDTO.entityToDTO(t));
		});
		if (!dto.isEmpty())
			return dto;
		else
			throw new Bricks99Exception(environment.getProperty("AdminService.NO_SELLERS_FOUND"));

	}

	@Override
	public void downloadSellers(HttpServletResponse response) throws Bricks99Exception {
		System.out.println("invoked report generate method");
		List<SellerRegistrationDTO> list = viewAllSellers();
//		File f = new File("G:/XL_Experiment/WritingSellerData.xlsx");
//		System.out.println(f.exists());
//		System.out.println(f.canWrite());
//		System.out.println(f.canRead());
		Workbook workbook = null;

		workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Sheet1");
		int rowNo = 0;

		for (SellerRegistrationDTO dto : list) {
			int colNo = 0;
			Row row = sheet.createRow(rowNo);

			Cell cell = row.createCell(colNo);

			cell.setCellValue(dto.getSellerId());
			System.out.println(dto.getSellerId());

			++rowNo;
		}

		try {

			// FileOutputStream fos = new FileOutputStream(f);
			ServletOutputStream fos = response.getOutputStream();
			workbook.write(fos);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
//		System.out.println("invoked report generate method");
//		List<SellerRegistrationDTO> list = viewAllSellers();
//		System.out.println(list);
//		File f = new File("G:\\XL_Experiment\\WritingSellerData.xlsx");
//		Workbook workbook = null;
//
//		try {
//			if (!f.exists()||f.length()==0) {
//
//				workbook = new XSSFWorkbook(); // Create a new workbook if the file doesn't exist
//			} else {
//
//				FileInputStream fis = new FileInputStream(f);
//				
//				workbook = new XSSFWorkbook(fis); // Load existing workbook
//				System.out.println("check");
//				fis.close();
//				
//			}
//
//			Sheet sheet = workbook.createSheet("Sheet1");
//			int rowNo = 0;
//
//			for (SellerRegistrationDTO dto : list) {
//				System.out.println(dto);
//				Row row = sheet.createRow(rowNo);
//				int colNo = 0; // Reset colNo for each new row
//
//				Cell cell = row.createCell(colNo);
//				cell.setCellValue(dto.getSellerId());
//				System.out.println(dto.getSellerId());
//
//				rowNo++; // Increment rowNo for each new seller
//			}
//
//			try (FileOutputStream fos = new FileOutputStream(f)) {
//				workbook.write(fos);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (workbook != null) {
//					workbook.close();
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

	}
}
