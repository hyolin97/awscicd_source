package kr.co.mz.comm.utils.excel.excelutils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExcelCellRef {
	private static final Logger logger = LoggerFactory.getLogger(ExcelCellRef.class);
	
	/**
     * Cell에 해당하는 Column Name을 가젼온다(A,B,C..)
     * 만약 Cell이 Null이라면 int cellIndex의 값으로
     * Column Name을 가져온다.
     * @param cell
     * @param cellIndex
     * @return
     */
    public static String getName(Cell cell, int cellIndex) {
        int cellNum = 0;
        if(cell != null) {
            cellNum = cell.getColumnIndex();
        }
        else {
            cellNum = cellIndex;
        }
        
        return CellReference.convertNumToColString(cellNum);
    }
    
    public static String getValue(Cell cell) {
        String value = "";
        
        if(cell == null) {
            value = "";
        }
        else {
        	if( cell.getCellType() == CellType.FORMULA ) {
        		if( cell.getCachedFormulaResultType() == CellType.NUMERIC ) {
        			value = cell.getNumericCellValue() + "";
        		} else if( cell.getCachedFormulaResultType() == CellType.STRING ) {
        			value = cell.getStringCellValue();
        		}
            }
            else if( cell.getCellType() == CellType.NUMERIC ) {
            	if( DateUtil.isCellDateFormatted(cell) ) {
            		// 엑셀에서 double 값이 오차로 인해 시간상으로 1초의 오차가 생김
            		// 11:59:59 이렇게 나오는 것을 12:00:00 으로 나오게 하기 위채 추가됨
            		double tmp = cell.getNumericCellValue()*1000000 + 5;
            		tmp = Math.round(tmp)/1000000.0;
            		cell.setCellValue(tmp);
            		Date date = cell.getDateCellValue();
            		value = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            	} else {
            		value = cell.getNumericCellValue() + "";
            	}
            }
            else if( cell.getCellType() == CellType.STRING ) {
                value = cell.getStringCellValue();
            }
            else if( cell.getCellType() == CellType.BOOLEAN ) {
                value = cell.getBooleanCellValue() + "";
            }
            else if( cell.getCellType() == CellType.ERROR ) {
                value = cell.getErrorCellValue() + "";
            }
            else if( cell.getCellType() == CellType.BLANK ) {
                value = "";
            }
            else {
                value = cell.getStringCellValue();
            }
        }
        
        return value;
    }
    
}
