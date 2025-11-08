package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.core.TSFBuilder;

import WebDriverManager.GetConfigData;

public class ExcelUtils {
	private static HashMap<String,String> dataFromExcel=new HashMap<String,String>();
	private static List<String> columnsList=new ArrayList();	
	private static List<String> rowList=new ArrayList();

	public static HashMap<String,String> readExcelData(){
		try {			
			File fs=new File(new FileInputStream(System.getProperty("user.dir"))+ GetConfigData.getTestDataPath());
			XSSFWorkbook workbook=new XSSFWorkbook(fs);
			XSSFSheet sheet=workbook.getSheetAt(0);
			List<String>columnListHeaders=readColumns(sheet);
			List<String>rowList=readRows(sheet,GetConfigData.getScenarioName());
			for (int i = 0; i < columnListHeaders.size(); i++) {
				dataFromExcel.put(columnListHeaders.get(i), rowList.get(i));
			}			
			return dataFromExcel;
		}catch(Exception e)
		{
			e.printStackTrace();			
			return null;

		}
	}
	private static List<String> readColumns(XSSFSheet sheet) {
		int totalRows=sheet.getLastRowNum();
		int totalColumns=sheet.getRow(0).getLastCellNum();
		for (int c = 0; c < totalColumns; c++) {
			columnsList.add(sheet.getRow(0).getCell(c).getStringCellValue());
		}
		return  columnsList;
	}
	private static List<String> readRows(XSSFSheet sheet,String ScenarioName) {
		int index=-1;
		int totalRows=sheet.getLastRowNum();
		for (int r = 1; r <= totalRows; r++) {			
			if (sheet.getRow(r).getCell(0).getStringCellValue().equalsIgnoreCase(ScenarioName)) {
				for (int c = 0; c < columnsList.size(); c++) {
					rowList.add(sheet.getRow(r).getCell(c).getStringCellValue());
				}
				break;
			}
		}
		return  rowList;

	}

}
