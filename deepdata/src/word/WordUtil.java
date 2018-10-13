package word;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.FieldMergingArgs;
import com.aspose.words.IFieldMergingCallback;
import com.aspose.words.IMailMergeDataSource;
import com.aspose.words.ImageFieldMergingArgs;
import com.aspose.words.License;
import com.aspose.words.ListMapMergeDataSource;
import com.aspose.words.SaveFormat;

public class WordUtil{

	static{
		try {
			License license = new License();
			license.setLicense(new ByteArrayInputStream("<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>21991231</LicenseExpiry><SerialNumber>23dcc79f-44ec-4a23-be3a-03c1632404e9</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>".getBytes()));
		} catch (Exception e) {
			System.out.println("Aspose授权失败!");
			e.printStackTrace();
		}
	}

	public byte[] makeDoc(InputStream template, Map<String, Object> map, boolean is_new) {
		if(is_new){
			return this.makeFileNew(template, map, SaveFormat.DOC);
		}else{
			return this.makeFile(template, map, SaveFormat.DOC);
		}
	}

	public byte[] makePdf(InputStream template, Map<String, Object> map, boolean is_new) {
		if(is_new){
			return this.makeFileNew(template, map, SaveFormat.PDF);
		}else{
			return this.makeFile(template, map, SaveFormat.PDF);
		}
	}

	public byte[] makeJPG(InputStream template, Map<String, Object> map, boolean is_new) {
		if(is_new){
			return this.makeFileNew(template, map, SaveFormat.JPEG);
		}else{
			return this.makeFile(template, map, SaveFormat.JPEG);
		}
	}

	public byte[] makeHTML(InputStream template, Map<String, Object> map, boolean is_new) {
		if(is_new){
			return this.makeFileNew(template, map, SaveFormat.HTML);
		}else{
			return this.makeFile(template, map, SaveFormat.HTML);
		}
	}
	
