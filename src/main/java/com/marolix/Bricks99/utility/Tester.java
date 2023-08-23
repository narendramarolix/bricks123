package com.marolix.Bricks99.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.marolix.Bricks99.dto.SellerRegistrationDTO;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException {
//		"C:\Users\bhara\Downloads\Resume Design Template - Made with PosterMyWall (1).jpg"

		String s = System.getProperty("user.home");
//		File f= new File("abc.txt");
//		FileInputStream fis= new FileInputStream(f);
//		File f2= new File(s+"\\Downloads\\"+f.getName());
//		FileOutputStream fos=new FileOutputStream(f2);
		System.out.println("invoked report generate method");
		List<SellerRegistrationDTO> list = new ArrayList<SellerRegistrationDTO>();
		SellerRegistrationDTO ss = new SellerRegistrationDTO();
		ss.setSellerId(12);
		list.add(ss);
		System.out.println(list);
		File f = new File("G:\\XL_Experiment\\WritingSellerData.xlsx");
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(f);
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		try {
//			if (!f.exists()) {
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

		Sheet sheet = workbook.createSheet("Sheet1");
		int rowNo = 0;

		for (SellerRegistrationDTO dto : list) {
			System.out.println(dto);
			Row row = sheet.createRow(rowNo);
			int colNo = 0; // Reset colNo for each new row

			Cell cell = row.createCell(colNo);
			cell.setCellValue(dto.getSellerId());
			System.out.println(dto.getSellerId());

			rowNo++; // Increment rowNo for each new seller
		}

		try (FileOutputStream fos = new FileOutputStream(f)) {
			workbook.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}

}
