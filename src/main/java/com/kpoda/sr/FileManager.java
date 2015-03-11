package com.kpoda.sr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author 陈嘉镇
* @version 创建时间：2015-3-11 下午12:20:50
* @email benjaminchen555@gmail.com
*/
public class FileManager {
	private String dir;
	private String suppfix;
	private Charset c;

	public void setDir(String dir, String suppfix) {
		this.dir = dir;
		this.suppfix = suppfix;
	}

	public void searchReplace(String regex, String replace) {

		List<File> fs = getFileList();
		System.out.println("文件夹下的文件个数:" + fs.size());
		for (File file : fs) {
			System.out.println("file:"+file.getName());
			String string = txt2String(file);
			
			
			Pattern p = Pattern.compile(regex);
	        Matcher found = p.matcher(string);
	        // Find all matches
	        while (found.find()) {
	          // Get the matching string
	          String digitNumList = found.group();
	          System.out.println(digitNumList);
	        }

			
//			string = string.replaceAll(regex, replace);
//			System.out.println(string);
			//write(string, file);
		}
	}

	private void write(String string, File file) {

		//先读取原有文件内容，然后进行写入操作
		String filein = string;
		RandomAccessFile mm = null;
		try {
			mm = new RandomAccessFile(file, "rw");
			mm.write(filein.getBytes(c));
		} catch (IOException e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		} finally {
			if (mm != null) {
				try {
					mm.close();
				} catch (IOException e2) {
					// TODO 自动生成 catch 块
					e2.printStackTrace();
				}
			}
		}

	}

	/**
	 * 读取txt文件的内容
	 * @param file 想要读取的文件对象
	 * @return 返回文件内容
	 */
	public String txt2String(File file) {
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行
				s = new String(s.getBytes(c),c);
				result = result + "\n" + s;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 过滤目录下的文件
	 * @param dirPath 想要获取文件的目录
	 * @return 返回文件list
	 */
	private List<File> getFileList() {
		File[] files = new File(dir).listFiles();
		List<File> fileList = new ArrayList<File>();
		for (File file : files) {
			if (filter(file.getName())) {
				fileList.add(file);
			}
		}
		return fileList;
	}

	private boolean filter(String fileName) {
		if (fileName.lastIndexOf(this.suppfix) > 0) {
			return true;
		}
		return false;
	}

	public void setCharset(Charset c) {
		this.c = c;
	}

}
