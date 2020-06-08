<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>simpleMap</title>
		<script	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
		<script src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=l7xx381cd67d8c21411c8f6170b3183da38b"></script>
<script type="text/javascript">

			var map, marker1;
			function initTmap() {					
		
				$("#btn_select").click(function() {
					// 2. API 사용요청
					var fullAddr = $("#fullAddr").val();
					$.ajax({
						method : "GET",
						url : "https://apis.openapi.sk.com/tmap/geo/fullAddrGeo?version=1&format=json&callback=result",
						async : false,
						data : {
							"appKey" : "l7xx381cd67d8c21411c8f6170b3183da38b",
							"coordType" : "WGS84GEO",
							"fullAddr" : fullAddr
						},
						success : function(response) {

							var resultInfo = response.coordinateInfo; // .coordinate[0];
							console.log(resultInfo);
							
							// 기존 마커 삭제
							
							
							// 3.마커 찍기
							// 검색 결과 정보가 없을 때 처리
							if (resultInfo.coordinate.length == 0) {
								$("#result").text(
								"요청 데이터가 올바르지 않습니다.");
							} else {
								var lon, lat;
								var resultCoordinate = resultInfo.coordinate[0];
								if (resultCoordinate.lon.length > 0) {
									// 구주소
									lon = resultCoordinate.lon;
									lat = resultCoordinate.lat;
								} else { 
									// 신주소
									lon = resultCoordinate.newLon;
									lat = resultCoordinate.newLat
								}
							
								var lonEntr, latEntr;
								
								if (resultCoordinate.lonEntr == undefined && resultCoordinate.newLonEntr == undefined) {
									lonEntr = 0;
									latEntr = 0;
								} else {
									if (resultCoordinate.lonEntr.length > 0) {
										lonEntr = resultCoordinate.lonEntr;
										latEntr = resultCoordinate.latEntr;
									} else {
										lonEntr = resultCoordinate.newLonEntr;
										latEntr = resultCoordinate.newLatEntr;
									}
								}														
								
								
								
								// 검색 결과 주소를 담을 변수
								var address = '', newAddress = '';
								var city, gu_gun, eup_myun, legalDong, adminDong, ri, bunji;
								var buildingName, buildingDong, newRoadName, newBuildingIndex, newBuildingName, newBuildingDong;
								
								// 새주소일 때 검색 결과 표출		// 새주소인 경우 matchFlag가 아닌	// newMatchFlag가 응답값으로	// 온다
								if (resultCoordinate.newMatchFlag.length > 0) {
									// 새(도로명) 주소 좌표 매칭	// 구분 코드
									newMatchFlag = resultCoordinate.newMatchFlag;
									
									// 시/도 명칭
									if (resultCoordinate.city_do.length > 0) {
										city = resultCoordinate.city_do;
										newAddress += city + "\n";
									}
									
									// 군/구 명칭
									if (resultCoordinate.gu_gun.length > 0) {
										gu_gun = resultCoordinate.gu_gun;
										newAddress += gu_gun + "\n";
									}
									
									// 읍면동 명칭
									if (resultCoordinate.eup_myun.length > 0) {
										eup_myun = resultCoordinate.eup_myun;
										newAddress += eup_myun + "\n";
									} else {
										// 출력 좌표에 해당하는
										// 법정동 명칭
										if (resultCoordinate.legalDong.length > 0) {
											legalDong = resultCoordinate.legalDong;
											newAddress += legalDong + "\n";
										}
										// 출력 좌표에 해당하는
										// 행정동 명칭
										if (resultCoordinate.adminDong.length > 0) {
											adminDong = resultCoordinate.adminDong;
											newAddress += adminDong + "\n";
										}
									}
									// 출력 좌표에 해당하는 리 명칭
									if (resultCoordinate.ri.length > 0) {
										ri = resultCoordinate.ri;
										newAddress += ri + "\n";
									}
									// 출력 좌표에 해당하는 지번 명칭
									if (resultCoordinate.bunji.length > 0) {
										bunji = resultCoordinate.bunji;
										newAddress += bunji + "\n";
									}
									// 새(도로명)주소 매칭을 한
									// 경우, 길 이름을 반환
									if (resultCoordinate.newRoadName.length > 0) {
										newRoadName = resultCoordinate.newRoadName;
										newAddress += newRoadName + "\n";
									}
									// 새(도로명)주소 매칭을 한
									// 경우, 건물 번호를 반환
									if (resultCoordinate.newBuildingIndex.length > 0) {
										newBuildingIndex = resultCoordinate.newBuildingIndex;
										newAddress += newBuildingIndex + "\n";
									}
									// 새(도로명)주소 매칭을 한
									// 경우, 건물 이름를 반환
									if (resultCoordinate.newBuildingName.length > 0) {
										newBuildingName = resultCoordinate.newBuildingName;
										newAddress += newBuildingName + "\n";
									}
									// 새주소 건물을 매칭한 경우
									// 새주소 건물 동을 반환
									if (resultCoordinate.newBuildingDong.length > 0) {
										newBuildingDong = resultCoordinate.newBuildingDong;
										newAddress += newBuildingDong + "\n";
									}
									// 검색 결과 표출
									if (lonEntr > 0) {
										
										var text =  lat 
										var text2 = lon
										$("#result2").html(text);
										$("#result3").html(text2);
									} 
								}					
                                   
								
							}
						},
				
					});
				});
		
			}
			
		</script>
	</head>
	<body onload=initTmap();initTmap2();>
	
		<p id="result2"></p>
		<p id="result3"></p>
		

		<input type="text" class="text_custom" id="fullAddr"
			name="fullAddr" value="서울시 마포구 와우산로29가길 69">
		<button id="btn_select">적용하기</button>
		
		
	</body>
</html>
			