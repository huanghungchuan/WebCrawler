package com.how2java.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.how2java.pojo.Oriutype;
import com.how2java.service.OriutypeService;

import jxl.Sheet;
import jxl.Workbook;

@Controller
@RequestMapping("oriutype")
public class OriutypeController {
	@Autowired
	OriutypeService oriutypeService;
	
	@RequestMapping("insertOriutype")
	public ModelAndView insertOriutype(){
		ModelAndView mav = new ModelAndView();
		
		try {
			//读取excel
			File file = new File("D:/deepcode/dlevel.xls");
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
				String str = sheet.getCell(0, i).getContents();
				if(str != null && str.length() <= 1000) {
					Oriutype oriutype = new Oriutype();
					oriutype.setDeftype(removeSpaces(sheet.getCell(1, i).getContents()));
					oriutype.setDlevel(dealLevel(removeSpaces(sheet.getCell(2, i).getContents())));
					oriutype.setLevelinfo(removeSpaces(sheet.getCell(2, i).getContents()));
					oriutype.setLink(sheet.getCell(0, i).getContents());
					oriutype.setProtype("Java");
					oriutype.setGithub(getGitByDealLink(sheet.getCell(0, i).getContents()));
					if(oriutype.vifyColLen(oriutype)) {
						 oriutypeService.insertOriutype(oriutype);
					}
	               
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.setViewName("listCategory");
		return mav;
	}

	/*
	 * 导入deflinktype时处理level
	 */
	public  String dealLevel(String str) {
		if(str == null ) {
			return "";
		}else if(str.contains("priority1")) {
			str = "3";
		}else if(str.contains("priority2")) {
			str = "2";
		}else if(str.contains("priority3")) {
			str = "1";
		}else {
			return "";
		}
		return str;
	}
	
	/*
	 * 得到github项目url
	 */
	public static  String getGitByDealLink(String str) {
		if(str == null ) {
			return "";
		}
		String[] strArr = str.split("/");
		str = "";
		if(strArr != null && strArr.length > 3) {
			strArr[2] = "github.com";
			for(int i=0; i< 6; i++) {
				if(i != 3) {
					str += strArr[i] + "/";
				}
			}
		}
		return str;
	}
	
		
	/*
	 * 去除双空格及换行符等
	 */
	public  String removeSpaces(String str) {
		if(str == null ) {
			return "";
		}
		while(str.indexOf("  ") >=0) {
			str = str.replace("\r", "").replace("\n", "").replace("  ", " ");
		}
		return str;
	}
		
		
	 public static void main(String args[]) throws IOException {  
		
		 String url = "a\r\n";
		 String url2 = "c\r\n";
		 writeFile(url);
		 writeFile(url2);
	}  
	
	
	 public static void writeFile(String url)  throws IOException  {
		 String filepath = "D:\\giturl.txt";//文件路径
		 OutputStream out = null ;
		 try {
			File file = new File(filepath);//获取文件对象
			if(!file.exists()) {//文件不存在，则重新创建
				file.createNewFile();
			}
			out = new FileOutputStream(file,true);
			byte b[] =  url.getBytes();
			out.write(b);	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) out.close();
		}		 
	 }
}
