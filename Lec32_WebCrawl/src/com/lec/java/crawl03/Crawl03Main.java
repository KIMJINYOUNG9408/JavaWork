package com.lec.java.crawl03;

import org.jsoup.Jsoup;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawl03Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Daum 실시간 검색어");
        
		// http://www.daum.net
		// 15개
		String url;
		Response response;
		Document doc;
		Element element;
		Elements elements;
		
		url = "http://www.daum.net";
		response = Jsoup.connect(url).execute();
		
		doc = response.parse();
		
		element = doc.selectFirst(".slide_favorsch");
		elements = element.select("a");
		// doc.select("div.slide_favorsch ul.list_favorsch li a");
		for(Element e : elements) {
			System.out.println(e.text().trim());
			System.out.println(e.attr("href").trim());
		}
		
		System.out.println(elements.size());
	}

}
