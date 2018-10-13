package com.how2java.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.LinkedList;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		
		/*try {
			   System.out.println("start");
			   Process pr = Runtime.getRuntime().exec("python  F:\\\\siva\\test.py -s F:\\\\siva\\93a3d8317bb8a9efe9f39ac38893fecfeba364e4.siva -f  F:\\\\siva -g test -r Y -i 0 ");
			   BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			   String line;
			   while ((line = in.readLine()) != null) {
				   System.out.println(line);
			   }
			   in.close();
			   System.out.println("end");
			  
			   traverseFolder1("F:\\\\siva\\test");
		} catch (Exception e) {
			   e.printStackTrace();
		}*/
		
		
	}
	
	public static void traverseFolder1(String path) {
        int fileNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    list.add(file2);
                } else {
                    System.out.println("文件绝对路径:" + file2.getAbsolutePath().replace(path, ""));
                    System.out.println("文件名:" + file2.getName());
                    System.out.println("文件大小:" + file2.length());
                    System.out.println("文件md5:" + file2);
                    if (file2.getName().contains(".")) {
                    	System.out.println("文件类型:" + file2.getName().substring(file2.getName().lastIndexOf(".")+1,file2.getName().length())); 
                    }else {
                    	System.out.println("文件类型:");
                    }
                    fileNum++;
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        list.add(file2);
                    } else {
                        System.out.println("文件绝对路径:" + file2.getAbsolutePath().replace(path, ""));
                        System.out.println("文件名:" + file2.getName());
                        fileNum++;
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件共有:" + fileNum);

    }
	
	
	
	
}
