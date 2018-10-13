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
		// ��ȡ�����ļ�
		ResourceBundle resource = ResourceBundle.getBundle("deepcode");
		setLlqDriver(resource.getString("llqDriver")); // �������������
		setDriverpath(resource.getString("driverpath")); // ���������·��
		
	}
	
/*********************1.��ȡ��˵�ģ������*********************************************************/	
	@RequestMapping("getModUrl")
	public void getModUrl() {
		WebDriver driver = null;
		try {
			// ��������
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
				//���ģ��·��
				if(isNotRepeat(webElement.getAttribute("href"),"1")) {
					mvnRepService.add(mvnUrlModel);
				}
			}
		}
	}
/*********************2.��ȡģ��������Դ����*********************************************************/		
	@RequestMapping("getModChildUrl")
	public void getModChildUrl() {
		WebDriver driver = null;
		try {
			// ��������
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
				//������ҳ����
				insertModChildURL(driver);
				//�õ�������ť������
				List<WebElement> pageList = driver.findElements(By.cssSelector("ul.search-nav a"));
				if(pageList != null && pageList.size() > 1) {
					for(int i=0 ;i< pageList.size()-1; i++) {
						WebElement pageEle = pageList.get(i);
						//��������ҳ����
						if(pageEle != null ) {
							driver.get(pageEle.getAttribute("href"));
							//��ʱ
							Thread.sleep(2000);
							//��������ҳ����
							insertModChildURL(driver);
						}
					}
				}	
				//level=1��ȡ����level=2�ĺ�level����ΪA
				mvnUrl.setLevel("A");
				mvnRepService.update(mvnUrl);
			}
		}
	}
	/*
	 * �����ȡ������
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
	
/*********************3.��ȡ����Դ�ĸ����汾����*********************************************************/	
	@RequestMapping("getModChildVersionUrl")
	public void getModChildVersionUrl() {
		WebDriver driver = null;
		try {
			// ��������
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
						//���������б�
						List<WebElement> navList = driver.findElements(By.cssSelector("ul.tabs li a"));
						System.out.println("ҳǩ����"+navList.size());
						//�����汾�а汾����
						if(navList != null ) {
							for(WebElement nav :navList) {
								if(nav != null && nav.getAttribute("href") != null) {
									//�л���ĳ��ҳǩ
									driver.get(nav.getAttribute("href"));
									//ץȡtable�е�Ԫ��
									insertData("a.vbtn.release",  driver);
									//ˢ��ҳ��
									driver.get(nav.getAttribute("href"));
									insertData("a.vbtn.release.candidate",  driver);
									//����ȱ��Ԫ��					
								}
							}
						}
						//level=2��ȡ����level=3�ĺ�level����ΪB
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
	
/*********************4.��ȡ����Դ�ĸ����汾�������ı�*********************************************************/		
	@RequestMapping("getModChildText")
	public void getModChildText() {
		WebDriver driver = null;
		try {
			// ��������
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
					//��ȡ�ı�
					List<WebElement> versionList = driver.findElements( By.cssSelector(".snippet")   );
					for(WebElement versionModel : versionList) {
						MvnUrlModel mvnUrlVersion = new MvnUrlModel();
						mvnUrlVersion.setModUrl(mvnUrl.getModUrl());
						mvnUrlVersion.setReptext( versionModel.getText());//��ȡxml�ı�
						mvnUrlVersion.setLevel("4");
						if(isNotRepeat(mvnUrl.getModUrl(),"4") ) {
							mvnRepService.add(mvnUrlVersion);
						}
						//ֻץȡmaven
						break;
					}
				}
				//level=3��ȡ�ı���level����ΪC
				mvnUrl.setLevel("C");
				mvnRepService.update(mvnUrl);
			}
		}
	}
/*********************5.ƴ���ı�xml,��ִ��*********************************************************/	
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
		//�����ļ�
		//String filePath = System.getProperty("user.dir")+File.separator+"pom.xml";
		String filePath = "F:\\cobothang\\deepdata"+File.separator+"pom.xml";
		//��������
		if(mvnList != null ) {
			for(MvnUrlModel mvnUrl : mvnList) {
				//���ݵõ�������ƴ��pom.xml	
				String xmlStr = "<?xml version=\"1.0\"?>\r\n" + 
						"<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\r\n" + 
						"    <modelVersion>4.0.0</modelVersion>\r\n" + 
						"    <groupId>temp</groupId>\r\n" + 
						"    <artifactId>temp</artifactId>\r\n" + 
						"    <version>1.0-SNAPSHOT</version> \r\n" + 
						"    <dependencies>\r\n" + 
						"   "+ mvnUrl.getReptext() +"\r\n"+//��ȡ�İ汾pom.xml��Ϣ
						"    </dependencies>\r\n" +
						"</project>";
				//����pom.xml�ı�	
				dataListIntoTxt(xmlStr,filePath);
				//��mvnִ��xml�ļ�
				List<String > strList = excuteCmd("mvn -f "+filePath+" dependency:copy-dependencies");
				System.out.println("mvn -f "+filePath+" dependency:copy-dependencies");
				//�������level=4����ΪDOd
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
			out.write(str); // \r\n��Ϊ���� 
			out.flush(); // �ѻ���������ѹ���ļ�  
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
	
/*********************�����ظ�*********************************************************/	
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
