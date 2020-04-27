package com.lec.java.crawl11;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

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

public class Crawl11Main {

	public static final String REQ_SERVICE = "stationInfo";
	public static final String API_KEY = "54416e74676b616c373055434c526e"; // 나의 키값.

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
	public static void main(String[] args) {
		System.out.println("서울시 지하철 역사(station) 정보");

		int start_Index = 1;
		int end_Index = 5;
		String statnNm = "강남";
		StringBuffer sb; // response 받은 텍스트
		String url_address = "";
		
		try {
			statnNm = URLEncoder.encode(statnNm, "utf-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
					
		// URL이 제대로 넘어오는 지확인하는 작업.
		System.out.println("--- XML 파싱 --- ");
		url_address = buildUrlAddress("xml", start_Index, end_Index, statnNm);
        System.out.println(url_address);
		
        
        
		
		
//       System.out.println(url_address);
		sb = readFromUrl(url_address); 
		parseXML(sb.toString());

//		System.out.println(sb);		
		System.out.println("--- JSON 파싱 ---");
		url_address = buildUrlAddress("json", start_Index, end_Index, statnNm);
		sb = readFromUrl(url_address);
        parseJSON(sb.toString());
        
		System.out.println("\n프로그램 종료");
		
	} // end main()

	
	public static String buildUrlAddress(String reqType, int start_Index, int end_Index, String statnNm) {

		String url_address =  String.format("http://swopenapi.seoul.go.kr/api/subway/%s/%s/%s/%d/%d/%s", API_KEY,
				reqType,REQ_SERVICE, start_Index, end_Index, statnNm);
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
		
		DocumentBuilderFactory dbFactory;
		DocumentBuilder dBuilder;
		
		try {
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			
			InputStream in = new ByteArrayInputStream(xmlText.getBytes("utf-8"));
			
			Document dom = dBuilder.parse(in);
			
			Element docElement = dom.getDocumentElement();
			
			docElement.normalize();
			
			NodeList nList = docElement.getElementsByTagName("row");
			System.out.println(nList.getLength());
			
			for(int i=0; i<nList.getLength(); i++) {
				Node node = nList.item(i);
				
				if (node.getNodeType() != Node.ELEMENT_NODE)
					continue;

				Element rowElement = (Element) node; 
				
				String statnNm = rowElement.getElementsByTagName("statnNm").item(0).getChildNodes().item(0)
						.getNodeValue().trim();
				String subwayId = rowElement.getElementsByTagName("subwayId").item(0).getChildNodes().item(0)
						.getNodeValue().trim();
				String subwayNm = rowElement.getElementsByTagName("subwayNm").item(0).getChildNodes().item(0)
						.getNodeValue().trim();
				
				System.out.println(i+1 + " : " + statnNm + "역 " + subwayId + " " + subwayNm);
				
				
				
			}
			
		} catch (ParserConfigurationException e) {
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
		
		JSONObject jobj = new	 JSONObject(jsonText);
		JSONArray row = jobj.getJSONArray("stationList");
		
		System.out.println(row.length());
		for(int i=0; i<row.length(); i++) {
			JSONObject station = row.getJSONObject(i);
			String statnNm = station.getString("statnNm");
			int subwayId = station.getInt("subwayId");
			String subwayNm = station.getString("subwayNm");
			
			System.out.println(i+1 + " : " + statnNm + "역 " + subwayId + " " + subwayNm);
					
		}
	
		
		
		
	}
} // end class()
