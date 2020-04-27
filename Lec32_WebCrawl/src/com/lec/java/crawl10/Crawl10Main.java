package com.lec.java.crawl10;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Crawl10Main {

	public static final String REQ_SERVICE = "CardSubwayStatsNew";
	public static final String API_KEY = "54416e74676b616c373055434c526e"; // 나의 키값.

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

	/*
	 * JSON 파싱 java.io.Reader 프로그램이 '문자 단위' 데이터를 읽어들이는(read) 통로 ├─
	 * java.io.InputStreamReader // 스트림 기반의 reader └─ java.io.BufferedReader //
	 * 문자(character) 기반 reader
	 */

	public static void main(String[] args) {
		System.out.println("서울시 지하철호선별 역별 승하차 인원 정보");

		int startIndex; // 요청 시작위치 정수 입력( 페이징 시작번호입니다 : 데이터 행 시작번호 )
		int endIndex; // 요청 종료위치 정수 입력 ( 페이징 끝번호 입니다: 데이터 행 끝번호)
		String date; // 날짜 yyyymmdd 형식

		StringBuffer sb; // response 받은 텍스트
		String url_address;
		startIndex = 1;
		endIndex = 5;
		date = "20200329";

		// URL이 제대로 넘어오는 지확인하는 작업.
		System.out.println("--- XML 파싱 --- ");
		url_address = buildUrlAddress("xml", startIndex, endIndex, date);

//       System.out.println(url_address);
		sb = readFromUrl(url_address);
		parseXML(sb.toString());

//		System.out.println(sb);		
		System.out.println("--- JSON 파싱 ---");
		url_address = buildUrlAddress("JSON", startIndex, endIndex, date);
		sb = readFromUrl(url_address);
        parseJSON(sb.toString());
       
		System.out.println("\n프로그램 종료");
	} // end main()

	public static String buildUrlAddress(String reqType, int startIndex, int endIndex, String date) {

		String url_address = String.format("http://openapi.seoul.go.kr:8088/%s/%s/CardSubwayStatsNew/%d/%d/%s", API_KEY,
				reqType, startIndex, endIndex, date);

		return url_address;
	} // end buildurlAddress

	/**
	 * 
	 * @param urlAddress : 주어진 url 주소
	 * @return 서버로부터 받은 텍스트 데이터(HTML) 리턴
	 */
	public static StringBuffer readFromUrl(String urlAddress) {

		StringBuffer sb = new StringBuffer(); // response 받은 데이터 담을 객체
		URL url = null; // java.net.URL
		HttpURLConnection conn = null; // java.net.HttpURLConnection

		InputStream in = null;
		InputStreamReader reader = null; // byte 스트림 --> 문자기반 Reader
		BufferedReader br = null;

		char[] buf = new char[512]; // 문자용 버퍼

		// BufferedReader <-InputStreamReader <-InputStream < - HttpURLConnection

		try {
			url = new URL(urlAddress); // url 객체 생성
			conn = (HttpURLConnection) url.openConnection(); // Connection 객체 생성

			if (conn != null) { // url이 잘못되었을때는 진행하지 않기 위해서
				conn.setConnectTimeout(2000); // 2초이네에 연결이 수립안되면 java.net.SocketTimeoutException 발생

				conn.setRequestMethod("GET"); // GET 방식 request // "GET", "POST"......
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
				conn.setUseCaches(false); // 캐시 사용 안함

				System.out.println("request 시작 : " + urlAddress);
				conn.connect(); // request 발생 --> 이후 response 받을때까지 delay
				System.out.println("response 완료");

				int responseCode = conn.getResponseCode();
				System.out.println("response code : " + responseCode); // 코드 값 200 나와야 정상
				// 참조 : https://developer.mozilla.org/ko/docs/Web/HTTP/Status
				if (responseCode == HttpURLConnection.HTTP_OK /* == 200 */) {
					in = conn.getInputStream(); // InputStream <- HttpURLConnection
					reader = new InputStreamReader(in, "utf-8"); // reader 객체 < - inputstream
					br = new BufferedReader(reader); // BufferedReader <- InputStreamReader

					int cnt; // 읽은 글자의 개수 담을 변수
					while ((cnt = br.read(buf)) != -1) { // 즉 없으면
						sb.append(buf, 0, cnt); // response 받은 텍스트를 string buffer에 추가
					}
				} else {
					System.out.println("response 실패");
					return null;
				}
			} else {
				System.out.println("conn null");
				return null;

			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
			if (conn != null)
				conn.disconnect(); // 작업끝나고 Connection 해제.
		}

		return sb;
	} // end readFromUrl

	public static void parseXML(String xmlText) {
		// XML 파싱
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder;

		try {
			// DOM parser 객체 생성
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();

			// String -> InputStream 변환
			InputStream in = new ByteArrayInputStream(xmlText.getBytes("utf-8"));

			// InputStream - > Dom 객체 생성
			Document dom = dBuilder.parse(in); // import org.w3c.dom.Document; 가져와야함 주의

			// Dom 최상위 document element 추출
			Element docElement = dom.getDocumentElement(); // import org.w3c.dom.Element

			// 파싱하기전 normalize()
			docElement.normalize(); // 흩어진 text node 들을 정렬하는 등의 절차 //XML 파싱하기 전에 꼭 normalize()부터 하자

			// DOM 내의 데이터 파싱
			NodeList nList = docElement.getElementsByTagName("row"); // <row> element 들로 구성된 NodeList 리턴
			System.out.println("<row> 개수 : " + nList.getLength());

			System.out.println();
			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);

				// element node인 경우만 파싱 진행
				if (node.getNodeType() != Node.ELEMENT_NODE)
					continue;

				Element rowElement = (Element) node; // 부모 = node Element = node를 상속받음

				String LINE_NUM = rowElement.getElementsByTagName("LINE_NUM").item(0).getChildNodes().item(0)
						.getNodeValue().trim();
				String SUB_STA_NM = rowElement.getElementsByTagName("SUB_STA_NM").item(0).getChildNodes().item(0)
						.getNodeValue().trim();
				String RIDE_PASGR_NUM = rowElement.getElementsByTagName("RIDE_PASGR_NUM").item(0).getChildNodes()
						.item(0).getNodeValue().trim();
				String ALIGHT_PASGR_NUM = rowElement.getElementsByTagName("ALIGHT_PASGR_NUM").item(0).getChildNodes()
						.item(0).getNodeValue().trim();

				System.out.printf("%s : %8s[승차:%6s 하차:%6s]\n", LINE_NUM, SUB_STA_NM, RIDE_PASGR_NUM, ALIGHT_PASGR_NUM);

//				System.out.println(rowElement.getTextContent());
//				System.out.println("node type : " + node.getNodeType());
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // end parseXML

	public static void parseJSON(String jsonText) {
		// org.json 라이브러리 다운로드
		// https://mvnrepository.com/artifact/org.json/json
		// 최신버젼 클릭후, Files 항목 클릭해서 다운로드
		
		JSONObject jobj = new JSONObject(jsonText); // JSON 파싱 : JSONObject <- String
		JSONArray row = jobj.getJSONObject(REQ_SERVICE).getJSONArray("row");
        System.out.println(jobj);
		System.out.println("row 의 개수 : " + row.length());
		
		for(int i=0; i<row.length(); i++) {
			JSONObject station = row.getJSONObject(i);			
		   String LINE_NUM =  station.getString("LINE_NUM");
		   String SUB_STA_NM =  station.getString("SUB_STA_NM");
		   int RIDE_PASGR_NUM =  station.getInt("RIDE_PASGR_NUM");
		   int ALIGHT_PASGR_NUM =  station.getInt("ALIGHT_PASGR_NUM");
		    
		   System.out.printf("%s : %8s[승차:%6s 하차:%6s]\n", LINE_NUM, SUB_STA_NM, RIDE_PASGR_NUM, ALIGHT_PASGR_NUM);
		}
		
		
	}
} // end class()
