package kr.co.mz.comm.utils.excel;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.co.mz.comm.utils.excel.excelutils.ExcelRead;
import kr.co.mz.comm.utils.excel.excelutils.ExcelReadOption;

@Service(value = "ExcelUploadService")
public class ExcelUploadService {
	private static final Logger logger = LoggerFactory.getLogger(ExcelUploadService.class);

	public void testUploadExcel(File destFile) throws Exception {
		logger.debug("testUploadExcel");
		ExcelReadOption excelReadOption = new ExcelReadOption();
		excelReadOption.setFilePath(destFile.getAbsolutePath());
		logger.debug("excelReadOption.getFilePath="+excelReadOption.getFilePath());
		
		excelReadOption.setOutputColumns("A","B","C");
        excelReadOption.setStartRow(2);
        
        List<Map<String, String>>excelContent = ExcelRead.read(excelReadOption);
        for(Map<String, String> article: excelContent){
        	logger.debug(article.get("A"));
        	logger.debug(article.get("B"));
        	logger.debug(article.get("C"));
        }
	}
}
