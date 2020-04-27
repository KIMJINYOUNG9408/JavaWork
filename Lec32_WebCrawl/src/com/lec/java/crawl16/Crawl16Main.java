package com.lec.java.crawl16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//https://www.yogiyo.co.kr/api/v1/restaurants-geo/?items=20&lat=37.4936151279937&lng=127.032670651995&order=rank&page=0&search=
public class Crawl16Main {
	public static void main(String[] args) throws IOException, JsonProcessingException {
		System.out.println("요" + "기요 맛집정보 : header 추가");

		String url = "https://www.yogiyo.co.kr/api/v1/restaurants-geo/?items=20&lat=37.4936151279937&lng=127.032670651995&order=rank&page=0&search=";

		StringBuffer sb = readFromUrl(url);
//		System.out.println(sb);

		ObjectMapper mapper = new ObjectMapper();

		Restaurants restaurant = mapper.readValue(sb.toString(), Restaurants.class);

		for (Info e : restaurant.getRestaurants()) {
			System.out.println("음식점이름:" + e.getSlug() + "평점: " + e.getReview());
		}

		System.out.println("\n프로그램 종료");
	}

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

				/*
				 * 헤더 정보 추가 ↓↓↓↓↓↓↓↓↓↓↓↓↓ 대문자 상관 X
				 */
				conn.setRequestProperty("X-ApiKey", "iphoneap");
				conn.setRequestProperty("X-ApiSecret", "fe5183cc3dea12bd0ce299cf110a75a2");

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
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Restaurants {
	private List<Info> restaurants;

	public List<Info> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Info> restaurants) {
		this.restaurants = restaurants;
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Info {
	private String slug;

	@JsonProperty("review_avg")
	private String review;

	public Info() {
		super();
	}

	public Info(String slug, String review) {
		super();
		this.slug = slug;
		this.review = review;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

}
