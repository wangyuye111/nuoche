package com.nuoche.redirect.resolverB.interface4;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.nuoche.classroot.interface4.JyLogDetect;
import com.nuoche.classroot.interface4.JyLogDetect.DataType;

import jxl.Cell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExcelUtil {

	private File file;
	private WritableWorkbook m_workbook;
	private WritableSheet m_sheet;
	
	public ExcelUtil(String filePath) throws BiffException, IOException {
		
		File file = new File(filePath);
		if(file.exists()) {
			Workbook wb = Workbook.getWorkbook(file);
			m_workbook = wb.createWorkbook(file, wb);
		} else {
			m_workbook = Workbook.createWorkbook(new FileOutputStream(file));
		}
		
		if(m_workbook.getNumberOfSheets() == 0) {
			m_sheet = m_workbook.createSheet("sheet1", 0);
		} else {
			m_sheet = m_workbook.getSheet(0);
		}
		
		this.file = file;
	}
	
	@SuppressWarnings("deprecation")
	public static  void appendRow(ArrayList<Map<String,Object>> list,String path,File imgpath,JyLogDetect log) {
		HSSFWorkbook wb = new HSSFWorkbook();  
	    HSSFSheet sheet = wb.createSheet("new sheet");  
	    sheet.setColumnWidth((short)1, (short)(25 * 256));
	    HSSFCellStyle style = wb.createCellStyle(); // 样式对象  
	    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直  
	    //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平  
		HSSFRow row0 = sheet.createRow((short) 0);  
		String[] title = {"编号", "二维码"};
		for(int i = 0 ;i < title.length; i++) {
			HSSFCell ce = row0.createCell((short) i);  
			ce.setCellValue(title[i]);
		}
		
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		
		
		
		String fullPath = "img/qrcodeimg";
		String filedir = imgpath+ File.separator  + fullPath;
		File fullDir = new File(filedir);
		if (!fullDir.exists()) {
			fullDir.mkdirs();
		}
		log.send(DataType.basicType, "01107", "exportexcel()-excelFile: ", fullDir.getAbsolutePath()+"-------"+filedir);
		
		log.send(DataType.basicType, "01107", "exportexcel()-excelFile: ", list.size());
		int row=0;
		for(int i=0;i<list.size();i++){
			
			HSSFRow rows=null;
			HSSFCell ces=null;
			BufferedImage bufferImg = null;
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			row=row+1;
			rows = sheet.createRow((short) row);  
			rows.setHeightInPoints(125);
			ces = rows.createCell((short) 0);  
			ces.setCellValue(list.get(i).get("card_num").toString());
			
			String fileName = list.get(i).get("card_num").toString()+".jpg";
			String filepath = filedir + File.separator + fileName;
			log.send(DataType.basicType, "01107", "exportexcel()-excelFile: ", filepath);
			try {
				bufferImg = ImageIO.read(new File(filepath));
				ImageIO.write(bufferImg, "jpg", byteArrayOut);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				log.send(DataType.basicType, "01107", "exportexcel()-excelFile: ", e1);
			}
			
			ces = rows.createCell((short) 1);
			// anchor主要用于设置图片的属性
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 1, row, (short) 1, row);
			// 插入图片
			patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
			//ces.setCellValue(list.get(i).get("patient_name").toString());
			try {
				bufferImg.flush();
				byteArrayOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.send(DataType.basicType, "01107", "exportexcel()-excelFile: ", e);
		}  
		try {
			wb.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.send(DataType.basicType, "01107", "exportexcel()-excelFile: ", e);
		}  
		
	}
	
	
	public int getRows() {
		return m_sheet.getRows();
	}
	
	public ArrayList<String> getRow(int index) {
		
		int totalRow = m_sheet.getRows();
		if(totalRow <= index) {
			return null;
		}
		
		ArrayList<String> strList = new ArrayList<String>();
		Cell[] cells = m_sheet.getRow(index);
		
		for(int i = 0; i < cells.length; i++) {
			strList.add(cells[i].getContents());
		}
		
		return strList;
	}
	
	public int getColumns() {
		return m_sheet.getColumns();
	}
	
	public ArrayList<String> getColumn(int index) {
		
		int totalColumn = m_sheet.getColumns();
		if(totalColumn <= index) {
			return null;
		}
		
		ArrayList<String> strList = new ArrayList<String>();
		Cell[] cells = m_sheet.getColumn(index);
		
		for(int i = 0; i < cells.length; i++) {
			strList.add(cells[i].getContents());
		}
		
		return strList;
	}
	
	public void appendRow(ArrayList<String> list) {
		
		if(list == null || list.size() == 0) {
			return;
		}
		
		int rownum = m_sheet.getRows();
		
		try {
			for(int i = 0; i < list.size(); i++) {
				Label label = new Label(i,rownum,list.get(i));
				m_sheet.addCell(label);
			}
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public void appendRow1(ArrayList<String> list) {
		
		if(list == null || list.size() == 0) {
			return;
		}
		
		int rownum = m_sheet.getRows();
		
		try {
			for(int i = 0; i < list.size(); i++) {
				Label label = new Label(i,rownum,list.get(i));
				m_sheet.addCell(label);
				//m_sheet.addMergedRegion(new CellRangeAddress(0,0,14,14));
			}
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void appendColumn(ArrayList<String> list) {
		
		if(list == null || list.size() == 0) {
			return;
		}
		
		int columnnum = m_sheet.getColumns();
		
		try {
			for(int i = 0; i < list.size(); i++) {
				Label label = new Label(columnnum,i,list.get(i));
				m_sheet.addCell(label);
			}
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flush();
	}
	
	public ArrayList<ArrayList<String>> getAllInfo() {
		
		if(isEmpty()) {
			return null;
		}
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		int totalRow = getRows();
		for(int i = 0; i < totalRow; i++) {
			ArrayList<String> listRow = getRow(i);
			list.add(listRow);
		}
		return list;
	}
	
	public boolean isEmpty() {
		
		int rownum = m_sheet.getRows();
		int columnnum = m_sheet.getColumns();
		
		return (rownum == 0 && columnnum == 0);
	}
	
	public void flush() {
		if(m_workbook != null) {
			try {
				m_workbook.write();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void close() {
		flush();
		if(m_workbook != null) {
			try {
				m_workbook.close();
			} catch (WriteException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
