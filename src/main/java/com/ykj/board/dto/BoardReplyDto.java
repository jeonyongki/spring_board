package com.ykj.board.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class BoardReplyDto {
	private int r_num;//pk
	private int r_bnum;//fk 게시글번호
	private String r_contents;
	@JsonFormat(pattern = "yyyy-MM-dd-E HH:mm:ss",timezone = "Asia/Seoul")
	private Timestamp r_date;
	private String r_id;
}
