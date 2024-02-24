package com.ykj.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ykj.board.dao.MemberDao;
import com.ykj.board.dto.MemberDto;

import lombok.extern.java.Log;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberDaoTest {
	@Autowired
	MemberDao memberDao;
	@Test
	public void memberInsertTest() {
		PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		String encPwd = null;
		for(int i=1; i<10; i++) {
			MemberDto member = new MemberDto("tester"+i,"1234","테스터"+i,"2024030"+i,"대한민국","010-1111-111"+i,i*10,null);
			encPwd = pwdEncoder.encode(member.getM_pwd());
			member.setM_pwd(encPwd);
			memberDao.memberInsert(member);
			log.info(member.toString());
		}
	}
	@Test
	public void idCheckTest() {
		String id = "tester10";
		int num = memberDao.memberIdCheck(id);
		if(num!=0) log.info("아이디 중복입니다! "+id);
		else log.info("사용할수있는 아이디 "+id);
	}
	@Test
	public void pwdCheckTest() {
		String id = "tester2";
		String pwd = memberDao.memberPwdCheck(id);
		if(pwd!=null) log.info(id+ " : "+pwd);
		else log.info(id+" : 아이디없음");
	}	
	@Test
	public void memberLoginInfoTest() {
		String id = "tester5";
		MemberDto dto = memberDao.memberLoginInfo(id);
		if(dto!=null) {
			log.info(dto.toString());
		}
	}
}

