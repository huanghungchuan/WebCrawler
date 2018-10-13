package com.how2java.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.how2java.pojo.MvnUrlModel;
import com.how2java.service.MvnRepService;

@Controller
@RequestMapping("mvnController")
public class MvnController {
	@Autowired
	MvnRepService mvnRepService;
	private static String llqDriver;
	private static String driverpath;
	static {
		// 读取配置文件
		ResourceBundle resource = ResourceBundle.getBundle("deepcode");
		setLlqDriver(resource.getString("llqDriver")); // 浏览器驱动名称
		setDriverpath(resource.getString("driverpath")); // 浏览器驱动路径
		
	}
	
/*********************1.获取左菜单模块链接*********************************************************/	
	@RequestMapping("getModUrl")
	public void getModUrl() {
		WebDriver driver = null;
		try {
			// 加载驱动
			System.setProperty(llqDriver, driverpath);
			driver = new ChromeDriver();
			driver.get("http://mvnrepository.com/");
			getModURL(driver);	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (driver != null)
			driver.close();
		}
	}
	public void getModURL(WebDriver driver) {
		List<WebElement> linkList = driver.findElements(By.cssSelector("ul.categories li a"));
		System.out.println(linkList.size());
		if(linkList != null ) {
			for(WebElement webElement : linkList) {	
				MvnUrlModel mvnUrlModel = new MvnUrlModel();
				mvnUrlModel.setModUrl(webElement.getAttribute("href"));
				mvnUrlModel.setUrlText(webElement.getText());
				mvnUrlModel.setLevel("1");
				//添加模块路径
				if(isNotRepeat(webElement.getAttribute("href"),"1")) {
					mvnRepService.add(mvnUrlModel);
				}
			}
		}
	}
/*********************2.获取模块中子资源链接*********************************************************/		
	@RequestMapping("getModChildUrl")
	public void getModChildUrl() {
		WebDriver driver = null;
		try {
			// 加载驱动
			System.setProperty(llqDriver, driverpath);
			driver = new ChromeDriver();
			getModChildURL(driver);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (driver != null)
			driver.close();
		}
	}	
	
	public void getModChildURL(WebDriver driver ) throws InterruptedException {
		MvnUrlModel mvnUrlModel = new MvnUrlModel();
		mvnUrlModel.setLevel("1");
		List<MvnUrlModel> mvnList =  mvnRepService.getList(mvnUrlModel);
		if(mvnList != null ) {
			for(MvnUrlModel mvnUrl : mvnList) {
				Thread.sleep(2000);
				driver.get(mvnUrl.getModUrl() ==null ? "":mvnUrl.getModUrl());
				//插入首页数据
				insertModChildURL(driver);
				//得到其他按钮的链接
				List<WebElement> pageList = driver.findElements(By.cssSelector("ul.search-nav a"));
				if(pageList != null && pageList.size() > 1) {
					for(int i=0 ;i< pageList.size()-1; i++) {
						WebElement pageEle = pageList.get(i);
						//插入其他页数据
						if(pageEle != null ) {
							driver.get(pageEle.getAttribute("href"));
							//延时
							Thread.sleep(2000);
							//插入其他页数据
							insertModChildURL(driver);
						}
					}
				}	
				//level=1获取链接level=2的后，level更新为A
				mvnUrl.setLevel("A");
				mvnRepService.update(mvnUrl);
			}
		}
	}
	/*
	 * 插入获取的数据
	 */
	public void insertModChildURL(WebDriver driver) {
		List<WebElement> linkList = driver.findElements(By.cssSelector("div.im a"));
		if(linkList != null ) {
			for(WebElement webElement : linkList) {	
				MvnUrlModel mvnUrlModel = new MvnUrlModel();
				mvnUrlModel.setModUrl(webElement.getAttribute("href"));
				mvnUrlModel.setUrlText(webElement.getText());
				mvnUrlModel.setLevel("2");
				if(isNotRepeat(webElement.getAttribute("href"),"2")  && !"".equals(webElement.getText())) {
					mvnRepService.add(mvnUrlModel);
				}
			}
		}
	}
	
/*********************3.获取子资源的各个版本链接*********************************************************/	
	@RequestMapping("getModChildVersionUrl")
	public void getModChildVersionUrl() {
		WebDriver driver = null;
		try {
			// 加载驱动
			System.setProperty(llqDriver, driverpath);
			driver = new ChromeDriver();
			getModChildVersionURL(driver);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (driver != null)
			driver.close();
		}
	}
	
