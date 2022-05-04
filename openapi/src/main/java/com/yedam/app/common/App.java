package com.yedam.app.common;

import java.util.List;

import com.yedam.forecast.service.ForecastService;
import com.yedam.forecast.vo.WeatherInfo;

public class App {

	public static void main(String[] args) {
//		MovieService.getDailyBoxOfficeResult();
//		List<movieInfo> list = MovieService.getDailyBoxOfficeResult();
//		for(movieInfo info : list) {
//			System.out.println(info);
//		}
		
//		ForecastService.getWeatherForecast();
		List<WeatherInfo> list = ForecastService.getWeatherForecast();
		for(WeatherInfo info : list) {
			System.out.println(info);
		}
	}

}