	/***文件生成***/
	public byte[] makeFile(InputStream template, final Map<String, Object> map, int fileType) {
		//处理特定的业务信息
		for(String key: map.keySet()){
			//处理二维码信息
			if(key.toLowerCase().startsWith("tdc_")){
				byte[] tdc = TwodimeCodeUtil.encoderQRCodeAsByteArray((String)map.get(key),"jpeg",2);
				map.put(key, tdc);
			}
			//处理图片信息
			if(key.toLowerCase().startsWith("img_")){
				try {
					InputStream is = new FileInputStream((String)map.get(key));
					ByteArrayOutputStream bytestream = new ByteArrayOutputStream();   
					int ch;   
					while ((ch = is.read()) != -1) {   
						bytestream.write(ch);   
					}   
					byte imgdata[] = bytestream.toByteArray();
					bytestream.close();    
					map.put(key, imgdata);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		byte[] rst = null;
		try {
			Document doc = new Document(template);
			//linux下加上下面两行
			//String fontpath = ServletActionContext.getServletContext().getRealPath("doctemplate") + File.separator + "fonts";
			//FontSettings.setFontsFolder(fontpath, false);
			
			doc.getMailMerge().setFieldMergingCallback(
					new IFieldMergingCallback() {
						private String fieldName = null;
						private String fieldValue = null;
						@SuppressWarnings("unchecked")
						public void fieldMerging(FieldMergingArgs fma)	throws Exception {
							fieldName = fma.getDocumentFieldName(); 
							if (fieldName.toLowerCase().startsWith("spw_") || fieldName.toLowerCase().startsWith("htm_")) {
								if(fma.getTableName()!=null && fma.getTableName().length()>0){
									fieldValue = ((List<Map<String, String>>)map.get(fma.getTableName())).get(fma.getRecordIndex()).get(fieldName);
								}else{
									fieldValue = (String)map.get(fieldName);
								}
							}
							if (fieldName.toLowerCase().startsWith("spw_")) {
								DocumentBuilder builder = new DocumentBuilder(fma.getDocument());
								builder.moveToMergeField(fieldName);
								//修改字体
								builder.getFont().setName("Wingdings");
								//windows采用
								//处理特殊字符打对勾
								if("Y".equals(fieldValue)){
									builder.write(StringEscapeUtils.unescapeJava("\\uF0FE"));	
								}else{
									builder.write(StringEscapeUtils.unescapeJava("\\uF0A8"));
								}
							}else if(fieldName.toLowerCase().startsWith("htm_")){
								DocumentBuilder builder = new DocumentBuilder(fma.getDocument());
								builder.moveToMergeField(fieldName);
								if(fieldValue!=null && fieldValue.length()>0){
									//处理html中的图片的替换路径
									String reg = "(<img[^>]+src=[ ]*['\"])([^\"/]*/)+.*nameImage=";
									fieldValue = fieldValue.replaceAll(reg,"$1"+"D:/uploadImages/");
					                builder.insertHtml(fieldValue);
								}
							}
						}
						public void imageFieldMerging(ImageFieldMergingArgs ifma) throws Exception {
							
						}
					});
			if (map != null && map.size() > 0) {
				setData(doc,map);
				ByteArrayOutputStream boutput = new ByteArrayOutputStream();
				doc.save(boutput, fileType);
				rst = boutput.toByteArray();
				boutput.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}

	@SuppressWarnings("unchecked")
	private void setData(Document doc, Map<String, Object> map) throws Exception {
		IMailMergeDataSource iMailMergeDataSource = null;
		List<String> names = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		for (final Map.Entry<String, Object> obj : map.entrySet()) {
			if (!(obj.getValue() instanceof List<?>)) {
				names.add(obj.getKey());
				values.add(obj.getValue());
			} else {
				iMailMergeDataSource = new IMailMergeDataSource() {
					{
						this.dataList = (List<Map<String, Object>>) obj.getValue();
						this.index = -1;
					}
					private int index;
					private List<Map<String, Object>> dataList;

					public IMailMergeDataSource getChildDataSource(String arg0)
							throws Exception {
						return null;
					}

					public String getTableName() throws Exception {
						return obj.getKey();
					}

					public boolean getValue(String key, Object[] values)
							throws Exception {
						if (index < 0 || index >= dataList.size()) {
							return false;
						}
						if (values != null && values.length > 0) {
							values[0] = this.dataList.get(index).get(key);
							return true;
						} else {
							return false;
						}
					}

					public boolean moveNext() throws Exception {
						if (dataList.size() > index) {
							index++;
						}
						return dataList.size() > index;
					}
				};
				doc.getMailMerge().executeWithRegions(iMailMergeDataSource);
				//添加list里还有list的情况
				for(int i=0;i<((List<?>)obj.getValue()).size();i++) {
					setData(doc,(Map<String, Object>)((List<?>)obj.getValue()).get(i));
				}
			}
			doc.getMailMerge().execute((String[]) names.toArray(new String[names.size()]),values.toArray());
		}
	}
	
	
	/***新的文件生成***/
	public byte[] makeFileNew(InputStream template, final Map<String, Object> dataMap, int fileType) {
		byte[] rst = null;
		try {
			Document doc = new Document(template);
			//回调函数
			doc.getMailMerge().setFieldMergingCallback(new IFieldMergingCallback() {
				private String fieldName = null;
				private Object fieldValue = null;
				private DocumentBuilder builder = null;
				private String htmlReg = "(<img[^>]+src=[ ]*['\"])([^\"/]*/)+.*nameImage=";
				public void fieldMerging(FieldMergingArgs fma) throws Exception {
					fieldName = fma.getFieldName();
					fieldValue = fma.getFieldValue();
					//处理持殊的字符开头的 tdc_:二维码 ;img_:图片;htm_:html代码; spw_:复选框
					if (fieldName.toLowerCase().startsWith("tdc_")){
						//处理二维码图片
						builder = new DocumentBuilder(fma.getDocument());
						builder.moveToMergeField(fieldName);
						builder.insertImage(TwodimeCodeUtil.encoderQRCodeAsByteArray((String)fieldValue,"jpeg",2));
					}else if(fieldName.toLowerCase().startsWith("img_")){
						//图片的先不处理,暂时没有这种业务
					}else if(fieldName.toLowerCase().startsWith("htm_")){
						//处理数据格式是HTML代码
						builder = new DocumentBuilder(fma.getDocument());
						builder.moveToMergeField(fieldName);
						fieldValue = ((String)fieldValue).replaceAll(htmlReg,"$1"+"D:/uploadImages/");
		                builder.insertHtml((String) fieldValue);
					}else if(fieldName.toLowerCase().startsWith("spw_")){
						//处理数据复选框
						builder = new DocumentBuilder(fma.getDocument());
						builder.moveToMergeField(fieldName);
						builder.getFont().setName("Wingdings");
						if("Y".equals(fieldValue)){
							builder.write(StringEscapeUtils.unescapeJava("\\uF0FE"));	
						}else{
							builder.write(StringEscapeUtils.unescapeJava("\\uF0A8"));
						}
					}else if(fieldName.toLowerCase().startsWith("hyl_")){
						builder = new DocumentBuilder(fma.getDocument());
						builder.moveToMergeField(fieldName);	
						builder.getFont().setColor(Color.blue);
						builder.setUnderline(1);
						builder.insertHyperlink(String.valueOf(fieldValue), String.valueOf(fieldValue), false);
					}
				}
				
				public void imageFieldMerging(ImageFieldMergingArgs arg0) throws Exception {}
				
			});
			List<String> names = new ArrayList<String>();
	        List<Object> values = new ArrayList<Object>();
	        String tmp_key;
	        for (Iterator<String> it = dataMap.keySet().iterator(); it.hasNext(); ) {
	            tmp_key = it.next();
	            if (!(dataMap.get(tmp_key) instanceof List)) {
	                names.add(tmp_key);
	                values.add(dataMap.get(tmp_key));
	                it.remove();
	            }
	        }
	        doc.getMailMerge().execute((String[]) names.toArray(new String[names.size()]), values.toArray());
	        for (String key : dataMap.keySet()) {
	            doc.getMailMerge().executeWithRegions(new ListMapMergeDataSource(key, (List)dataMap.get(key)));
	        }
	        ByteArrayOutputStream boutput = new ByteArrayOutputStream();
			doc.save(boutput, fileType);
			rst = boutput.toByteArray();
			boutput.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}
	
	
}
