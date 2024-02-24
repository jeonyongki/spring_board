package com.ykj.board.dao;

import com.ykj.board.dto.MemberDto;

public interface MemberDao {
	
	public void memberInsert(MemberDto member);

	public void memberDeleteAll();
	
	public int memberIdCheck(String id);
	
	public String memberPwdCheck(String id);

	public MemberDto memberLoginInfo(String id);
}