	public void getModChildVersionURL(WebDriver driver) {
		MvnUrlModel mvnUrlModel = new MvnUrlModel();
		mvnUrlModel.setLevel("2");
		List<MvnUrlModel> mvnList =  mvnRepService.getList(mvnUrlModel);
		if(mvnList != null ) {
			for(MvnUrlModel mvnUrl : mvnList) {
				try {
					Thread.sleep(2000);
					if(mvnUrl.getModUrl() != null) {
						driver.get(mvnUrl.getModUrl());
						//遍历顶部列表
						List<WebElement> navList = driver.findElements(By.cssSelector("ul.tabs li a"));
						System.out.println("页签数："+navList.size());
						//遍历版本中版本链接
						if(navList != null ) {
							for(WebElement nav :navList) {
								if(nav != null && nav.getAttribute("href") != null) {
									//切换到某个页签
									driver.get(nav.getAttribute("href"));
									//抓取table中的元素
									insertData("a.vbtn.release",  driver);
									//刷新页面
									driver.get(nav.getAttribute("href"));
									insertData("a.vbtn.release.candidate",  driver);
									//可能缺少元素					
								}
							}
						}
						//level=2获取链接level=3的后，level更新为B
						mvnUrl.setLevel("B");
						mvnRepService.update(mvnUrl);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
			}
		}
	}
	
	public void  insertData(String cssSelStr, WebDriver driver) {
		try {
			Thread.sleep(2000);
			List<WebElement> versionList = driver.findElements( By.cssSelector(cssSelStr)   );
			if(versionList != null ) {
				for(WebElement versionModel : versionList) {
					MvnUrlModel mvnUrlVersion = new MvnUrlModel();
					mvnUrlVersion.setModUrl(versionModel.getAttribute("href"));
					mvnUrlVersion.setUrlText(versionModel.getText());
					mvnUrlVersion.setLevel("3");
					if(isNotRepeat(versionModel.getAttribute("href"),"3")  && !"".equals(versionModel.getText())) {
						mvnRepService.add(mvnUrlVersion);
					}
				}
			}	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
/*********************4.获取子资源的各个版本的下载文本*********************************************************/		
	@RequestMapping("getModChildText")
	public void getModChildText() {
		WebDriver driver = null;
		try {
			// 加载驱动
			System.setProperty(llqDriver, driverpath);
			driver = new ChromeDriver();
			getModChildTextByInfo(driver);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (driver != null)
			driver.close();
		}
	}
	
	public void getModChildTextByInfo(WebDriver driver) throws InterruptedException {
		MvnUrlModel mvnUrlModel = new MvnUrlModel();
		mvnUrlModel.setLevel("3");
		List<MvnUrlModel> mvnList =  mvnRepService.getList(mvnUrlModel);
		if(mvnList != null ) {
			for(MvnUrlModel mvnUrl : mvnList) {
				Thread.sleep(2000);
				if(mvnUrl.getModUrl() != null) {
					driver.get(mvnUrl.getModUrl());
					//获取文本
					List<WebElement> versionList = driver.findElements( By.cssSelector(".snippet")   );
					for(WebElement versionModel : versionList) {
						MvnUrlModel mvnUrlVersion = new MvnUrlModel();
						mvnUrlVersion.setModUrl(mvnUrl.getModUrl());
						mvnUrlVersion.setReptext( versionModel.getText());//获取xml文本
						mvnUrlVersion.setLevel("4");
						if(isNotRepeat(mvnUrl.getModUrl(),"4") ) {
							mvnRepService.add(mvnUrlVersion);
						}
						//只抓取maven
						break;
					}
				}
				//level=3获取文本后，level更新为C
				mvnUrl.setLevel("C");
				mvnRepService.update(mvnUrl);
			}
		}
	}
/*********************5.拼接文本xml,并执行*********************************************************/	
	@RequestMapping("excutePomXml")
	public void excutePomXml() {
		try {
			excutePomXmlByInfo();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void excutePomXmlByInfo() throws InterruptedException {
		MvnUrlModel mvnUrlModel = new MvnUrlModel();
		mvnUrlModel.setLevel("4");
		List<MvnUrlModel> mvnList =  mvnRepService.getList(mvnUrlModel);
		//创建文件
		//String filePath = System.getProperty("user.dir")+File.separator+"pom.xml";
		String filePath = "F:\\cobothang\\deepdata"+File.separator+"pom.xml";
		//遍历下载
		if(mvnList != null ) {
			for(MvnUrlModel mvnUrl : mvnList) {
				//根据得到的链接拼接pom.xml	
				String xmlStr = "<?xml version=\"1.0\"?>\r\n" + 
						"<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\r\n" + 
						"    <modelVersion>4.0.0</modelVersion>\r\n" + 
						"    <groupId>temp</groupId>\r\n" + 
						"    <artifactId>temp</artifactId>\r\n" + 
						"    <version>1.0-SNAPSHOT</version> \r\n" + 
						"    <dependencies>\r\n" + 
						"   "+ mvnUrl.getReptext() +"\r\n"+//获取的版本pom.xml信息
						"    </dependencies>\r\n" +
						"</project>";
				//创建pom.xml文本	
				dataListIntoTxt(xmlStr,filePath);
				//用mvn执行xml文件
				List<String > strList = excuteCmd("mvn -f "+filePath+" dependency:copy-dependencies");
				System.out.println("mvn -f "+filePath+" dependency:copy-dependencies");
				//此条完后，level=4更新为DOd
				if(strList != null && strList.size()>0) {
					mvnUrl.setLevel("D");
					mvnRepService.update(mvnUrl);
				}
			}
		}
	}

	
	
	public void dataListIntoTxt(String str, String filePath) {
		BufferedWriter out = null;
		try {
			File writename = new File(filePath);
			if(!writename.exists()) {
				writename.createNewFile();
			}
			out = new BufferedWriter(new FileWriter(filePath));
			out.write(str); // \r\n即为换行 
			out.flush(); // 把缓存区内容压入文件  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 if(out != null)
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	
	

	public List<String> excuteCmd(String pythonCmd) {
		// TODO Auto-generated method stub
		List<String> versionList = new ArrayList<String>();
		BufferedReader in = null;
		try {
			//execute cmd
			Process process = Runtime.getRuntime().exec(pythonCmd);
			process.waitFor(); 
			//Waiting for Python command execution to complete
			//Thread.sleep(6000);
			//get result
			in = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				versionList.add(line == null ? "" : line.replace(" ", "") );
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//close flow
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return versionList;		
	}
	
/*********************数据重复*********************************************************/	
	public boolean isNotRepeat(String modUrl,String level) {
		MvnUrlModel mvnUrlModel = new MvnUrlModel();
		mvnUrlModel.setModUrl(modUrl);
		mvnUrlModel.setLevel(level);
		int num = mvnRepService.getNum(mvnUrlModel);
		if(num > 0 ) {
			return false;
		}
		return true;
	}
	
	
	public static String getDriverpath() {
		return driverpath;
	}
	public static void setDriverpath(String driverpath) {
		MvnController.driverpath = driverpath;
	}
	public static String getLlqDriver() {
		return llqDriver;
	}
	public static void setLlqDriver(String llqDriver) {
		MvnController.llqDriver = llqDriver;
	}	
}
