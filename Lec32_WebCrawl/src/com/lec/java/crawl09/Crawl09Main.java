package com.lec.java.crawl09;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawl09Main {
// 연습
// 네이버 '검색어'
// 블로그
// -posttitle
// -post url
// blog title

// 페이징규현
	public static void main(String[] args) throws IOException {
		System.out.println("네이버 검색, 페이징");

		crawNaverBlog("파이썬", 1);

		System.out.println("\n프로그램 종료");
	}// end main

	public static void crawNaverBlog(String search, int page) throws IOException {
		if (search == null || search.trim().length() == 0 || page < 1)
			return; // 매개변수 검증

		String url;
		Document doc;
		Response response;
		Elements elements;
		Elements rowElements;
		
		url = String.format("https://search.naver.com/search.naver?date_from=&date_option=0&date_to=&dup_remove=1&nso=&post_blogurl=&post_blogurl_without=&query=%s&sm=tab_pge&srchby=all&st=sim&where=post&start=%d"
				, URLEncoder.encode(search,"utf-8"),(page-1) *10 +1);
		
		//		System.out.println(url);
		
		doc = Jsoup.connect(url).execute().parse();
		
		elements = doc.select("._prs_blg > ul > li.sh_blog_top > dl");
		
		for (Element e : elements ) {
			String postTitle = e.selectFirst("a").text().trim();
			String blogTitle = e.selectFirst(".txt_block a.txt84").text().trim();
			String postUrl = e.selectFirst(".url").attr("href").trim();
			
			System.out.print(postTitle + "\t  + ");
			System.out.print(blogTitle + "\t  + ");
			System.out.println(postUrl);
		}
		
		System.out.println();
	}

}// end class
