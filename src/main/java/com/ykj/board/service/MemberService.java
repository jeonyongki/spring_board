package com.ykj.board.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ykj.board.dao.MemberDao;
import com.ykj.board.dto.MemberDto;

import lombok.extern.java.Log;
@Log
@Service
public class MemberService {
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	HttpSession httpSession;
	@Transactional
	public String memberInsert(MemberDto member, RedirectAttributes redirectAttributes) {
		String view = null;
		String msg = null;
		//비밀번호 암호화(Spring Security 암호화 인코더)
		PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		String encPwd = pwdEncoder.encode(member.getM_pwd());
		member.setM_pwd(encPwd);
		log.info(encPwd);
		if(member!=null) {
			try {
				memberDao.memberInsert(member);
				view ="redirect:loginForm";
				msg = member.getM_id()+"님 회원가입 완료!";
				log.info(msg+" >> loginForm");
			} catch (Exception e) {
				view ="redirect:joinForm";
				msg = member.getM_id()+"님 회원가입 실패!";
				log.info(msg+" >> joinForm\n"+e.getMessage());
				e.printStackTrace();
			}
			redirectAttributes.addFlashAttribute("msg", msg);
		}
		return view;
	}

	public boolean memberIdCheck(String id) {
		
		int result = memberDao.memberIdCheck(id);
		
		boolean isCount = (result!=0) ? true : false;
		
		return isCount;
	}

	public String memberLogin(MemberDto dto, RedirectAttributes redirectAttributes) {
		String msg = null;
		String view = "redirect:loginForm";
		String currId = dto.getM_id();
		try {
			int result = memberDao.memberIdCheck(currId);
			if(result!=0) {//아이디 존재
				String currPwd = dto.getM_pwd();
				String dbPwd = memberDao.memberPwdCheck(currId);
				PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
				boolean isPwd = pwdEncoder.matches(currPwd, dbPwd);
				if(isPwd) {	
					dto = memberDao.memberLoginInfo(currId);
					httpSession.setAttribute("member", dto);
					msg = "로그인 되었습니다!";				
					view = "redirect:boardList";
					log.info(msg);
				}else {
					msg = "비밀번호 오류!";
					log.info(msg);
				}
					
			}else {
				msg = "아이디가 존재하지않습니다!";
				log.info(msg);
			}
			
		} catch (Exception e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}	
				
		redirectAttributes.addFlashAttribute("msg",msg);
		return view;
	}

}
