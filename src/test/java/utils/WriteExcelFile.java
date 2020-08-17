package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelFile {
	
	public static void writeValuesToExcel(ArrayList<String> keys, ArrayList<String> values) {
		
		try {	
			FileOutputStream fos = new FileOutputStream(new File(".\\testData\\Invalid.xlsx"));
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("Sheet1");	
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("FileName");
			row.createCell(1).setCellValue("ID");
			
			for (int i=0; i<keys.size(); i++) {
				XSSFRow row1 = sheet.createRow(i+1);
				row1.createCell(0).setCellValue(keys.get(i));
				row1.createCell(1).setCellValue(values.get(i));
			}
			
			wb.write(fos);
			fos.close();
			wb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}	
}
