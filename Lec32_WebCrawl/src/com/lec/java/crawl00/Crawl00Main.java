package com.lec.java.crawl00;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawl00Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Jsoup");

		
		File f = new File("data/hello.html"); 
		Document doc = Jsoup.parse(f, "UTF-8"); // 파일에서 ->Document 변환 (DOM)
		Element element;
		Elements elements;
		//System.out.println(doc.outerHtml());
		element = doc.selectFirst("div");
		//System.out.println(element.outerHtml());
		 
		elements = doc.select("div");
		System.out.println(elements.size());
		//System.out.println(elements.outerHtml());
		
		// Elements 의 get (n) -> n번째 element 리턴
//		element = elements.get(3);
//		System.out.println(element.outerHtml());
//		
//		for(int i=0; i<elements.size(); i++) { // for문으로 뽑기 
//			element= elements.get(i);
//			System.out.println(element.outerHtml());
//			System.out.println();
//		}  
		
//		for(Element e : elements) {  // enhance for문으로 뽑기
//			System.out.println(e.outerHtml());
//			System.out.println();
//		}
		
		element = doc.selectFirst("div#addr");
//		System.out.println(element.outerHtml());
		elements = element.select("ul.favorite a");
		
		for(Element e : elements) {
			//System.out.println(e.outerHtml());
			System.out.println(e.text().trim());
			System.out.println(e.attr("href").trim());
		}
		
		elements = doc.select("img");
		for(Element e : elements) {
			System.out.println(e.attr("src").trim());
		}
		
		
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class
