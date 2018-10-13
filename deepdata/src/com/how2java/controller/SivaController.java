package com.how2java.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.how2java.pojo.SivaModel;
import com.how2java.service.SivaService;

@Controller
@RequestMapping("siva")
public class SivaController {
	@Autowired
	SivaService sivaService;// ����ori_github������
	
	@RequestMapping("siva")
	public ModelAndView siva() {
		ModelAndView mav = new ModelAndView();
		try {
			List<SivaModel> sivaList = sivaService.get(); 
			if(sivaList != null && sivaList.size() > 0) {
				for(int i=0 ;i<sivaList.size(); i++ ) {
					SivaModel sivaModel = sivaList.get(i);
					String siva = sivaModel.getSiva();
					if (siva != null && !"".equals(siva)) {
						String[] sivaArr = siva.split(",");
						for(int j=0; j< sivaArr.length; j++) {
							//����siva�ļ���������sivafile���У������Ϊ0����ʾû���������
							SivaModel sivafile = new SivaModel();
							sivafile.setSivafile(sivaArr[j]);
							String num = sivaService.getSiva(sivafile).getNum();
							if("0".equals(num)) {//����0,�����û���������
								sivaService.update(sivaModel);								
								continue;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		mav.setViewName("listCategory");
		return mav;
	}

	
	@RequestMapping("sivaList")
	public ModelAndView sivaList() {
		ModelAndView mav = new ModelAndView();
		try {
			File writename = new File("F:\\cobothang\\deepdata\\src\\word\\file.txt");
			if(!writename.exists()) {
				writename.createNewFile();
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			
			List<SivaModel> sivaList = sivaService.getList(); 
			if(sivaList != null && sivaList.size() > 0) {
				for(SivaModel sivaModel : sivaList) {
					if(sivaModel.getUrl() != null && !"".equals(sivaModel.getUrl())) {
						out.write(sivaModel.getUrl()+"\r\n"); // \r\n��Ϊ���� 
					}					
				}				
			}
			out.flush(); // �ѻ���������ѹ���ļ�  
	        out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		mav.setViewName("listCategory");
		return mav;		
	}
	
	
	
}
