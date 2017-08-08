package baseutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * Excel 数据表对象
 * @author penghong
 *
 */
public class ExcelSheet{
	private String filepath;//文件路径
	private int numerSheet;//excel表格位置
    private  HSSFWorkbook workbook;
    private HSSFSheet sheet;
    
    
	static String value = null;
	static ArrayList<String> list = new ArrayList<String>();

	static ArrayList<String> values = new ArrayList<String>();

	public ExcelSheet(String filepath,int numberSheet)
	{   
		this.filepath=filepath;
		this.numerSheet=numberSheet;
		File file = new File(filepath);
		try {
			
			  workbook = new HSSFWorkbook(new FileInputStream(file));
			  sheet = workbook.getSheetAt(numberSheet);
			  
		} catch (IOException e) {

			e.printStackTrace();
		}
	
	}
	

	/*
	 * 获取单元格的值
	 * @param rowindex 行
	 * @param rankindex 列
	 * @return value 单元格的值
	 * @throws Exception
	 */
	public String getsigdata(int rowindex,int rankindex) throws Exception {
		HSSFRow row=null;
		HSSFCell cell=null;
		int lastrow= sheet.getLastRowNum();
        if(rowindex<=lastrow)
        {
			row = sheet.getRow(rowindex);
			cell = row.getCell(rankindex);

        }
        return getValue(cell);
	
	}
	
	
	
	
	

	
	/*
	 * 获取列的所有值
	 * @param rankindex 列索引
	 * @return rankvalues 值列表
	 */
	public List<String> getrankvalue(int rankindex) {
		ArrayList<String> rankvalues = new ArrayList<String>();
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 1; i <= lastRowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			HSSFCell cell = row.getCell(rankindex);
			String value=getValue(cell);
			
			if (value== "") {
				break;
			} else {
				rankvalues.add(value);
			}
		}
		return rankvalues;
	}
	
     /*
	 * 获取行的所有值
	 * @param rowindex 行数
	 * @return rankvalues 行值列表
      */
	public List<String> getrowvalue(int rowindex)
	{
		ArrayList<String> rowvalues = new ArrayList<String>();
		HSSFRow row = sheet.getRow(rowindex);
		Iterator<Cell> iterator=row.cellIterator();
		HSSFCell cell=null;
		while(iterator.hasNext())
		{   
			
			rowvalues.add(getValue(iterator.next()));
		}
		return rowvalues;
	}
	
	
	/*
	 * 写入值至单元格
	 * @param rowindex 行数
	 * @param rankindex 列数
	 * @param writevalue 要写入的字符串
	 */
	
    public  void write(int rowindex,int rankindex,String writevalue){

    	try {
        	HSSFRow row =sheet.getRow(rowindex);
        	HSSFCell cell=row.getCell(rankindex);
			cell.setCellValue(writevalue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    } 
	

	/**
	 * 获取行数
	 * @return rows 总行数
	 */
	public int getrows(){
		
		int rows = sheet.getLastRowNum();
		return rows;
	}
	
	
	
	
	/*
	 * 格式化单元格数值
	 * @param cell 单元格
	 */
	private static String getValue (Cell cell)
	{
		if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN)
		{
			// 返回布尔类型的值
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC)
		{
			// 返回数值类型的值
			return String.valueOf((long)(cell.getNumericCellValue()));
		}else
		{
			// 返回字符串类型的值
			return String.valueOf(cell.getStringCellValue());
		}
	}
	
	public String getFilePath()
	{
		return filepath;
	}
	
	public void setFilePath(String filepath)
	{
		this.filepath=filepath;
	}

  
	
}
