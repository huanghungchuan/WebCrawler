package com.how2java.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.how2java.pojo.Orideftype;
import com.how2java.pojo.Oriutype;
import com.how2java.service.OrideftypeService;
import com.how2java.service.OriutypeService;

import jxl.Sheet;
import jxl.Workbook;

@Controller
@RequestMapping("orideftype")
public class OrideftypeController {
	@Autowired
	OrideftypeService orideftypeService;
	
	@RequestMapping("insert")
	public ModelAndView insert(){
		System.out.println("this is a test");
		ModelAndView mav = new ModelAndView();
		String err = "";
		try {
			//读取excel
			File file = new File("D:/index3.xls");
			if(!file.exists()) {
				file.createNewFile();
			}
			Workbook book = Workbook.getWorkbook(file);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			//int col = sheet.getColumns();//列数
			int row = sheet.getRows();//行数
			for(int i = 1; i < row; i++) {
            	//
				Orideftype orideftype = new Orideftype();
				orideftype.setURL(sheet.getCell(0, i).getContents());
				orideftype.setSIVA(sheet.getCell(1, i).getContents());
				orideftype.setFILE_COUNT(sheet.getCell(2, i).getContents());
				orideftype.setLANGS(sheet.getCell(3, i).getContents());
				orideftype.setLANGS_BYTE_COUNT(sheet.getCell(4, i).getContents());
				orideftype.setLANGS_LINES_COUNT(sheet.getCell(5, i).getContents());
				orideftype.setLANGS_FILE_COUNT(sheet.getCell(6, i).getContents());
				orideftype.setCOMMIT_COUNT(sheet.getCell(7, i).getContents());
				orideftype.setBRANCHES_COUNT(sheet.getCell(8, i).getContents());
				orideftype.setFORK_COUNT(sheet.getCell(9, i).getContents());
				orideftype.setEMPTY_LINES_COUNT(sheet.getCell(10, i).getContents());
				orideftype.setCODE_LINES_COUNT(sheet.getCell(11, i).getContents());
				orideftype.setCOMMENT_LINES_COUNT(sheet.getCell(12, i).getContents());
				orideftype.setLICENSE(sheet.getCell(13, i).getContents());
				
				if(orideftype.vifyColLen(orideftype)) {
					System.out.println("test");
					 orideftypeService.init_insert(orideftype);
				}else {
					err += String.valueOf(i);
				}
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(err);
		mav.setViewName("listCategory");
		return mav;
	}
	
	
	
	@RequestMapping("insertSiva")
	public ModelAndView insertSiva(){
		ModelAndView mav = new ModelAndView();
		try {
			InputStreamReader reader = new InputStreamReader( new FileInputStream("F:\\cobothang\\deepdata\\src\\word\\file2.txt")); // 建立一个输入流对象reader  
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
            String line = "";  
            line = br.readLine();  
            while (line != null) {  
                line = br.readLine(); // 一次读入一行数据  
                Orideftype orideftype = new Orideftype();
				orideftype.setSIVA(line);
				orideftypeService.init_insert(orideftype);
            } 
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("listCategory");
		return mav;
	}
	
	
}
