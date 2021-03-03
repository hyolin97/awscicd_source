package kr.co.mz.comm.utils.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value="ExcelDownloadService")
public class ExcelDownloadService {
	private static final Logger logger = LoggerFactory.getLogger(ExcelDownloadService.class);

	public XSSFWorkbook testDownExcel() throws Exception {
		logger.debug("testDownExcel");
		
		// 워크북 생성
		XSSFWorkbook wb = new XSSFWorkbook();
		// 워크시트 생성
		Sheet sheet = wb.createSheet();
		
		// 제목 row
		Row row0 = sheet.createRow(0);
		row0.createCell(0).setCellValue("이름");
		row0.createCell(1).setCellValue("연락처");
		row0.createCell(2).setCellValue("이메일");
		
		// Data row
		Row row1 = sheet.createRow(1);
		row1.createCell(0).setCellValue("고기홍");
		row1.createCell(1).setCellValue("010-1234-5678");
		row1.createCell(2).setCellValue("abcd@gmail.com");
		
		Row row2 = sheet.createRow(2);
		row2.createCell(0).setCellValue("홍길동");
		row2.createCell(1).setCellValue("010-9876-5432");
		row2.createCell(2).setCellValue("zyxw@gmail.com");
		
		// 셀크기 자동으로 변경
		for(int i=0; i<3; i++) {
			sheet.autoSizeColumn(i);
			sheet.setColumnWidth(i, (sheet.getColumnWidth(i)+512));
		}
		
		return wb;
	}
}
