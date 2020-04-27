import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Movie {
   public static void main(String[] args) throws IOException {
	
	   String url;
	   Response response;
	   Document doc;
	   Element element;
	   Elements elements;
	   
	   url = "https://movie.naver.com/movie/sdb/rank/rmovie.nhn";
	   response = Jsoup.connect(url).execute();
	   
	   doc =response.parse();
	   
	   element = doc.selectFirst(".box_type_1");
	   elements = element.select("ul.r_ranking li > a");
	   
	  
	   for(Element e : elements) {
		   e.selectFirst("span.blind").remove(); // 1위 2위 ... 텍스트 <span> 없애려면 해당 element삭제 (remove();)
		   System.out.println(e.text());
	   }
}
}
