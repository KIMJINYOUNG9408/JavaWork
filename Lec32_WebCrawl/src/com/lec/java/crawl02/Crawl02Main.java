package com.lec.java.crawl02;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawl02Main {

	public static void main(String[] args) throws IOException {
		System.out.println("네이트 인기 검색어");
		
			String url;
			Response response;
			Document doc;
			Element element;
			Elements elements;
			
			url = "https://www.nate.com";
			response = Jsoup.connect(url).execute();
		
		doc = response.parse();
	    
		element = doc.selectFirst(".search_keyword");
		elements = element.select("a");
		// elements = doc.select(".search_keyword dd a");
		for(Element e : elements) {
			System.out.println(e.text().trim());
			System.out.println(e.attr("href").trim());
		}
		
		System.out.println("\n프로그램 종료");

	}

}
