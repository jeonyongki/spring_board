package com.ykj.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ykj.board.service.BoardService;

import lombok.extern.java.Log;
@Log
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("boardList")
	public ModelAndView boardList(@RequestParam(name = "pageNum", required = false) Integer pageNumber) {
		//pageNumber는 페이지 번호를 받는 매개변수(페이지 변경시 해당하는 페이지 번호)
		log.info("boardList() : "+pageNumber);
		ModelAndView mv = boardService.getBoardList(pageNumber);
		return mv;
	}
	@GetMapping("writeForm")
	public String writeForm(@RequestParam(name="pageNum", required=false)Integer number) {
		int pageNumber = (number!=null) ? number : 1;
		log.info("writeForm() : "+pageNumber);
		return "writeForm";
	}	
	@PostMapping("writeProc")
	public String writeProc(MultipartHttpServletRequest multipart, RedirectAttributes redirectAttributes) {
		log.info("writeProc()");
		String view = boardService.boardInsert(multipart, redirectAttributes);
		return view;
	}
	//게시글 상세보기
	@GetMapping("boardContents")
	public ModelAndView boardContents(@RequestParam(name="bnum", required=false) Integer bnum) {
		log.info("boardContents() : "+bnum);
		ModelAndView mv = boardService.getBoardContents(bnum);
		return mv;
	}
}
