package com.yedam.movie.vo;



import java.util.Date;
// sql = db 랑 연결하는 것
import lombok.Data;

@Data
public class movieInfo {
	private int rnum;
	private int rank;
	private int rankInten;
	private String rankOldAndNew;
	private String movieCd;
	private String movieNm;
	private Date openDt;
	private long salesAmt;
	private double salesShare;
	private long salesInten;
	private double salesChange;
	private long salesAcc;
	private long audiCnt;
	private long audiInten;
	private double audiChange;
	private long audiAcc;
	private long scrnCnt;
	private long showCnt;
}
