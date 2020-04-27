package daily.dailysum;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 연습 : 자치구단위 서울 생활인구 일별 집계표
 * ■자치구단위 서울 생활인구 일별 집계표
 * 	http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-15379&srvType=S&serviceKind=1&currentPageNo=1
 * 	http://openapi.seoul.go.kr:8088/(인증키)/(요청파일타입)/SPOP_DAILYSUM_JACHI/(요청시작INDEX)/(요청종료INDEX)/(기준일ID)/(시군구코드)
 * 
 * 샘플url
 * 	XML 버젼
 * 	http://openapi.seoul.go.kr:8088/(인증키)/xml/SPOP_DAILYSUM_JACHI/1/5/
 * 		예] http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/xml/SPOP_DAILYSUM_JACHI/1/5/
 * 		예] http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/xml/SPOP_DAILYSUM_JACHI/1/5/20190101
 * 		예] http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/xml/SPOP_DAILYSUM_JACHI/1/5/20190101/11000
 * 
 * 	JSON 버젼
 * 	http://openapi.seoul.go.kr:8088/(인증키)/json/SPOP_DAILYSUM_JACHI/1/5/
 * 		예] http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/json/SPOP_DAILYSUM_JACHI/1/5/	
 * 		예] http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/json/SPOP_DAILYSUM_JACHI/1/5/20190101
 * 		예] http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/json/SPOP_DAILYSUM_JACHI/1/5/20190101/11000
 * 
 * ※ 한번에 1000개 까지의 데이터만 볼수 있슴
 * 
 */
// http://openapi.seoul.go.kr:8088/54416e74676b616c373055434c526e/xml/SPOP_DAILYSUM_JACHI/1/5/20190101
//http://openapi.seoul.go.kr:8088/54416e74676b616c373055434c526e/JSON/SPOP_DAILYSUM_JACHI/1/5/20190101
/*  입력예]
 *  날짜입력: 20190101
 *  시작Index : 1
 *  끝Index: 5
 *  
 *  [XML]
 *  날짜             구ID        총생활인구수           일최대이동인구수
 *  ----------------------------------------------------------------------
 *  20190101	11000    11121182.98210      4764635.18080 
 *  20190101    11110    274919.65940        177877.95000 
 *  20190101    11140    267096.65940        149729.45840 
 *  20190101    11170    315220.18480        149329.14120 
 *  20190101    11200    351993.77950        153738.94490
 *   
 *  [JSON]
 *  날짜             구ID        총생활인구수           일최대이동인구수
 *  ----------------------------------------------------------------------
 *  20190101	11000    11121182.98210      4764635.18080 
 *  20190101    11110    274919.65940        177877.95000 
 *  20190101    11140    267096.65940        149729.45840 
 *  20190101    11170    315220.18480        149329.14120 
 *  20190101    11200    351993.77950        153738.94490 
 * 
 */

// ★ 주목 ★
// XML 은 Jsoup 를 활용하여 파싱하세요
// JSON 은  jackson 을 활용하여 파싱하세요

public class DailySumMain {
	public static final String REQ_SERVICE = "SPOP_DAILYSUM_JACHI";
	public static final String API_KEY = "54416e74676b616c373055434c526e";

	public static void main(String[] args) throws IOException {
		System.out.println("자치구단위 서울 생활인구 일별 집계표");
       // XML
		String url = buildUrlAddress("xml", 1, 5, 20190101);
		

		Document doc = Jsoup.connect(url).parser(Parser.xmlParser()).get();
		Elements elements = doc.select("row");

		System.out.println("[XML]");
		System.out.println("날짜             구ID    총생활인구수           일최대이동인구수");
		System.out.println("---------------------------------------------------------");
		for (Element e : elements) {
			String STDR_DE_ID = e.selectFirst("STDR_DE_ID").text().trim();
			String SIGNGU_CODE_SE = e.selectFirst("SIGNGU_CODE_SE").text().trim();
			String TOT_LVPOP_CO = e.selectFirst("TOT_LVPOP_CO").text().trim();
			String DAIL_MXMM_MVMN_LVPOP_CO = e.selectFirst("DAIL_MXMM_MVMN_LVPOP_CO").text().trim();
			System.out
					.println(STDR_DE_ID + " " + SIGNGU_CODE_SE + "\t" + TOT_LVPOP_CO + "\t" + DAIL_MXMM_MVMN_LVPOP_CO);
		}
        System.out.println();
		// JSON
		ObjectMapper mapper = new ObjectMapper();
		URL url2 = new URL(buildUrlAddress("JSON", 1, 5, 20190101));
		Population pop = mapper.readValue(url2, Population.class);

		System.out.println("[JSON]");
		System.out.println("날짜             구ID    총생활인구수           일최대이동인구수");
		System.out.println("---------------------------------------------------------");
		for (PopRow e : pop.getSPOP_DAILYSUM_JACHI().getRow()) {
			System.out.println(e.getDate() + " " + e.getId() + "\t" + e.getTotLvPop() + "\t" + e.getDailyMovePop());

		}

	} // end main

	public static String buildUrlAddress(String reqType, int start_Index, int end_Index, int date) {
		String url_Address = String.format("http://openapi.seoul.go.kr:8088/%s/%s/%s/%d/%d/%d", API_KEY, reqType,
				REQ_SERVICE, start_Index, end_Index, date);

		return url_Address;

	}

} // end class

@JsonIgnoreProperties(ignoreUnknown = true)
class Population {
	public Daily SPOP_DAILYSUM_JACHI;

	public Daily getSPOP_DAILYSUM_JACHI() {
		return SPOP_DAILYSUM_JACHI;
	}

	public void setSPOP_DAILYSUM_JACHI(Daily sPOP_DAILYSUM_JACHI) {
		SPOP_DAILYSUM_JACHI = sPOP_DAILYSUM_JACHI;
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Daily {
	private List<PopRow> row;

	public List<PopRow> getRow() {
		return row;
	}

	public void setRow(List<PopRow> row) {
		this.row = row;
	}
}

@JsonIgnoreProperties(ignoreUnknown = true)
class PopRow {
	@JsonProperty("STDR_DE_ID")
	private String date;

	@JsonProperty("SIGNGU_CODE_SE")
	private String id;

	@JsonProperty("TOT_LVPOP_CO")
	private String totLvPop;

	@JsonProperty("DAIL_MXMM_MVMN_LVPOP_CO")
	private String dailyMovePop;

	public PopRow() {
		super();
	}

	public PopRow(String date, String id, String totLvPop, String dailyMovePop) {
		super();
		this.date = date;
		this.id = id;
		this.totLvPop = totLvPop;
		this.dailyMovePop = dailyMovePop;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTotLvPop() {
		return totLvPop;
	}

	public void setTotLvPop(String totLvPop) {
		this.totLvPop = totLvPop;
	}

	public String getDailyMovePop() {
		return dailyMovePop;
	}

	public void setDailyMovePop(String dailyMovePop) {
		this.dailyMovePop = dailyMovePop;
	}

}