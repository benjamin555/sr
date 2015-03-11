package com.kpoda.sr;

import java.nio.charset.Charset;

import org.junit.Test;

/**
* @author 陈嘉镇
* @version 创建时间：2015-3-11 下午12:19:32
* @email benjaminchen555@gmail.com
*/
public class FileManagerTest {
	
	private FileManager fileManager = new FileManager();
	
	@Test
	public void testSearch() {
		String regex = "href=\"[^\"]*.html\"";
		String dir = "G:\\gitwork\\damai\\src\\main\\webapp";
		String suppfix =".html";
		fileManager.setDir(dir,suppfix);
		fileManager.setCharset(Charset.forName("utf-8"));
		String replace ="<A href=\"#\"";
		fileManager.searchReplace(regex,replace);
	}

}
