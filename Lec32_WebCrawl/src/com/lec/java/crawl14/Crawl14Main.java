package com.lec.java.crawl14;
/*
 * XML, Json 파싱1
 * 
 * ■서울시 지하철호선별 역별 승하차 인원 정보
 * http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12914&srvType=A&
 * serviceKind=1&currentPageNo=1
 * 
 * 샘플url XML 버젼
 * http://openapi.seoul.go.kr:8088/54416e74676b616c373055434c526e/xml/
 * CardSubwayStatsNew/1/5/20181001 예)
 * http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/xml/
 * CardSubwayStatsNew/1/5/20181001
 * 
 * JSON 버젼 http://openapi.seoul.go.kr:8088/54416e74676b616c373055434c526e/json/
 * CardSubwayStatsNew/1/5/20181001 예)
 * http://openapi.seoul.go.kr:8088/4d46796d7366726f3833774a774955/json/
 * CardSubwayStatsNew/1/5/20181001
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/*
 * JSON 파싱 java.io.Reader 프로그램이 '문자 단위' 데이터를 읽어들이는(read) 통로 ├─
 * java.io.InputStreamReader // 스트림 기반의 reader └─ java.io.BufferedReader //
 * 문자(character) 기반 reader
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Crawl14Main {
	public static void main(String[] args) throws IOException {
		System.out.println("jakson-databind 연습 : URL -> json -> Java");
		
		 ObjectMapper mapper = new ObjectMapper();
		 URL url = new URL("http://openapi.seoul.go.kr:8088/54416e74676b616c373055434c526e/json/CardSubwayStatsNew/1/5/20181001");
		 
		 Subway subway = mapper.readValue(url, Subway.class);
//		 System.out.println(subway.getCardSubwayStateNew().getList_total_count());
		 for(SubRow e : subway.getCardSubwayStateNew().getRow()) {
				System.out.printf("%5s : %8s역 [승차:%6d 하차:%6d]\n", 
						e.getLineNum(), 
						e.getStationName(), 
						e.getRidePassenger(), 
						e.getAlightPassenger());
			}
		 
		System.out.println("\n프로그램 종료");
		System.out.println(url);
	} // end main()
} // end class

@JsonIgnoreProperties(ignoreUnknown = true) // 하나만 뽑으려고 하면. 지금상황에서는 list_total_count만 시험
class Subway {
	public Stats CardSubwayStatsNew;

	public Stats getCardSubwayStateNew() {
		return CardSubwayStatsNew;
	}

	public void setCardSubwayStateNew(Stats cardSubwayStateNew) {
		CardSubwayStatsNew = cardSubwayStateNew;
	}
	
}
@JsonIgnoreProperties(ignoreUnknown = true)
class Stats {
  
   
   private List<SubRow> row;


public List<SubRow> getRow() {
	return row;
}

public void setRow(List<SubRow> row) {
	this.row = row;
}



}

//JSON 필드명과 매핑되는 Java 객체ㅢ 변수명을 달리 하고 싶다면 
//@JsonProperty 사용

@JsonIgnoreProperties(ignoreUnknown = true) // 6개중 4개만 받기위해ㅓ 하는것
class SubRow {
	
	@JsonProperty("LINE_NUM") // JSON의 LINE_NUM --> lineNum 으로 매핑
	private String lineNum;
	
	@JsonProperty("SUB_STA_NM")
	private String stationName;
	
	@JsonProperty("RIDE_PASGR_NUM")
	private int ridePassenger;
	
	@JsonProperty("ALIGHT_PASGR_NUM")
	private int alightPassenger;

	public SubRow() {
		super();
	}
	
	public SubRow(String lineNum, String stationName, int ridePassenger, int alightPassenger) {
		super();
		this.lineNum = lineNum;
		this.stationName = stationName;
		this.ridePassenger = ridePassenger;
		this.alightPassenger = alightPassenger;
	}

	public String getLineNum() {
		return lineNum;
	}

	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public int getRidePassenger() {
		return ridePassenger;
	}

	public void setRidePassenger(int ridePassenger) {
		this.ridePassenger = ridePassenger;
	}

	public int getAlightPassenger() {
		return alightPassenger;
	}

	public void setAlightPassenger(int alightPassenger) {
		this.alightPassenger = alightPassenger;
	}


	
	
	
	
	
	
	
	
}

