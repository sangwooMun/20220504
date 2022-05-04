package com.yedam.movie.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.yedam.app.common.PropertiesPair;
import com.yedam.movie.vo.BoxOfficeInfo;
import com.yedam.movie.vo.BoxOfficeResult;
import com.yedam.movie.vo.movieInfo;

public class MovieService {
	private static final String key = "2a875a30cdd35c1d7158134d991eef15";

	// 일별 박스 오피스
	public static List<movieInfo> getDailyBoxOfficeResult() {
		String serviceURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?";
		// 요청하려는 URL 가져오기 json 은 끝에 ? 붙여야 함
		
		List<PropertiesPair> params = new ArrayList<PropertiesPair>();
		params.add(new PropertiesPair("key", key));
		params.add(new PropertiesPair("targetDt", "20220429"));
		
		StringBuilder sb = new StringBuilder();

		try {
			String paramURL = PropertiesPair.getQuery(params);

			String requseURL = serviceURL + paramURL;
			
			URL url = new URL(requseURL); // url 생성자 requseURL 반환 받음
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {	// 서버에 정보를 보내고 응답하는 것, 정상적으로 연결이 됐다면 http_ok를 받겠다.
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8")); // 스트림 염, 데이터 입력 받기
				String line;
				while((line=br.readLine()) != null) { // null이 아닐경우 계속 받겠다
					sb.append(line);
				}
				br.close(); // 스트림 열었으면 닫아야함 통신도 끊어야함
			}else {
				System.out.println(con.getResponseMessage()); // 잘 못 됐을 경우
			}
			con.disconnect(); // 연결 끊기
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String jsonResult = sb.toString();
		BoxOfficeResult result = new Gson().fromJson(jsonResult, BoxOfficeResult.class);
		
//		System.out.println(jsonResult); // 파싱하기전 확인차 출력
		
//		BoxOfficeInfo info = result.getBoxOfficeResult();
//		List<MovieInfo> list = info.getDailyBoxOfficeList();
		
		return result.getBoxOfficeResult().getDailyBoxOfficeList();
	}
}
