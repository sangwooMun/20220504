package com.yedam.forecast.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.yedam.app.common.PropertiesPair;
import com.yedam.forecast.vo.WeatherInfo;

public class ForecastService {

	/// 동네예보 - 육상예보조회
	public static List<WeatherInfo> getWeatherForecast() { // 정적
		String key = "XNnRW73S8GSMY4IQEK4Mb0dBXOAfVx1ffZaD3fgCo7luTjZVuBXa/yXXlCNnv7hERxlgQtasxqJvoGtmLsUXbw==";

		String serviceURL = "http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?";

		List<PropertiesPair> params = new ArrayList<PropertiesPair>();
		params.add(new PropertiesPair("ServiceKey", key));
		params.add(new PropertiesPair("pageNo", "1"));
		params.add(new PropertiesPair("numOfRows", "10"));
		params.add(new PropertiesPair("dataType", "JSON"));
		params.add(new PropertiesPair("regId", "11A00101"));

		StringBuilder sb = new StringBuilder();

		try {
			String paramURL = PropertiesPair.getQuery(params);

			String requestURL = serviceURL + paramURL;
			URL url = new URL(requestURL);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-type", "application/json"); // 여러가지 정보를 줘야할때 포괄적으로 사용
																		// json 형태롤 데이터를 보내고 있다는 뜻
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
			} else {
				System.out.println(con.getResponseMessage());
			}
			con.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

		String jsonResult = sb.toString();
		
		return getWeatherData(jsonResult);
	}

	private static List<WeatherInfo> getWeatherData(String jsonData) {
		List<WeatherInfo> list = new ArrayList<WeatherInfo>();
		try {
			JSONParser parser = new JSONParser();
			
			JSONObject forecastData = (JSONObject)parser.parse(jsonData);
			JSONObject response = (JSONObject)forecastData.get("response");
			JSONObject body = (JSONObject)response.get("body");
			JSONObject items = (JSONObject)body.get("items");
			JSONArray item = (JSONArray)items.get("item");
			
			for(int i =0; i< item.size(); i++) {
				JSONObject data = (JSONObject)item.get(i);
				WeatherInfo info = new WeatherInfo();
				
				// 발표시간 = 필수값
				info.setAnnounceTime((long)data.get("announceTime"));
				// 발표번호 = 옵션
				info.setNumEf((data.get("numEf") != null)? 0 : (long)data.get("numEf"));
				// 예보구역코드 = 필수값
				info.setRegId((String)data.get("regId"));
				// 강수확률 = 옵션
				info.setRnSt((data.get("rnSt")==null)? 0 : (long)data.get("rnSt"));
				// 강수형태 = 필수값
				info.setRnYn((long)data.get("rnYn"));
				// 예상기온 = 옵션
				info.setTa((data.get("ta")==null)? "" : (String)data.get("ta"));
				// 날씨 = 필수값
				info.setWf((String)data.get("wf"));
				// 하늘상태 = 필수값
				info.setWfCd((String)data.get("wfCd"));
				// 풍속 강도코드 = 필수값
				info.setWsIt((String)data.get("wsIt"));
				
				list.add(info);
			}
			
		}catch(ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
}
