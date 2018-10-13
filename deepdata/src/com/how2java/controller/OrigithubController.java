package com.how2java.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.how2java.pojo.Origithub;
import com.how2java.service.OrigithubService;

import jxl.Sheet;
import jxl.Workbook;

@Controller
@RequestMapping("origit")
public class OrigithubController {
	@Autowired
	OrigithubService origithubService;
	
	@RequestMapping(value="insertOrigithub", method = RequestMethod.POST)
	public ModelAndView insertOrigithub(){
		ModelAndView mav = new ModelAndView();
		try {
			//读取excel
			File file = new File("D:/deepcode/javaurl.xls");
			if(!file.exists()) {
				file.createNewFile();
			}
			Workbook book = Workbook.getWorkbook(file);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			//int col = sheet.getColumns();//列数
			int row = sheet.getRows();//行数
			for(int i = 0; i < row; i++) {
            	//
            	Origithub origithub = new Origithub();
                origithub.setGithub(sheet.getCell(0, i).getContents());
                origithub.setProtype("Java");
                 origithubService.insertOrigithub(origithub);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.setViewName("listCategory");
		return mav;
		
	}
	
	
	
}
