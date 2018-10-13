package word;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class TestFile {

	File or;
	File[] files;
	
	List<String> pathName = new ArrayList<String>();
	
	// ���ڱ����ļ�
	public void iteratorPath(String dir) {
		or = new File(dir);
		files = or.listFiles();
		if (files!= null){
			for (File file : files) {
				if (file.isFile()) {
					pathName.add(file.getName());
				}else if(file.isDirectory()){
					iteratorPath(file.getAbsolutePath());
				}
			}
		}
	}
			
	public static void main(String[] args) throws Exception {
		
		File writename = new File("F:\\cobothang\\deepdata\\src\\word\\file.txt");
		if(!writename.exists()) {
			writename.createNewFile();
		}
		BufferedWriter out = new BufferedWriter(new FileWriter(writename)); 
		TestFile and = new TestFile();
		and.iteratorPath("E:\\cobot");
		for (String list : and.pathName) {
			System.out.println(list);
			out.write(list+"\r\n"); // \r\n��Ϊ����  
		}
		out.flush(); // �ѻ���������ѹ���ļ�  
        out.close();
	}
}