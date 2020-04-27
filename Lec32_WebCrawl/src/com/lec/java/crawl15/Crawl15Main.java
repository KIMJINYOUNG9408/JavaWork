package com.lec.java.crawl15;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

/* XML, JSON 파싱 연습
 * 
 * ■서울시 지하철 역사 정보
http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-12753&srvType=A&serviceKind=1&currentPageNo=1

샘플url

XML 버젼
http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/xml/stationInfo/1/5/서울

JSON 버젼
http://swopenAPI.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/json/stationInfo/1/5/서울

 */

// 의존관계 필요 : jackson  ( 여러개 ) 
public class Crawl15Main {

	public static void main(String[] args) throws IOException {
		System.out.println("jackson-databind 연습2");

		ObjectMapper mapper = new ObjectMapper();
		URL url = new URL(
				"http://swopenapi.seoul.go.kr/api/subway/4d46796d7366726f3833774a774955/json/stationInfo/1/5/%EC%84%9C%EC%9A%B8");

		Subway subway = mapper.readValue(url, Subway.class);
		int i = 1;
		for (SubList e : subway.getStationList()) {

			System.out.println(i + " : " + e.getStatnNm() + "역 " + e.getSubwayId() + " " + e.getSubwayNm());
			i++;
		}

		System.out.println("\n프로그램 종료");
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Subway {
	private List<SubList> stationList;

	public List<SubList> getStationList() {
		return stationList;
	}

	public void setStationList(List<SubList> stationList) {
		this.stationList = stationList;
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class SubList {
	private String statnNm;
	private int subwayId;
	private String subwayNm;

	public SubList() {
		super();
	}

	public SubList(String statnNm, int subwayId, String subwayNm) {
		super();
		this.statnNm = statnNm;
		this.subwayId = subwayId;
		this.subwayNm = subwayNm;
	}

	public String getStatnNm() {
		return statnNm;
	}

	public void setStatnNm(String statnNm) {
		this.statnNm = statnNm;
	}

	public int getSubwayId() {
		return subwayId;
	}

	public void setSubwayId(int subwayId) {
		this.subwayId = subwayId;
	}

	public String getSubwayNm() {
		return subwayNm;
	}

	public void setSubwayNm(String subwayNm) {
		this.subwayNm = subwayNm;
	}

}
