package com.how2java.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.how2java.pojo.Oridepdata;
import com.how2java.service.OridepdataService;

import jxl.Sheet;
import jxl.Workbook;
import word.WordUtil;
@Controller
@RequestMapping("oridepdata")
public class OridepdataController {
	@Autowired
	OridepdataService oridepdataService;
	
	@RequestMapping("insertOridepdata")
	public ModelAndView insertOridepdata(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String filename = request.getParameter("filename");
		String str = "";
		try {
			//读取excel
			File file = new File(filename);
			if(!file.exists()) {
				file.createNewFile();
			}
			Workbook book = Workbook.getWorkbook(file);
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			int col = sheet.getColumns();//列数
			int row = sheet.getRows();//行数
			for(int i = 1; i < row; i++) {
            	//
				Oridepdata oridepdata = new Oridepdata();
				
				oridepdata.setBugdes( RemoveSpaces(sheet.getCell(0, i).getContents()) );
				oridepdata.setBugtype(  RemoveSpaces( sheet.getCell(1, i).getContents())  );//类型
				oridepdata.setLink(   RemoveSpaces(sheet.getCell(2, i).getContents())  );//原链接数据
				oridepdata.setLine(  modifyReplace(sheet.getCell(3, i).getContents(),"Line" ) );//行数
				oridepdata.setBugexp(   RemoveSpaces(sheet.getCell(4, i).getContents())   );//示例文字
				oridepdata.setOtherexp(   RemoveSpaces(sheet.getCell(5, i).getContents()).replace("Explanations from Others", "")   );//其他解释
				oridepdata.setBuginfo(   RemoveSpaces(sheet.getCell(6, i).getContents())  );//示例html
				
				oridepdata.setDlevel("3");
				oridepdata.setProtype("java");
				if(col > 7) {
					oridepdata.setDkey(  dealDkey(sheet.getCell(7, i).getContents())  );//关键字
					if(col > 8) {
						oridepdata.setFilename(  RemoveSpaces(sheet.getCell(8, i).getContents()).replace("icon-code", "")  );//处理文件名
						oridepdata.setProtype(oridepdata.getFilename().substring(oridepdata.getFilename().lastIndexOf(".")+1,  oridepdata.getFilename().length()) );//项目类型
					}
				}
				 oridepdata.setPronum(String.valueOf( getProNum(oridepdata.getBugdes())));//多少个项目进行改动
				 oridepdata.setBugerr(modifyExamples(getBugExp(sheet.getCell(6, i).getContents())));//错误示例
				 oridepdata.setBugcor(modifyExamples(getBugRep(sheet.getCell(6, i).getContents())));//正确示例
			
				 oridepdata.setMdvalue(MD5("类型：" + oridepdata.getBugtype() + ",示例：" + oridepdata.getBugexp()));// MD5之加密
				 oridepdata.setDeflink(  modifyURL(sheet.getCell(2, i).getContents())  );//缺陷链接
				
				 System.out.println("err："+oridepdata.getBugerr().length());
				 System.out.println("cor："+oridepdata.getBugcor().length());
				 
				if (oridepdata.vifyColLen(oridepdata) && !getMd5IsExist(oridepdata.getMdvalue()) && "java".equals(oridepdata.getProtype())) {
					oridepdataService.insertOridepdata(oridepdata);
				}else {
					str += String.valueOf(i)+",";
				}               
			}
			//整体更新等级字段为空的数据
			//oridepdataService.updateDlevel();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("listCategory");
		System.out.println("错误行数："+str);
		return mav;	
	}
	
	/*
	 * MD5值是否重复
	 */
	public boolean getMd5IsExist(String mdvalue) {
		//若值为空，不插入
		if(mdvalue ==null || "".equals(mdvalue)) {
			return true;
		}else {
			return oridepdataService.getMdListByMdvalue(mdvalue);
		}
	}
	
	/*
	 * 生成结果数据报告
	 */
	@RequestMapping("bornResultWord")
	public ModelAndView bornResultWord(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String level = request.getParameter("level");
		//String path = System.getProperty("user.dir");
		String wslj =  "F:/cobothang/deepdata/src/word/deep1.doc";
		try {
			//生成文书相关信息
				List<Object> ws = null;
				ws = makeWs(wslj,"", "", "doc", Integer.valueOf(level));
				byte[] wsnr=(byte[]) ws.get(1);
				//获得数据输出
				InputStream in = new ByteArrayInputStream(wsnr);
				
				
				File file = new File("D:/word_"+level+".doc");
				FileOutputStream output = new FileOutputStream(file);//改成创建一个文件，进行写入
				int n = 0;
				byte b[] = new byte[500];
				while ((n = in.read(b)) != -1) {
					output.write(b, 0, n);
				}
				output.flush();
				output.close();
				System.out.println("文件"+level+"已生成!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.setViewName("listCategory");
		return mav;
	}
		
	/**
	 * 
	 * @param 文书模板路径
	 * @param 文书模板ID
	 * @param 文书ID
	 * @param 文书格式
	 * @return 文书二进制数据
	 */
	@SuppressWarnings("unchecked")
	private  List<Object> makeWs(String mblj,String mbid,String wsid,String wsgs,int dlevel){
		Map<String,Object> result = new HashMap<String,Object>();		
		//list循环域
		Oridepdata oridepdata = new Oridepdata();
		oridepdata.setDlevel(String.valueOf(dlevel));
		List<Oridepdata> listOri =  oridepdataService.list(oridepdata);
		List<Map> list =  new ArrayList<Map>();
		if(listOri != null && listOri.size() >0 ) {
			for(int i=0;i < listOri.size() ; i++) {
				Map map = new HashMap();
				map.put("NUM", i+1);
				map.put("TITLE", listOri.get(i).getBugtype());
				map.put("LEVEL", dealLevel(listOri.get(i).getDlevel()) );
				map.put("BUGDES", listOri.get(i).getBugdes());
				map.put("BUGEXP", listOri.get(i).getBugerr());
				map.put("BUGREP", listOri.get(i).getBugcor());
				map.put("OTHEREXP", listOri.get(i).getOtherexp());
				map.put("HYL_BUGLINK", listOri.get(i).getDeflink());
				map.put("DKEY", listOri.get(i).getDkey());
				map.put("ID", listOri.get(i).getId());
				map.put("PRONUM", listOri.get(i).getPronum());
				list.add(map);				
			}
		}
		result.put("CONTENT",list) ;		
		//
		byte[] file_content = null;
		try {
			WordUtil wordUtil = new WordUtil();
			if("pdf".equalsIgnoreCase(wsgs)){
				file_content = wordUtil.makePdf(new FileInputStream( mblj ), result, true);
			}else if("doc".equalsIgnoreCase(wsgs)){
				file_content = wordUtil.makeDoc(new FileInputStream( mblj ), result, true);
			}
		} catch (FileNotFoundException e) {
			System.out.println("文书生成出错文书路径["+mblj +"]");
			e.printStackTrace();
		}
		List rst  = new ArrayList();
		rst.add("...");
		rst.add(file_content);
		return rst;
	}
	
	
	
	/*
	 * 去除双空格及换行符等.replace("Explanations from Others", "")
	 */
	public  String RemoveSpaces(String str) {
		if(str == null ) {
			return "";
		}
		while(str.indexOf("  ") >=0) {
			str = str.replace("\r", "").replace("\n", "").replace("  ", " ");
		}
		return str;
	}
	
	/*
	 * 得到代码段实例
	 */
	public static String getCodeStr(String str) {
		//System.out.println("示例：\n"+str);
		if(str == null ) {
			return "";
		}
		String[] strarr = str.split("<div class=\"diff-view\">");
		if(strarr!=null && strarr.length > 1) {
			String[] strarr2 = strarr[1].split("<div class=\"diff-nav\">");
			if(strarr2 !=null && strarr2.length > 1 && str != null ) {
				return strarr2[0];
			}
		}
		return "";
	}
	
	/*
	 * 得到错误的示例
	 */
	public  String getBugExp(String str) {
		if(str == null ) {
			return "";
		}
		str = getCodeStr(str);
		//System.out.println("源：\n"+str);
		Pattern r = Pattern.compile("(<pre class=\"add inSummary\">)(.+?)(</pre>)");//去除添加的代码行
	    Matcher m = r.matcher(str);
	    str = m.replaceAll("");//去除匹配
	    
	    Pattern r1 = Pattern.compile("(<pre class=\"add\">)(.+?)(</pre>)");//去除添加的代码行
	    Matcher m1 = r1.matcher(str);
	    str = m1.replaceAll("");//去除匹配
	    //System.out.println("去除匹配示例：\n"+str);
		return str;//去除标签
	}
		
	/*
	 * 得到正确的示例
	 */
	public static String getBugRep(String str) {
		if(str == null ) {
			return "";
		}
		str = getCodeStr(str);
		Pattern r = Pattern.compile("(<pre class=\"remove inSummary\">)(.+?)(</pre>)");//去除添加的代码行
        Matcher m = r.matcher(str);
        str = m.replaceAll("");//去除匹配
        
        Pattern r1 = Pattern.compile("(<pre class=\"remove\">)(.+?)(</pre>)");//去除添加的代码行
        Matcher m1 = r1.matcher(str);
        str = m1.replaceAll("");//去除匹配
		//System.out.println("正确示例：\n"+str);
        return str;//去除标签
	}
	
	
	/*
	 * 去除得到的错误正确示例中的标签，对代码进行换行处理
	 */
	public static String modifyExamples(String str) {
		if(str == null ) {
			return "";
		}
		str = str.replace("<pre class=\"nochange\">", "").replace("</pre>", "\n").replace("<pre class=\"add inSummary\">", "Ⓐ ")
				.replace("<pre class=\"nochange inSummary\">", "").replace("<pre class=\"remove inSummary\">", "Ⓡ ").replace("<pre class=\"space\">", "")
				.replace("<pre class=\"add\">", "Ⓐ ").replace("<pre class=\"remove\">", "Ⓡ ").replace("<strong>", "").replace("</strong>", "").replace("<code>", "").replace("</code>", "")
				.replace("</div>", "").replace("<div class=\"diff-lines\">", "").replace("<pre class=\"space inSummary\">", "").replace("�? Example 1/3 �?", "").replace("&gt;", ">")
				.replace("&lt;", "<").replace("&amp;", "&");
		while(str.indexOf("\n\n") >=0) {
			str = str.replace("\n\n", "\n").replace("\n   \n", "\n");
		}
		return str;
	}
	
	/*
	 * 处理链接地址
	 */
	public  String modifyURL(String str) {
		if(str == null ) {
			return "";
		}
		String[] strarr = str.split("/");
		if(strarr!= null && strarr.length >0) {
			str = "";
			for(int i = 0;i<strarr.length;i++) {
				if(i != strarr.length-2) {
					str +=  strarr[i]+"/";
				}
			}
		}
		return str;
	}
	public  String modifyReplace(String str,String replaceStr) {
		if(str == null ) {
			return "";
		}
		str = str.replace(replaceStr, "").replace(" ", "");
		return str;
	}
	/*
	 * 处理关键字
	 */
	public  String dealDkey(String str) {
		if(str == null ) {
			return "";
		}
		str = str.replace(" ", ";").replace("\n", ";");
		while(str.indexOf(";;") >=0) {
			str = str.replace(";;", ";");
		}
		return str;
	}
	
	
	public String dealLevel(String str) {
		if(str == null ) {
			return "";
		}else if("1".equals(str)){
			str = "critical";
		}else if("2".equals(str)){
			str = "warning";
		}else if("3".equals(str)){
			str = "info";
		}
		return str;
	}
	
	
	
	/*
	 * 生成等级缺陷页面数据
	 */
	@RequestMapping("selectBugByLevel")
	public ModelAndView selectBugByLevel(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String level = request.getParameter("level");
		Oridepdata oridepdata = new Oridepdata();
		oridepdata.setDlevel(level);
		List<Oridepdata> listOri =  oridepdataService.list(oridepdata);
		
		mav.addObject("listOri", listOri);
		mav.setViewName("listCategory");
		return mav;
	}
	
	/*
	 * 得到在多少个项目中进行了修改
	 */
	public int getProNum(String bugdes) {
		if(bugdes != null) {
		    String[] strArr = bugdes.split(" ");
	        if(strArr!= null && strArr.length > 6) {
	        	try {
	        		if(bugdes.startsWith("This is shown because")) {
	        			 return Integer.valueOf(strArr[4]); 
	        		}else if(bugdes.startsWith("This issue was fixed by")){
	        			 return Integer.valueOf(strArr[5]); 
	        		}
	        	}catch (Exception e){
	        		  return 1;  
	        	}
	        }
		}
        return 1;  
	}
	
	/*
	public static void main(String[] args ) {
		String dd = "<div class=\"subheading\"> Example Fixes </div> <div class=\"diffs selected\"><div class=\"diff-source\"><svg class=\"icon icon-inline icon-github\" viewBox=\"0 0 24 23.41\" xmlns=\"http://www.w3.org/2000/svg\" data-name=\"Layer 1\" id=\"Layer_1\"><defs><clipPath transform=\"translate(.01)\" id=\"clip-path\"><path class=\"cls-1\" d=\"M0 0h24v24H0z\"></path></clipPath></defs><title>icon-github</title><g class=\"cls-2\" id=\"github\"><g id=\"github-2\"><path transform=\"translate(.01)\" d=\"M12 0a12 12 0 0 0-3.8 23.39c.6.11.82-.26.82-.58v-2c-3.34.73-4-1.61-4-1.61a3.17 3.17 0 0 0-1.39-1.8c-1.09-.75.08-.73.08-.73a2.52 2.52 0 0 1 1.83 1.24 2.55 2.55 0 0 0 3.46 1 2.55 2.55 0 0 1 .76-1.6C7.14 17 4.34 16 4.34 11.38a4.63 4.63 0 0 1 1.23-3.23A4.26 4.26 0 0 1 5.67 5s1-.32 3.3 1.23a11.31 11.31 0 0 1 6 0C17.25 4.65 18.26 5 18.26 5a4.38 4.38 0 0 1 .12 3.18 4.65 4.65 0 0 1 1.23 3.22c0 4.61-2.81 5.63-5.48 5.92a2.87 2.87 0 0 1 .81 2.22v3.28c0 .38.21.69.82.57A12 12 0 0 0 12 0\" id=\"github-3\"></path></g></g></svg> <a class=\"external-link\" target=\"_blank\" href=\"https://github.com/jitsi/otr4j/commit/7247b348e68ff4a699ce58266d2201449d1ce362?diff=split#diff-a5c75516f83f1fa6a892399fc2c6cc11L444\">jitsi/otr4j</a></div> <div class=\"diff-view\"><div class=\"diff-lines\"><pre class=\"nochange\"><code>		'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};</code></pre><pre class=\"nochange\"></pre><pre class=\"nochange inSummary\"><code>public static String <strong>byteArrayToHexString</strong>(byte[] in) {</code></pre><pre class=\"nochange\"></pre><pre class=\"nochange\"><code>	if (in == null || in.length &lt;= 0)</code></pre><pre class=\"nochange\"><code>		return null;</code></pre><pre class=\"remove inSummary\"><code>	StringBuffer out = new <strong>StringBuffer</strong>(in.length * 2);</code></pre><pre class=\"add inSummary\"><code>	StringBuilder out = new <strong>StringBuilder</strong>(in.length * 2);</code></pre><pre class=\"nochange\"><code>	for (int i = 0; i &lt; in.length; i++) {</code></pre><pre class=\"nochange\"><code>		out.append(HEX_ENCODER[(in[i] &gt;&gt;&gt; 4) &amp; 0x0F]);</code></pre><pre class=\"nochange\"><code>		out.append(HEX_ENCODER[in[i] &amp; 0x0F]);</code></pre><pre class=\"nochange\"><code>	}</code></pre><pre class=\"nochange inSummary\"><code>	return out.<strong>toString</strong>();</code></pre><pre class=\"nochange\"><code>}</code></pre><pre class=\"nochange\"></pre></div></div> <div class=\"diff-nav\"><div class=\"arrow arrow-left\">▾</div> <span>Example 1/3</span> <div class=\"arrow arrow-right\">▾</div></div></div>";
		System.out.println(modifyExamples(getBugRep(dd)));
	}*/
	
	
	/*
	 * 更新所有示例的错误实例和正确示例
	 * 生成等级缺陷页面数据
	 */
	@RequestMapping("updateBuginfo")
	public ModelAndView updateBuginfo(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String level = request.getParameter("level");
		Oridepdata oridepdata = new Oridepdata();
		oridepdata.setDlevel(level);
		List<Oridepdata> listOri =  oridepdataService.list(oridepdata);
		for(Oridepdata oridepdata2 : listOri) {
			oridepdata2.setBugcor(modifyExamples(getBugRep(oridepdata2.getBuginfo())));
			oridepdata2.setBugerr(modifyExamples(getBugExp(oridepdata2.getBuginfo())));
			oridepdata2.setPronum(String.valueOf( getProNum(oridepdata2.getBugdes())));
			oridepdataService.update(oridepdata2);
		}
		mav.setViewName("listCategory");
		return mav;
	}
	
	/*
	 * 将类型与示例进行加密来对比
	 */
	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
