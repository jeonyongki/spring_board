package com.ykj.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MemberDto {
	private String m_id;
	private String m_pwd;
	private String m_name;
	private String m_birth;
	private String m_addr;
	private String m_phone;
	private int m_point;
	private String g_name;//등급
}
