package com.ykj.board.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDto {
	private int bnum;
	private String btitle;
	private String bcontents;
	private String bid;//작성자 id(외래키)
	private String mname;//작성자 이름
	private Timestamp bdate;//yyyy/MM/dd hh:mm:dd 형식의 날짜 데이터
	private int bviews;
}
